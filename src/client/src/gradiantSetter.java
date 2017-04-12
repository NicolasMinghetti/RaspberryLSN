import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by mperrier on 12/04/17.
 */
public class gradiantSetter implements Runnable{


    private InetAddress addr;
    private int port;

    gradiantSetter(int port, InetAddress addr){
        this.port = port;
        this.addr = addr;
    }

    public void run() {

        try {
                DatagramSocket socket = new DatagramSocket(port, addr);
                socket.setBroadcast(true);

                while (true) {

                    byte[] buf = new byte[1000];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);

                    String received = new String(packet.getData());
                    JSONObject obj = new JSONObject(received);
                    System.out.println("Package received: " + obj);

                }

        } catch (Exception e) {
                System.out.println("Erreur :" + e);
        }
    }

}


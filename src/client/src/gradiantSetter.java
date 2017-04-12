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
                JSONObject packetGradient = Utils.receive(socket);
            }

        } catch (Exception e) {
            System.out.println("Erreur :" + e);
        }


    }

}


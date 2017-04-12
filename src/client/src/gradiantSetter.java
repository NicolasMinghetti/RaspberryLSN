import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by mperrier on 12/04/17.
 */
public class gradiantSetter implements Runnable{


    InetAddress addr;
    int port;

    gradiantSetter(int port, InetAddress addr){
        this.port = port;
        this.addr = addr;
    }

    public void run() {

        while (true) {
            try {
                DatagramSocket socket = new DatagramSocket(port, addr);
                socket.setBroadcast(true);
                DatagramPacket packet;
                byte[] buf = new byte[1000];
                packet = new DatagramPacket(buf, buf.length);

                socket.receive(packet);

                String received = new String(packet.getData());
                System.out.println("Package received: " + received);

                socket.close();

            } catch (Exception e) {
                System.out.println("Erreur :" + e);
            }
        }
    }
}

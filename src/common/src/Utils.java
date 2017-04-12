import org.json.JSONObject;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This class contains Utils functions to be used by client and server
 * Created by nicolas on 12/04/17.
 */
public class Utils {

    static int portNumber = 4444;
    static String networkAddress = "192.168.1.255";
    static int maxMessageLength = 1000;

    /**
     * This function can be used to broadcast a message on the network
     * @param message the message to send
     * @param senderId the id of the sender
     */
    public static void broadcast (int message, int senderId) {

        JSONObject obj = new JSONObject();

        obj.put("senderId", senderId);
        obj.put("message", message);

        System.out.println("Sending message: " + obj.toString());

        byte[] buf = obj.toString().getBytes();
        if (obj.toString().getBytes().length > maxMessageLength) {
            throw new java.lang.Error("Error: Message length exeeds maxMessageLength");
        }

        try {
            InetAddress address = InetAddress.getByName(networkAddress);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, portNumber);

            DatagramSocket socket = new DatagramSocket(portNumber);
            socket.send(packet);
        } catch (Exception E) {
            System.out.println("Error on InetAddress or IO exeption");
        }

    }
}

import org.json.JSONObject;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This class contains Utils functions to be used by client and server
 * Created by nicolas on 12/04/17.
 */
public class Utils {

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
        if (obj.toString().getBytes().length > Constants.maxMessageLength) {
            throw new java.lang.Error("Error: Message length exeeds maxMessageLength");
        }

        try {
            InetAddress address = InetAddress.getByName(Constants.networkAddress);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Constants.portNumber);

            DatagramSocket socket = new DatagramSocket(Constants.portNumber);
            socket.send(packet);
        } catch (Exception E) {
            System.out.println("Error on InetAddress or IO exeption : " +E);
        }

    }
}

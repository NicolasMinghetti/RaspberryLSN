import org.json.JSONObject;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

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
            DatagramPacket packet = new DatagramPacket(buf, buf.length, Constants.getNetAddr(), Constants.portNumber);

            DatagramSocket socket = new DatagramSocket(Constants.portNumber);
            socket.send(packet);
        } catch (Exception E) {
            System.out.println("Error on InetAddress or IO exeption : " +E);
        }

    }

    public static JSONObject receive(DatagramSocket socket) throws Exception{

        byte[] buf = new byte[Constants.maxMessageLength];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData());
        JSONObject obj = new JSONObject(received);
        System.out.println("Package received: " + obj);
        return obj;
    }
}

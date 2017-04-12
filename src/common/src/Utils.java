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
     * @param networkObject JSONObject the packet to be sent over the netork
     */
    public static void broadcast (JSONObject networkObject) {

        System.out.println("Sending message: " + networkObject.toString());

        byte[] buf = networkObject.toString().getBytes();
        if (networkObject.toString().getBytes().length > Constants.maxMessageLength) {
            throw new java.lang.Error("Error: Message length exeeds maxMessageLength");
        }

        try {
            DatagramPacket packet = new DatagramPacket(buf, buf.length, Constants.getNetAddr(), Constants.portNumber);
            DatagramSocket socket = new DatagramSocket(Constants.portNumber);
            socket.send(packet);
        } catch (Exception E) {
            System.out.println("Error on IO exception: " + E);
        }
    }

    /**
     * This function returns a network packet with the given parameters
     * @param buildingGradient boolean, set to true if initializing gradient
     * @param senderId the id of the sender (the device that created the message)
     * @param sentTime the time when the message is sent for the first time
     * @param messageUid the uniqueId of the message
     * @return obj the JSON network packet
     */
    public static JSONObject createNetworkPacket(boolean buildingGradient, int senderId, String sentTime, int messageUid) {

        JSONObject obj = new JSONObject();
        obj.put("gradientInitialize", buildingGradient);
        obj.put("gradient", 0);
        obj.put("senderId", senderId);
        obj.put("sentTime", sentTime);
        obj.put("messageUid", messageUid);
        return  obj;
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

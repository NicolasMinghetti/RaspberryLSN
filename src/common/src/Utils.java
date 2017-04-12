import org.json.JSONObject;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * This class contains Utils functions to be used by client and server
 * Created by nicolas on 12/04/17.
 */
public class Utils {

    /**
     * This function can be used to broadcast a message on the network
     * @param networkObject JSONObject the packet to be sent over the network
     * @param socket DatagramSocket socket on which the message is sent
     */
    public static void broadcast (JSONObject networkObject, DatagramSocket socket) {

        System.out.println("Sending message: " + networkObject.toString());

        byte[] buf = networkObject.toString().getBytes();
        if (networkObject.toString().getBytes().length > Constants.maxMessageLength) {
            throw new java.lang.Error("Error: Message length exceeds maxMessageLength");
        }

        try {
            DatagramPacket packet = new DatagramPacket(buf, buf.length, Constants.getNetAddr(), Constants.portNumber);
            socket.send(packet);
        } catch (Exception E) {
            System.out.println("Error on IO exception: " + E);
        }
    }

    /**
     * This function returns a network packet with the given parameters
     * @param buildingGradient boolean, set to true if initializing gradient
     * @param senderId the id of the sender (the device that sent the last message)
     * @param creatorId the id of the message creator (the device that created the message)
     * @param gradient the gradient of this sender
     * @param sentTime the time when the message is sent for the first time
     * @param messageUid the uniqueId of the message
     * @return obj the JSON network packet
     */
    public static JSONObject createNetworkPacket(boolean buildingGradient, int gradient, int senderId, int creatorId, String sentTime, String messageUid) {

        JSONObject obj = new JSONObject();
        obj.put("gradientInitialize", buildingGradient);
        obj.put("gradient", gradient);
        obj.put("senderId", senderId);
        obj.put("creatorId", creatorId);
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
        return obj;
    }
}

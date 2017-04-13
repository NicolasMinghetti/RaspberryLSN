import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.UUID;

/**
 * This class contains Utils functions to be used by client and server
 * Created by nicolas on 12/04/17.
 */
public class Utils {

    public static final Logger logger = LogManager.getLogger();

    /**
     * This function can be used to broadcast a message on the network
     * @param networkObject JSONObject the packet to be sent over the network
     * @param socket DatagramSocket socket on which the message is sent
     */
    public static void broadcast (JSONObject networkObject, DatagramSocket socket) {

        logger.info("Sending message: " + networkObject.toString());

        byte[] buf = networkObject.toString().getBytes();
        if (networkObject.toString().getBytes().length > Constants.messageLength) {
            throw new java.lang.Error("Error: Message length exceeds messageLength");
        }
        buf= Arrays.copyOf(buf, Constants.messageLength);

        try {
            DatagramPacket packet = new DatagramPacket(buf, Constants.messageLength, Constants.getNetAddr(), Constants.portNumber);
            socket.send(packet);
        } catch (Exception E) {
            logger.error("Error on IO exception: " + E);
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

        byte[] buf = new byte[Constants.messageLength];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData());
        JSONObject obj = new JSONObject(received);
        return obj;
    }

    /**
     * Use this function to get a unique id for a sent message
     * @param deviceId the id of this device, it is added at the end of the return String
     * @return String the unique generated
     */
    public static String getMessageUid (int deviceId) {
        return UUID.randomUUID().toString() + "-" + String.valueOf(deviceId);
    }
}

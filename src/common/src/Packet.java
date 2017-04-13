import org.json.JSONObject;

/**
 * Created by nicolas on 13/04/17.
 */
public class Packet extends JSONObject {

    /**
     * This function returns a network packet with the given parameters
     * @param buildingGradient boolean, set to true if initializing gradient
     * @param senderId the id of the sender (the device that sent the last message)
     * @param creatorId the id of the message creator (the device that created the message)
     * @param gradient the gradient of this sender
     * @param sentTime the time when the message is sent for the first time
     * @param messageUid the uniqueId of the message
     * @return Packet the network packet
     */
    public Packet (boolean buildingGradient, int gradient, int senderId, int creatorId, String sentTime, String messageUid) {
        this.put("gradientInitialize", buildingGradient);
        this.put("gradient", gradient);
        this.put("senderId", senderId);
        this.put("creatorId", creatorId);
        this.put("sentTime", sentTime);
        this.put("receptionTime", -1);
        this.put("messageUid", messageUid);
    }

    public Packet(String string) {
        super(string);
    }

    public String logMessage() {
        return this.get("messageUid") + "," + this.get("senderId") + "," + this.get("gradient") + "," + this.get("creatorId") + "," + this.get("sentTime") + "," + this.get("receptionTime")+","+ this.get("gradientInitialize");
    }
}

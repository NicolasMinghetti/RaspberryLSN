import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.UUID;

/**
 * This class contains Utils functions to be used by client and server
 * Created by nicolas on 12/04/17.
 */
public class Utils {

    public static final Logger trafficLog = LogManager.getLogger("trafficLog");
    public static final Logger debugLog = LogManager.getLogger("debugLog");

    /**
     * This function can be used to broadcast a message on the network
     * @param networkObject Packet the packet to be sent over the network
     * @param socket DatagramSocket socket on which the message is sent
     */
    public static void broadcast (Packet networkObject, DatagramSocket socket) {

        byte[] buf = networkObject.toString().getBytes();
        if (networkObject.toString().getBytes().length > Constants.messageLength) {
            throw new java.lang.Error("Error: Message length exceeds messageLength");
        }
        buf= Arrays.copyOf(buf, Constants.messageLength);

        try {
            DatagramPacket packet = new DatagramPacket(buf, Constants.messageLength, Constants.getNetAddr(), Constants.portNumber);
            trafficLog.info("sending," + networkObject.logMessage());
            socket.send(packet);
        } catch (Exception E) {
            debugLog.error("Error on IO exception: " + E);
        }
    }

    public static Packet receive(DatagramSocket socket, int Id) throws Exception{
        byte[] buf = new byte[Constants.messageLength];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData());
        Packet receivedPacket =  new Packet(received);
        if(receivedPacket.getInt("senderId") != Id) {
            Utils.trafficLog.info("receive," + receivedPacket.logMessage());
        }
        return receivedPacket;
    }

    /**
     * Use this function to get a unique id for a sent message
     * @param deviceId the id of this device, it is added at the end of the return String
     * @return String the unique generated
     */
    public static String getMessageUid (int deviceId) {
        return UUID.randomUUID().toString() + "-" + String.valueOf(deviceId);
    }

    public  static String getTime(){
        return DateTimeFormatter.ofPattern("HH:mm:ss.SSS").withZone(ZoneId.systemDefault()).format(Instant.now());
    }

}

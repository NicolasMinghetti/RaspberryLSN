import java.net.DatagramSocket;
import java.time.Instant;

/**
 * This is the main class of the server. It represents the sink in the ad-hoc gradient routing network.
 * Created by nicolas on 11/04/17.
 */
public class Main {

    private static int deviceId;

    private static int gradient = 0;    // the server is always the sink in the network

    public static void main(String[] args) {
        Utils.logger.info("Hello server");
        deviceId = Integer.parseInt(args[0]);
        initialization();
    }

    /**
     * This function initializes the gradient network
     */
    private static void initialization() {


        try {
            DatagramSocket socket = new DatagramSocket(Constants.portNumber);
            Utils.broadcast(
                    Utils.createNetworkPacket(
                            true, gradient, deviceId, deviceId, Instant.now().toString(),
                            Utils.getMessageUid(deviceId)),
                    socket);
        } catch(Exception E) {
            Utils.logger.error("Socket exception error: " + E);
        }
    }
}

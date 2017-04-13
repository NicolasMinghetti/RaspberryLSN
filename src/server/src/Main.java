import java.net.DatagramSocket;
import java.time.Instant;

/**
 * This is the main class of the server. It represents the sink in the ad-hoc gradient routing network.
 * Created by nicolas on 11/04/17.
 */
public class Main {

    private static int gradient = 0;    // the server is always the sink in the network

    private static Device server;

    public static void main(String[] args) {
        Utils.logger.info("Hello server");
        server = new Device(Constants.getNetAddr(), Constants.portNumber, Integer.valueOf(args[0]));
        server.setGradient(gradient);

        initialization();
        (new Thread(new ServerListener(server))).start();
    }

    /**
     * This function initializes the gradient network
     */
    private static void initialization() {
        try {
            DatagramSocket socket = server.getSocket();
            Utils.broadcast(
                    Utils.createNetworkPacket(
                            true, server.getGradient(), server.getId(), server.getId(), Instant.now().toString(),
                            Utils.getMessageUid(server.getId())),
                    socket);
        } catch(Exception E) {
            Utils.logger.error("Socket exception error: " + E);
        }
    }
}

import java.net.DatagramSocket;

/**
 * This is the main class of the server. It represents the sink in the ad-hoc gradient routing network.
 * Created by nicolas on 11/04/17.
 */
public class Main {

    private static int gradient = 0;    // the server is always the sink in the network

    private static Device server;

    public static void main(String[] args) {
        Utils.debugLog.debug("Hello server");
        server = new Device(Constants.portNumber, Integer.valueOf(args[0]));
        server.setGradientServer(gradient);

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
                    new Packet(
                            true, server.getGradient(), server.getId(), server.getId(),  Utils.getTime(),
                            Utils.getMessageUid(server.getId())),
                    socket);
        } catch(Exception E) {
            Utils.debugLog.error("Socket exception error: " + E);
        }
    }
}

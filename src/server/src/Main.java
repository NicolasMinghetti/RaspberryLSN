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

        (new Thread(new ServerSender(server))).start();
        (new Thread(new ServerListener(server))).start();
    }

}

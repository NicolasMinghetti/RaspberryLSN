import java.net.DatagramSocket;
import java.util.concurrent.TimeUnit;

/**
 * This class listens and logs messages received by the server
 * Created by nicolas on 13/04/17.
 */
public class ServerSender implements Runnable {

    private Device server;

    ServerSender(Device server){
        this.server = server;
    }

    public void run() {
        try {
            while (true) {
                initialization();
                TimeUnit.MILLISECONDS.sleep(Constants.gradientRefreshTime);
            }
        } catch (Exception e) {
            Utils.debugLog.error("Error: " + e);
        }
    }

    /**
     * This function initializes the gradient network
     */
    private void initialization() {
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

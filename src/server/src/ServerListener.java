import org.json.JSONObject;

/**
 * This class listens and logs messages received by the server
 * Created by nicolas on 13/04/17.
 */
public class ServerListener implements Runnable {

    private Device cli;

    ServerListener(Device cli){
        this.cli = cli;
    }

    public void run() {
        try {
            while (true) {
                Packet packet = Utils.receive(cli.getSocket(), cli.getId());
            }
        } catch (Exception e) {
            Utils.debugLog.error("Error: " + e);
        }
    }
}

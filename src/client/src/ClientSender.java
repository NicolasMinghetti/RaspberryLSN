import org.json.JSONObject;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * This class broadcasts periodically packets on the network
 * Created by nicolas on 12/04/17.
 */
public class ClientSender implements Runnable{

    private Client cli;

    ClientSender(Client cli){
        this.cli = cli;
    }

    /**
     * This function broadcasts periodically messages on the network when its gradient is set
     */
    public void run() {
        try {
            while (true) {
                if(cli.getGradient() != -1) {
                    TimeUnit.SECONDS.sleep(3);
                    JSONObject networkPacket = Utils.createNetworkPacket(false,
                            cli.getGradient(), cli.getId(), cli.getId(), Instant.now().toString(), String.valueOf(cli.getUniqueId()));
                    Utils.broadcast(networkPacket, cli.getSocket());
                }
            }
        } catch (Exception e) {
            Utils.logger.error("Error: " + e);
        }
    }
}

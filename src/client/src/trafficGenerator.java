import org.json.JSONObject;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Created by mperrier on 13/04/17.
 */
public class trafficGenerator {

    private int startWait;
    private Client cli;

    trafficGenerator(int startWait, Client cli){
        this.startWait=startWait;
        this.cli=cli;
    }

    public void CBR(int numberOfPacket, int waitBetweenPacket){
        try {
            for(int i=0; i<=numberOfPacket; i++) {
                JSONObject networkPacket = Utils.createNetworkPacket(false,
                        cli.getGradient(), cli.getId(), cli.getId(), Instant.now().toString(), String.valueOf(cli.getUniqueId()));
                if(cli.getGradient() == -1){
                    if(i > 0) i--;
                }else{
                    if(i==1)TimeUnit.MILLISECONDS.sleep(startWait);
                    Utils.broadcast(networkPacket, cli.getSocket());
                }
                TimeUnit.MILLISECONDS.sleep(waitBetweenPacket);
                }

        }catch (Exception e) {
            Utils.logger.error("Error: " + e + e.getStackTrace());
        }
    }

}

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Created by mperrier on 13/04/17.
 */
public class trafficGenerator {

    private int startWait;
    private Device cli;

    trafficGenerator(int startWait, Device cli){
        this.startWait=startWait;
        this.cli=cli;
    }

    public void CBR(int numberOfPacket, int waitBetweenPacket){
        try {
            for(int i=0; i<=numberOfPacket; i++) {
                Packet networkPacket = new Packet(false,
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
            Utils.debugLog.error("Error: " + e + e.getStackTrace());
        }
    }

}

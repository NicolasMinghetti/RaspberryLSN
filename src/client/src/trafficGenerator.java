import java.util.concurrent.TimeUnit;

/**
 * This class is used to broadcast traffic over the network
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
                TimeUnit.MILLISECONDS.sleep(Constants.startWait);

                for(int i=0; i<Constants.numberOfPacket; i++) {
                    Packet networkPacket = new Packet(false,
                            cli.getGradient(), cli.getId(), cli.getId(), Utils.getTime(), String.valueOf(cli.getUniqueId()));
                    Utils.broadcast(networkPacket, cli.getSocket());
                    TimeUnit.MILLISECONDS.sleep(waitBetweenPacket);
                }

        }catch (Exception e) {
            Utils.debugLog.error("Error: " + e);
        }
    }

}

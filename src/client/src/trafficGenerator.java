import org.json.JSONObject;

import java.net.DatagramSocket;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Created by mperrier on 13/04/17.
 */
public class trafficGenerator {

    private int startWait;
    private int packetSize;
    private Device cli;

    trafficGenerator(int startWait, int packetSize, Device cli){
        this.startWait=startWait;
        this.packetSize=packetSize;
        this.cli=cli;
    }

    public void CBR(int numberOfPacket, int waitBetweenPacket){
        try {
            TimeUnit.MILLISECONDS.sleep(startWait);
            for(int i=0; i<numberOfPacket; i++) {
                Packet networkPacket = new Packet(false,
                        cli.getGradient(), cli.getId(), cli.getId(), Instant.now().toString(), String.valueOf(cli.getUniqueId()));
                Utils.broadcast(networkPacket, cli.getSocket());
                TimeUnit.MILLISECONDS.sleep(waitBetweenPacket);
                }

        }catch (Exception e) {
            System.out.println("Error: " + e + e.getStackTrace());
        }
    }

}

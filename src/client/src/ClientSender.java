import org.json.JSONObject;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * This class broadcasts periodically packets on the network
 * Created by nicolas on 12/04/17.
 */
public class ClientSender implements Runnable{

    private Device cli;

    ClientSender(Device cli){
        this.cli = cli;
    }

    /**
     * This function broadcasts periodically messages on the network when its gradient is set
     */
    public void run() {
        (new trafficGenerator(0,0,cli)).CBR(5,1000);
    }
}

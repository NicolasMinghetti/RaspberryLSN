import org.json.JSONObject;

import java.time.Instant;
import java.util.Random;

/**
 * This is the main class of the server. It represents the sink in the ad-hoc gradient routing network.
 * Created by nicolas on 11/04/17.
 */
public class Main {

    private static int deviceId;

    public static void main(String[] args) {
        System.out.println("Hello server");
        deviceId = Integer.parseInt(args[0]);
        initialization();
    }

    /**
     * This function initializes the gradient network
     */
    private static void initialization() {
        Random rand = new Random();
        Utils.broadcast(Utils.createNetworkPacket(true, deviceId, Instant.now().toString(), rand.nextInt(1000000000)));
    }
}

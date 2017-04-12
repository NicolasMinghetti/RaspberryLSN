import org.json.JSONObject;

import java.time.Instant;

/**
 * Created by mperrier on 12/04/17.
 */
public class GradiantSetter {

    private JSONObject packet;
    private Client cli;

    GradiantSetter(JSONObject packet, Client cli){
        this.packet = packet;
        this.cli = cli;
    }
    public void run(){

        if(packet.getInt("gradient")<cli.getGradient() || cli.getGradient() == -1){
            cli.setGradient(packet.getInt("gradient")+1);
            JSONObject packetInit = Utils.createNetworkPacket(true,
                    cli.getGradient(), cli.getId(), cli.getId(), Instant.now().toString(), String.valueOf(cli.getUniqueId()));
            Utils.broadcast(packetInit, cli.getSocket());
        }
    }
}

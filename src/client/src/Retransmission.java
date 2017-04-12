import org.json.JSONObject;

/**
 * Created by mperrier on 12/04/17.
 */
public class Retransmission implements Runnable{

    private Client cli;
    private JSONObject packet;

    Retransmission(JSONObject packet, Client cli){
        this.cli = cli;
        this.packet = packet;
    }

    public void run(){

        if(packet.getInt("gradient")>cli.getGradient()){
            packet.put("gradient", cli.getGradient());
            Utils.broadcast(packet, cli.getSocket());
        }

    }
}

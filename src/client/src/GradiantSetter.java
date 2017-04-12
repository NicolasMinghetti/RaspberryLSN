import org.json.JSONObject;

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

        if(packet.getInt("gradient")<cli.getGradient()){
            cli.setGradient(packet.getInt("gradient")+1);
            JSONObject packetInit = new JSONObject();
            packetInit.put("gradient", cli.getGradient());
            packetInit.put("init", true);

        }
    }
}

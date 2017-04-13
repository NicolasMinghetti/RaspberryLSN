import java.util.Random;
import static java.lang.Thread.sleep;

/**
 * Created by mperrier on 12/04/17.
 */
public class GradiantSetter implements Runnable{

    private Packet packet;
    private Device cli;

    GradiantSetter(Packet packet, Device cli){
        this.packet = packet;
        this.cli = cli;
    }
    public void run(){
        try {
            Random rand = new Random();
            sleep(rand.nextInt(Constants.postGradientSleepTime));
            if(packet.getInt("gradient")<cli.getGradient() || cli.getGradient() == -1){
                cli.setGradient(packet.getInt("gradient")+1);
                Packet packetInit = new Packet(true,
                        cli.getGradient(), cli.getId(), cli.getId(), Utils.getTime(), String.valueOf(cli.getUniqueId()));
                Utils.broadcast(packetInit, cli.getSocket());
            }
        } catch (Exception E) {
            Utils.debugLog.error("Error random" + E);
        }

    }
}

import java.time.Instant;

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

        if(packet.getInt("gradient")<cli.getGradient() || cli.getGradient() == -1){
            cli.setGradient(packet.getInt("gradient")+1);
            Packet packetInit = new Packet(true,
                    cli.getGradient(), cli.getId(), cli.getId(), Instant.now().toString(), String.valueOf(cli.getUniqueId()));
            Utils.broadcast(packetInit, cli.getSocket());
        }
    }
}

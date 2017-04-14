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
        cli.setGradientClient(packet.getInt("gradient"));
    }
}

/**
 * This class is used when the device receives a network packet with gradientInitialize set to true. It sets the new
 * gradient for this device, and the broadcasts a network package for the other devices on the network.
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

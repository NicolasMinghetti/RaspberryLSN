/**
 * This class is used to retransmit a package over the network. Every clients use it to
 * transmit network package to the sink
 * Created by mperrier on 12/04/17.
 */
public class Retransmission implements Runnable{

    private Device cli;
    private Packet packet;

    Retransmission(Packet packet, Device cli){
        this.cli = cli;
        this.packet = packet;
    }

    public void run(){

        if(packet.getInt("gradient")>cli.getGradient() && cli.getGradient()!=-1){
            packet.put("gradient", cli.getGradient());
            packet.put("senderId", cli.getId());
            Utils.broadcast(packet, cli.getSocket());
        }

    }
}

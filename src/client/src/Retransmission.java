/**
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

        if(packet.getInt("gradient")>cli.getGradient()){
            packet.put("gradient", cli.getGradient());
            Utils.broadcast(packet, cli.getSocket());
        }

    }
}

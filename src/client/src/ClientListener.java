/**
 * This class is used to listen packages on the network, depending on what's in the networkPackage, it will call
 * GradiantSetter of Retransmission
 * Created by mperrier on 12/04/17.
 */
public class ClientListener implements Runnable{

    private Device cli;

    ClientListener(Device cli){
        this.cli = cli;
    }

    public void run() {
        try {
            while (true) {
                Packet packet = Utils.receive(cli.getSocket(), cli.getId());
                if(packet.getInt("senderId") != cli.getId()) {

                    if (packet.getBoolean("gradientInitialize")) {
                        GradiantSetter graSet = new GradiantSetter(packet, cli);
                        (new Thread(graSet)).start();

                    }else{
                        if(cli.getGradient()==-1){
                            GradiantSetter graSet = new GradiantSetter(packet, cli);
                            (new Thread(graSet)).start();
                        }else {
                            Retransmission retran = new Retransmission(packet, cli);
                            (new Thread(retran)).start();
                        }
                    }
                }
            }
        } catch (Exception e) {
            Utils.debugLog.error("Error: " + e);
        }
    }
}


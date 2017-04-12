import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 * Created by mperrier on 12/04/17.
 */
public class ClientListener implements Runnable{

    private Client cli;

    ClientListener(Client cli){
        this.cli = cli;
    }

    public void run() {


        try {

            while (true) {

                JSONObject packet = Utils.receive(cli.getSocket());
                if(packet.getInt("senderId") != cli.getId()) {
                    System.out.println("message receive: " + packet);

                    if (packet.getBoolean("gradientInitialize")) {
                        GradiantSetter graSet = new GradiantSetter(packet, cli);
                        (new Thread(graSet)).start();

                    }else{
                        Retransmission retran = new Retransmission(packet, cli);
                        (new Thread(retran)).start();
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Erreur :" + e);
        }


    }

}


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
                System.out.println("gradiant client: " + cli.getGradient() );
                JSONObject packet = Utils.receive(cli.getSocket());
                if(packet.getBoolean("gradientInitialize")){
                    GradiantSetter graSet = new GradiantSetter(packet, cli);
                    graSet.run();
                }

            }

        } catch (Exception e) {
            System.out.println("Erreur :" + e);
        }


    }

}


import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by mperrier on 12/04/17.
 */
public class Client {

    private int gradient = -1;
    DatagramSocket socket;


    Client(InetAddress addr, int port){
        try {
            socket = new DatagramSocket(port, addr);
            socket.setBroadcast(true);
        }catch(Exception E){
            System.out.println("Socket creation error: " + E);
        }
    }

    public void setGradient(int gradient){
        this.gradient = gradient;
    }

    public int getGradient(){
        return gradient;
    }

    public DatagramSocket getSocket(){
        return socket;
    }

}

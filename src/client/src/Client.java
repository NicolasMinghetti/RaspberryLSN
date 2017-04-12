import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mperrier on 12/04/17.
 */
public class Client {

    private AtomicInteger gradient = new AtomicInteger(-1);
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
        this.gradient = new AtomicInteger(gradient);
    }

    public int getGradient(){
        return gradient.get();
    }

    public DatagramSocket getSocket(){
        return socket;
    }

}

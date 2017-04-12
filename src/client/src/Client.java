import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mperrier on 12/04/17.
 */
public class Client {

    private AtomicInteger gradient = new AtomicInteger(-1);
    private DatagramSocket socket;
    private int id = 1;
    private int messageId;


    Client(InetAddress addr, int port){
        try {
            socket = new DatagramSocket(port);
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

    public int getId(){
        return id;
    }

    public DatagramSocket getSocket(){
        return socket;
    }

    public String getUniqueId(){
        return Integer.toString(id)+"-"+UUID.randomUUID().toString();
    }

}

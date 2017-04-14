import java.net.DatagramSocket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents a device object, it contains information on grandient, uniqueId...
 * Created by mperrier on 12/04/17.
 */
public class Device {

    private AtomicInteger gradient = new AtomicInteger(-1);
    private DatagramSocket socket;
    private int id;

    Device(int port, int id){
        this.id = id;
        try {
            socket = new DatagramSocket(port);
            socket.setBroadcast(true);
        }catch(Exception E){
            Utils.debugLog.error("Socket creation error: " + E);
        }
    }

    public int getGradient(){
        return gradient.get();
    }

    public void setGradient(int gradient){
        this.gradient = new AtomicInteger(gradient);
    }

    public int getId(){
        return id;
    }

    public DatagramSocket getSocket(){
        return socket;
    }

    public String getUniqueId(){
        return Utils.getMessageUid(id);
    }

}

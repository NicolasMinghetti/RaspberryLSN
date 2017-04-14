import java.net.DatagramSocket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by mperrier on 12/04/17.
 */
public class Device {

    private int gradient = -1;
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
        return this.gradient;
    }

    public synchronized void setGradientClient(int gradient){
        try {
            if(gradient<this.gradient || this.gradient == -1){
                int oldGradient = this.gradient;
                this.gradient = gradient +1;
                Packet packetInit = new Packet(true,
                        this.gradient, this.id, this.id, Utils.getTime(), String.valueOf(this.getUniqueId()));
                Random rand = new Random();
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(Constants.postGradientSleepTime));
                Utils.broadcast(packetInit, this.getSocket());
                if(oldGradient== -1) (new Thread(new ClientSender(this))).start();
            }

        } catch (Exception E) {
            Utils.debugLog.error("Error random" + E);
        }
    }

    public void setGradientServer(int gradient){
        this.gradient = gradient;
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

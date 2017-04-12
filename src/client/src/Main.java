import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by nicolas on 11/04/17.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here

        System.out.println("Hello Client");

        int port = 4444;
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName("192.168.1.255");
        }catch (UnknownHostException e){
            System.out.println("Host unknown " + e);
        }

        gradiantSetter grad = new gradiantSetter(port,addr);
        grad.run();


    }
}

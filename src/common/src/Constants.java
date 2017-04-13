import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * This file contains constants values
 * Created by nicolas on 12/04/17.
 */
public class Constants {

    static int portNumber = 4444;   // port number for sent messages

    static String networkAddress = "192.168.1.255"; // broadcast address for local network

    static int messageLength = 1000; // max length for transmitted messages


    public static InetAddress getNetAddr(){
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(networkAddress);
        } catch (UnknownHostException e) {
            Utils.logger.error("Host unknown " + e);
        }
        return addr;
    }

}

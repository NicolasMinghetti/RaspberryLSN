import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by nicolas on 11/04/17.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here

        System.out.println("Hello Client");

        gradiantSetter grad = new gradiantSetter(Constants.portNumber,Constants.getNetAddr());
        grad.run();


    }
}

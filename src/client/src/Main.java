/**
 * This is the Main class for clients. It launches two threads: ClientSender and ClientListener
 * Created by nicolas on 11/04/17.
 */
public class Main {

    public static void main(String[] args) {
        Utils.debugLog.debug("Hello client");
        Device cli = new Device(Constants.portNumber, Integer.valueOf(args[0]));
        (new Thread(new ClientListener(cli))).start();
        (new Thread(new ClientSender(cli))).start();
    }
}

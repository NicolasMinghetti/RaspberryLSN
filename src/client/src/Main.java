/**
 * Created by nicolas on 11/04/17.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here

        System.out.println("Hello Client");
        Client cli = new Client(Constants.getNetAddr(), Constants.portNumber, Integer.valueOf(args[0]));
        (new Thread(new ClientListener(cli))).start();
        (new Thread(new ClientSender(cli))).start();
    }
}

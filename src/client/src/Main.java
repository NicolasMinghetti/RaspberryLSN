/**
 * Created by nicolas on 11/04/17.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here

        System.out.println("Hello Client");
        Client cli = new Client(Constants.getNetAddr(), Constants.portNumber);
        ClientListener listener = new ClientListener(cli);
        listener.run();


    }
}

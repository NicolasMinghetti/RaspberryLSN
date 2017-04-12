
/**
 * Created by nicolas on 11/04/17.
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello server");

        initialization();
    }

    /**
     * This function initializes the gradient network
     */
    public static void initialization() {
        Utils.broadcast(666, 0);
    }
}

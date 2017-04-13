import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;

/**
 * This file contains constants values
 * Created by nicolas on 12/04/17.
 */
public class Constants {


    private static JSONObject initializeJsonObject() {
        File f = new File("constants.json");
        try {
            byte[] bytes = Files.readAllBytes(f.toPath());
            return new JSONObject(new String(bytes,"UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    static JSONObject json = initializeJsonObject();

    static int portNumber = json.getInt("portNumber");  // port number for sent messages

    static String networkAddress = json.getString("ipAdress"); // broadcast address for local network

    static int messageLength = json.getInt("messageLength"); // max length for transmitted messages


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

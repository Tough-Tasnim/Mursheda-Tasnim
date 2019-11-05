package tcpstring;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import util.NetworkUtil;

import static backup.Gamefx.printScore;
import static backup.Main.stage;
import static javafx.application.Application.launch;

public class Client {


    private NetworkUtil nc;
    public Client(String serverAddress, int serverPort) {
        try {
            nc = new NetworkUtil(serverAddress, serverPort);
            new ReadThread(nc);
            new WriteThread(nc, "Client");
            //label.setText(nc.read().toString());
        } catch (Exception e) {
            System.out.println(e);
        }finally {
            nc.closeConnection();
        }
    }


    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        Client client = new Client(serverAddress, serverPort);
    }
}


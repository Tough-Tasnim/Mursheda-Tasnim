package backup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import networking.HighScore;
import tcpstring.Server;

import java.util.ArrayList;



public class Main extends Application{

    public static Stage stage;
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showStartPage();
    }

    public void showStartPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Start.fxml"));
        Parent root = loader.load();

        // Loading the controller
        startController controller = loader.getController();

        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("FRUIT NINJA");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showINFOPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("info.fxml"));
        Parent root = loader.load();

        // Loading the controller
        INFOController controller = loader.getController();
       // controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("info");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showNewGamePage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("newgame.fxml"));
        Parent root = loader.load();

        // Loading the controller
        newGameController controller = loader.getController();
       controller.setMain(this);

        // Set the primary stage
        stage.setTitle("FRUIT NINJA");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showHelpPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("help.fxml"));
        Parent root = loader.load();

        // Loading the controller
        helpController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Fruit Ninja/help");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showFinalGame() throws Exception{
        Gamefx gamefx=new Gamefx();
        gamefx.finalGame();
    }




    public static void main(String[] args) {
        // This will launch the JavaFX application

        launch(args);
    }

}

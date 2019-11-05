package backup;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tcpstring.Client;
import tcpstring.ReadThread;
import tcpstring.Server;
import tcpstring.WriteThread;
import util.NetworkUtil;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static backup.Gamefx.printScore;
import static backup.Main.stage;

public class SeeScore extends Application {
    private static final String overURL="backup/gameOver.jpg";
    private static final String leaderboard="backup/leaderboard.jpg";
    public Label[] labelb=new Label[20];
    public Label[] labeld=new Label[20];


    public void start(Stage primaryStage) throws Exception{
        //Server server = new Server();
        stage=primaryStage;
        seeScore();
    }

    public void seeScore(){
        ImageView bg = new ImageView(overURL);
        bg.setFitHeight(500);
        bg.setFitWidth(600);
        Pane root = new Pane(bg);
        Scene scene = new Scene(root, 600, 500);
        //scene.setFill(new ImagePattern(bg, 0, 0, 1, 1, true));
        Label label=new Label();
        label.setTextFill(Color.BLACK);
        label.setText("SCORE: "+printScore.toString());
        label.setFont(Font.font(60));
        label.setLayoutX(150);
        label.setLayoutY(310);
        root.getChildren().add(label);

        Button playAgain=new Button("Play Again");
        playAgain.setLayoutX(50);
        playAgain.setLayoutY(420);
        playAgain.setFont(Font.font(24));
        playAgain.setTextFill(Color.BLACK);
        playAgain.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                Main main=new Main();
                try {
                    main.showFinalGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button leaderBoard=new Button("Leader Board");
        leaderBoard.setLayoutX(350);
        leaderBoard.setLayoutY(420);
        leaderBoard.setFont(Font.font(24));
        leaderBoard.setTextFill(Color.BLACK);
        leaderBoard.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {


//                String serverAddress = "127.0.0.1";
//                int serverPort = 33333;
//                //NetworkUtil nc=new NetworkUtil(serverAddress,serverPort)
//                Client client = new Client(serverAddress, serverPort);
//                client.showLeaderboard();
                showLeaderBoard();

                }


        });
        root.getChildren().add(playAgain);
        root.getChildren().add(leaderBoard);

        stage.setTitle("Score");
        stage.setScene(scene);
        stage.show();


    }

    public void showLeaderBoard(){
        ImageView lboard = new ImageView(leaderboard);
        lboard.setFitHeight(600);
        lboard.setFitWidth(600);
        Pane root = new Pane(lboard);
        Scene scene = new Scene(root, 600, 500);
        //scene.setFill(new ImagePattern(bg, 0, 0, 1, 1, true));

        Label label=new Label();
        label.setTextFill(Color.BLACK);
        label.setText("Leader Board");
        label.setFont(Font.font(48));
        label.setLayoutX(165);
        label.setLayoutY(10);
        root.getChildren().add(label);


        NetworkUtil nc=new NetworkUtil("127.0.0.1",33333);
        int i=0;
        int j=0;
        int lx=170;
        int ly=100;
        int rx=360;
        int ry=100;
        try {
            while (true) {
                String s=(String) nc.read();
                String[] str=s.split(":");
                if (s.equals(null))
                    break;
                else {
                    labelb[i]=new Label();
                    labelb[i].setText(str[0]);
                    labelb[i].setTextFill(Color.BLACK);
                    //label.setText("SCORE: "+printScore.toString());
                    labelb[i].setFont(Font.font(28));
                    labelb[i].setLayoutX(lx);
                    labelb[i].setLayoutY(ly);
                    root.getChildren().add(labelb[i]);
                    labeld[j]=new Label();
                    labeld[j].setText(str[1]);
                    labeld[j].setTextFill(Color.BLACK);
                    //label.setText("SCORE: "+printScore.toString());
                    labeld[j].setFont(Font.font(28));
                    labeld[j].setLayoutX(rx);
                    labeld[j].setLayoutY(ry);
                    root.getChildren().add(labeld[j]);
                    i++;
                    j++;
                    ly+=30;
                    ry+=30;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            nc.closeConnection();
        }

        Button back=new Button("Back");
        back.setLayoutX(20);
        back.setLayoutY(450);
        back.setFont(Font.font(20));
        back.setTextFill(Color.BLACK);
        back.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                seeScore();
            }
        });
        root.getChildren().add(back);

        stage.setTitle("Leader Board");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

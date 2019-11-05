package backup;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import networking.FileCreation;
import networking.HighScore;


import javax.swing.plaf.basic.BasicTreeUI;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static backup.Bomb.bombs;
import static backup.Fruit.fruitimg;
import static backup.Fruit.power;
import static backup.Main.stage;
import  static backup.INFOController.name;

public class Gamefx extends Application {




    public static Integer score=0;
    public static Integer printScore=0;


    public int fx=30;
    public int fy=560;
    public int tfy=500;
    public int px=60;
    public int py=760;
    public int tpy=700;
    public int bx=80;
    public int by=760;
    public int tby=700;
    public int tf=6;
    public int tp=7;
    public int tb=5;
    final Integer startTime=0;
    Integer second=startTime;
    double t1=0;

    public Gamefx() throws IOException {
    }

    //Stage stage;
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        finalGame();

    }

    private static final String bomb1="backup/Bomb.png";
    private static final String bomb2="backup/-10_Bomb.png";

    private static final String dalim="backup/dalim.png";
    private static final String dragon="backup/Dragonfruit.png";
    private static final String starfruit="backup/Starfruit.png";

    private static final String backgroundURL="backup/background.jpg";


    private static final String coconut="backup/Coconut.png";
    private static final String mango="backup/Mango.png";
    private static final String orange="backup/Orange.png";
    private static final String pineapple="backup/Pineapple.png";
    private static final String strawberry="backup/Strawberry1.png";
    private static final String wmelon="backup/watermelon.jpg";
    private static final String kiwi="backup/Kiwi_Fruit.png";
    private static final String tomato="backup/Tomato.png";

    private static final String fplus= "backup/25.bmp";
    private static final String pplus= "backup/50.bmp";
    private static final String bminus= "backup/-10_Bomb.png";

    public Timeline time=new Timeline();
    public Timeline time1=new Timeline();
    public Timeline time2=new Timeline();




    public void finalGame() throws Exception {


        //Stage primaryStage=new Stage();
        ImageView bg = new ImageView(backgroundURL);
        bg.setFitHeight(500);
        bg.setFitWidth(600);
        Pane root = new Pane(bg);
        Scene scene = new Scene(root, 600, 500);
        //scene.setFill(new ImagePattern(bg, 0, 0, 1, 1, true));
        Label label=new Label();
        label.setTextFill(Color.RED);
        label.setText("SCORE: "+score.toString());
        label.setFont(Font.font(28));
        label.setLayoutX(15);
        label.setLayoutY(15);
        root.getChildren().add(label);

        Label label1=new Label();
        label1.setTextFill(Color.RED);
        //label1.setText("time ");
        label1.setFont(Font.font(28));
        label1.setLayoutX(520);
        label1.setLayoutY(15);
//        HBox layout =new HBox(5);
//        layout.getChildren().add(label);
        root.getChildren().add(label1);
        stage.setTitle("Fruit Ninja");
        stage.setScene(scene);


        {

            time.setCycleCount(Timeline.INDEFINITE);
            if (time!=null){
                time.stop();
            }
            KeyFrame frame=new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    second++;
                    if (second<10)
                        label1.setText("00" + " : 0" +second.toString());
                    label1.setText("00" + " : " +second.toString());
                    if (second==31){
                        time.stop();
                        printScore=score;


                        try {
                            ArrayList<HighScore>highScores=new ArrayList<>();
                            try (BufferedReader br = new BufferedReader(new FileReader("SortedScore.txt"))) {
                                while (true) {
                                    String s = br.readLine();
                                    if (s==null) break;
                                    String[] sc=s.split(":");
                                    highScores.add(new HighScore(sc[0],Integer.parseInt(sc[1])));
                                }
                            } catch(IOException e) {
                                e.printStackTrace();
                            }

                            highScores.add(new HighScore(name,printScore));
                            sorting(highScores);
                            FileWriter fw = new FileWriter("SortedScore.txt");
                            BufferedWriter bw = new BufferedWriter(fw);
                            for (int i=0;i<highScores.size();i++){
                                System.out.println(highScores.get(i).getName()+"    "+highScores.get(i).getScore());
                                bw.append(highScores.get(i).getName());
                                bw.append(":"+highScores.get(i).getScore());
                                bw.newLine();
                            }

                            bw.close();
                            fw.close();
                            //Thread.sleep(500);
                            score=0;
                            SeeScore sScore=new SeeScore();
                            sScore.seeScore();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            });
            time.getKeyFrames().add(frame);
            time.playFromStart();
        }

        Fruit fruit = new Fruit(fruitimg, power);

        fruitimg.add(wmelon);
        fruitimg.add(coconut);
        fruitimg.add(mango);
        fruitimg.add(orange);
        fruitimg.add(pineapple);
        fruitimg.add(strawberry);
        fruitimg.add(kiwi);
        fruitimg.add(tomato);
        fruitimg.add(wmelon);
        fruitimg.add(coconut);
        fruitimg.add(mango);
        fruitimg.add(orange);
        fruitimg.add(pineapple);
        fruitimg.add(strawberry);
        fruitimg.add(kiwi);
        fruitimg.add(tomato);
        fruitimg.add(wmelon);
        fruitimg.add(coconut);
        fruitimg.add(mango);
        fruitimg.add(orange);
        fruitimg.add(pineapple);
        fruitimg.add(strawberry);
        fruitimg.add(kiwi);
        fruitimg.add(tomato);
        fruitimg.add(tomato);
        fruitimg.add(wmelon);
        fruitimg.add(coconut);
        fruitimg.add(mango);
        fruitimg.add(orange);
        fruitimg.add(pineapple);
        fruitimg.add(strawberry);
        fruitimg.add(kiwi);
        fruitimg.add(tomato);
        fruitimg.add(coconut);
        fruitimg.add(mango);
        fruitimg.add(orange);
        fruitimg.add(pineapple);
        fruitimg.add(strawberry);
        fruitimg.add(kiwi);
        fruitimg.add(tomato);

        power.add(dalim);
        power.add(dragon);
        power.add(starfruit);
        power.add(dalim);
        power.add(dragon);
        power.add(starfruit);
        power.add(dalim);
        power.add(dragon);
        power.add(starfruit);

        Bomb b = new Bomb(bombs);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);
        bombs.add(bomb1);


        for (int i = 0; i < fruitimg.size(); i++) {
            int j=i;
            Circle cir = new Circle();
            Circle c=new Circle();
            c.setRadius(0);
            c.setLayoutX(fx);
            c.setLayoutY(fy);
            cir.setRadius(30);
            cir.setLayoutX(fx);
            cir.setLayoutY(fy);
            Image f = new Image(fruitimg.get(i));
            Image fsmash=new Image(fplus);
            cir.setFill(new ImagePattern(f, 0, 0, 1, 1, true));

            if(fx>510){
                fx=60;
                tf=6;
            }
            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(tf));
            transition.setToX(30);
            transition.setToY(-tfy);
            transition.setAutoReverse(true);
            transition.setCycleCount(Animation.INDEFINITE);
            transition.setNode(cir);
            transition.play();
            root.getChildren().add(cir);

            TranslateTransition transition1 = new TranslateTransition();
            transition1.setDuration(Duration.seconds(tf));
            transition1.setToX(30);
            transition1.setToY(-tfy);
            transition1.setAutoReverse(true);
            transition1.setCycleCount(Animation.INDEFINITE);
            transition1.setNode(c);
            transition1.play();
            root.getChildren().add(c);



            cir.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    score+=25;
                    cir.setRadius(0);
                   label.setText("SCORE: "+score.toString());

                   c.setRadius(30);
                    c.setFill(new ImagePattern(fsmash,0,0,1,1,true));

                    {

                        time1.setCycleCount(Timeline.INDEFINITE);
                        if (time1!=null){
                            time1.stop();
                        }
                        KeyFrame frame=new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                t1+=0.001;
                                if (t1==0.001){
                                    c.setRadius(0);
                                }
                                t1=startTime;
                            }
                        });
                        time1.getKeyFrames().add(frame);
                        time1.playFromStart();
                    }

                }

            });

            fx += 60;
            fy+=250;
            tfy+=250;
            tf+=3;

        }


        for (int i = 0; i < power.size(); i++) {
            Circle cir = new Circle();

            cir.setRadius(60);
            Circle c=new Circle();
            c.setRadius(0);
            c.setLayoutX(px);
            c.setLayoutY(py);
            cir.setLayoutX(px);
            cir.setLayoutY(py);
            Image pf = new Image(power.get(i));
            Image psmash =new Image(pplus);
            cir.setFill(new ImagePattern(pf, 0, 0, 1, 1, true));
            if (px>450){
                px=130;
                tp=6;
            }
            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(tp));
            transition.setToX(30);
            transition.setToY(-tpy);
            transition.setAutoReverse(true);
            transition.setCycleCount(Animation.INDEFINITE);
            transition.setNode(cir);
            transition.play();
            root.getChildren().add(cir);

            TranslateTransition transition1 = new TranslateTransition();
            transition1.setDuration(Duration.seconds(tp));
            transition1.setToX(30);
            transition1.setToY(-tpy);
            transition1.setAutoReverse(true);
            transition1.setCycleCount(Animation.INDEFINITE);
            transition1.setNode(c);
            transition1.play();
            root.getChildren().add(c);

            cir.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    score+=50;
                    label.setText("SCORE: "+score.toString());
                    cir.setRadius(0);
                    c.setRadius(60);
                    c.setFill(new ImagePattern(psmash,0,0,1,1,true));
                    {

                        time1.setCycleCount(Timeline.INDEFINITE);
                        if (time1!=null){
                            time1.stop();
                        }
                        KeyFrame frame=new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                t1+=0.001;
                                if (t1==0.001){
                                    c.setRadius(0);
                                }
                                t1=startTime;
                            }
                        });
                        time1.getKeyFrames().add(frame);
                        time1.playFromStart();
                    }

                }

            });

            px += 100;
            py+=500;
            tpy+=500;
            tp+=5;
        }



        for (int i = 0; i < bombs.size(); i++) {
            Circle cir = new Circle();

            cir.setRadius(30);
            Circle c=new Circle();
            c.setRadius(0);
            c.setLayoutX(bx);
            c.setLayoutY(by);
            cir.setLayoutX(bx);
            cir.setLayoutY(by);
            Image bmb = new Image(bombs.get(i));
            Image blust=new Image(bminus);
            cir.setFill(new ImagePattern(bmb, 0, 0, 1, 1, true));

            if (bx>=510){
                bx=30;
            }
            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(tb));
            transition.setToX(30);
            transition.setToY(-tby);
            transition.setAutoReverse(true);
            transition.setCycleCount(Animation.INDEFINITE);
            transition.setNode(cir);
            transition.play();
            root.getChildren().add(cir);

            TranslateTransition transition1 = new TranslateTransition();
            transition1.setDuration(Duration.seconds(tb));
            transition1.setToX(30);
            transition1.setToY(-tby);
            transition1.setAutoReverse(true);
            transition1.setCycleCount(Animation.INDEFINITE);
            transition1.setNode(c);
            transition1.play();
            root.getChildren().add(c);
            cir.setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        score -= 10;
                        label.setText("SCORE: " + score.toString());
                        cir.setRadius(0);
                        c.setRadius(30);
                        c.setFill(new ImagePattern(blust, 0, 0, 1, 1, true));
                        {

                            time2.setCycleCount(Timeline.INDEFINITE);
                            if (time2 != null) {
                                time2.stop();
                            }
                            KeyFrame frame = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    t1 += 0.001;
                                    if (t1 == 0.001) {
                                        c.setRadius(0);
                                    }
                                    t1 = startTime;
                                }
                            });
                            time2.getKeyFrames().add(frame);
                            time2.playFromStart();
                        }
                    }catch (Exception e){

                    }

                }

            });
            bx += 80;
            by+=140;
            tby+=140;
            tb+=1;
        }

        stage.show();


    }


    public void sorting(ArrayList<HighScore>hScore){
//        Collections.sort(hScore, new Comparator<HighScore>() {
//            @Override
//            public int compare(HighScore o1, HighScore o2) {
//                return o1.getScore()-o2.getScore();
//            }
//        });
        Collections.sort(hScore, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore o1, HighScore o2) {
                return o2.getScore()-o1.getScore();
            }
        });

    }



    public static void main(String[] args)throws Exception {
        launch(args);
    }
}



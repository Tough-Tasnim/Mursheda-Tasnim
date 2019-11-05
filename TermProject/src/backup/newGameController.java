package backup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


import static javafx.application.Application.launch;

public class newGameController{
    private Main main;
    private Stage stage;


    public void backClicked(ActionEvent event) throws Exception{
        main.showINFOPage();

    }

    public void playClicked(ActionEvent event) throws Exception{
        main.showFinalGame();
    }

    public void helpClicked(ActionEvent event) throws Exception{
        main.showHelpPage();
    }

    void setMain(Main main) {
        this.main = main;
    }



}

package backup;


import javafx.event.ActionEvent;

public class helpController {
    private Main main;

    public void backClicked(ActionEvent event) throws Exception{
        main.showNewGamePage();
    }
    void setMain(Main main){this.main=main;}
}

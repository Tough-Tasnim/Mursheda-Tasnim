package backup;

import javafx.event.ActionEvent;

public class startController {
    private Main main;

    public void startClicked(ActionEvent event) throws Exception{
        main.showINFOPage();
    }

    void setMain(Main main) {
        this.main = main;
    }

}

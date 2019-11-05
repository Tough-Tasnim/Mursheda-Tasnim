package backup;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;

import java.io.FileWriter;


public class INFOController {
private Main main;


    @FXML
    private TextField nameText;
    @FXML
   private PasswordField passwordText;

    public static String name;
    public static String password;
    @FXML
    private Button goButton;
    private Button signButton;
  //  String validUserName = "admin";
    //String validPassword = "123";



        @FXML
        public void LoginClicked(ActionEvent event) throws Exception{
            name = nameText.getText();
            password=passwordText.getText();

            String validUserName1="tasnim";
            String validUserName2="urmi";

            String validPassWord1="123";
            String validPassword2="234";

        if ((name.equals(validUserName1)&&password.equals(validPassWord1))||(name.equals(validUserName2)&&password.equals(validPassword2))){

                // successful login
                try {
                    main.showNewGamePage();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("Please enter your name and password correctly");
            alert.showAndWait();

        }

        }




    public void setMain(Main main) {
        this.main = main;
    }

}

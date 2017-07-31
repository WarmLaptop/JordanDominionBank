package JordanDominionBank.view;

import JordanDominionBank.util.Database;
import JordanDominionBank.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by Jordan on 7/19/2017.
 */

//Controller for Login.fxml.
public class Controller {

    // fx:id's that are used inside of Login.fxml.
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label error;

    //This initializes the login button as disabled in the start of the program.
    public void initialize(){
        login.setDisable(true);
    }


    @FXML
    private void onButtonClicked(){
        System.out.println(username.getText());
    }

    //This methods will enable the login button when the input fields are filled.
    @FXML
    private void checkFields(){
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            login.setDisable(true);
        } else{
            login.setDisable(false);
        }
    }

    // Authenticating the login info. If both the username and the password exists in the database, a new Account scene will appear.
    @FXML
    private void login() {
        if(username.getText().equals(Database.getUsername()) &&
                password.getText().equals(Database.getPassword())){
            try{
                //A simple way of changing scenes by swapping out the scene root, rather than changing the pane of the scene.
                Parent root = FXMLLoader.load(getClass().getResource("Account.fxml"));
                Scene Account = new Scene(root,500,400);
                Main.getTheStage().setScene(Account);
            }
            catch (Exception e){
                System.out.println("error");
                //System.out.println(e.getStackTrace());
            }
        } else{
            error.setText("You are a fraud. GTFO!");
        }

    }

}

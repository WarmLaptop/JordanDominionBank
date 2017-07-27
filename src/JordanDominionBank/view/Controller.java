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
public class Controller {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label error;

    public void initialize(){
        login.setDisable(true);
    }

    @FXML
    private void onButtonClicked(){
        System.out.println(username.getText());
    }
    @FXML
    private void checkFields(){
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            login.setDisable(true);
        } else{
            login.setDisable(false);
        }
    }

    @FXML
    private void login() {
        if(username.getText().equals(Database.getUsername()) &&
                password.getText().equals(Database.getPassword())){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("view/Account.fxml"));
                Scene Account = new Scene(root,500,400);
                Main.getTheStage().setScene(Account);}
            catch (Exception e){
                System.out.println(e.getStackTrace());
            }
        } else{
            error.setText("You are a fraud. GTFO!");
        }

    }

}

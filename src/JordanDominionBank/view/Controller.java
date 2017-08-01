package JordanDominionBank.view;

import JordanDominionBank.util.Database;
import JordanDominionBank.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

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
    //fx:id's that are used inside the SignUp.fxml.
    @FXML
    Button signUp;



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
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/Account.fxml"));
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

    @FXML
    private void signUp() throws IOException{
        Dialog<ButtonType> newUser = new Dialog<>();
        newUser.initOwner(Main.getTheStage());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("NewUser.fxml"));

        try{
            newUser.getDialogPane().setContent(fxmlLoader.load());}
        catch (IOException e){}

        newUser.getDialogPane().getButtonTypes().add(ButtonType.OK);
        newUser.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = newUser.showAndWait();

        if(result.get() == ButtonType.OK && result.isPresent()){
            //NewUser class becomes the controller that contains the fx:id values.
            NewUser controller = fxmlLoader.getController();
            do{
                if(controller.newPassword.getText().equals(controller.newPasswordConf.getText())){
                    //put the new username and password into the database.
                    //Path path = Paths.get("C:\\Users\\Jordan\\Desktop\\NuPhysics\\Final Project\\src\\JordanDominionBank\\util\\UsernameAndPassword.txt");
                    try{
                        FileWriter fw = new FileWriter("C:\\\\Users\\\\Jordan\\\\Desktop\\\\NuPhysics\\\\Final Project\\\\src\\\\JordanDominionBank\\\\util\\\\UsernameAndPassword.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(String.format("%s,%s", controller.newUsername.getText(), controller.newPassword.getText()));
                        bw.newLine();
                        bw.close();


                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                    break;
                }
                else{
                    controller.pwMismatch.setText("Password not identical. Please enter your new password again.");
                    controller.newPassword.clear();
                    controller.newPasswordConf.clear();
                }
            } while(true);
        }

    } //End of signUp method.

}



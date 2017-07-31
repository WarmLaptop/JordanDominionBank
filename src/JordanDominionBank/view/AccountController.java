package JordanDominionBank.view;

import JordanDominionBank.*;
import JordanDominionBank.model.Account;
import JordanDominionBank.model.ChequingAccount;
import JordanDominionBank.model.SavingsAccount;
import JordanDominionBank.util.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Jordan on 7/20/2017.
 */
public class AccountController {
    private User user;

    //fx:id's for the fxml file.
    @FXML
    ListView<Account> accounts;

    @FXML
    TextArea accountInfo;

    //Initializes the account by getting the user info from the database class.
    //The account retrieves all items from the user info.
    public void initialize(){
        user = Database.getUser();
        accounts.getItems().addAll(user.getAccounts());
    }

    @FXML
    private void showAddAccount(){
        //New dialog:
        Dialog<ButtonType> addAccountDialog = new Dialog<>();
        addAccountDialog.initOwner(Main.getTheStage());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddAccount.fxml"));
        try{
            addAccountDialog.getDialogPane().setContent(fxmlLoader.load());}
        catch (IOException e){}

        addAccountDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        addAccountDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = addAccountDialog.showAndWait();

        if(result.get() == ButtonType.OK && result.isPresent()){
            AddAccount controller = fxmlLoader.getController();
            if(controller.accountType.getValue().equals("Saving")){
                user.addAccount(new SavingsAccount());
            } else if(controller.accountType.getValue().equals("Chequing")){
                user.addAccount(new ChequingAccount());
            }
        }
        updateListView();
    }

    @FXML
    private void showAccountInfo(){
        accountInfo.setText("balance: " + accounts.getSelectionModel().getSelectedItem().getBalance());

    }

    @FXML
    private void deleteAccount(){
        user.getAccounts().remove(accounts.getSelectionModel().getSelectedItem());
        updateListView();
    }

    private void updateListView(){
        accounts.getItems().clear();
        accounts.getItems().addAll(user.getAccounts());
    }

    @FXML
    private void logOut(){
        Main.getTheStage().setTitle("Login");
        Main.getTheStage().setScene(Main.getLoginScene());
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            Main.getLoginScene().setRoot(fxmlLoader.load(getClass().getResource("Login.fxml")));
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }

}

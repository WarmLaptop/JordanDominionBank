package JordanDominionBank.view;

import JordanDominionBank.*;
import JordanDominionBank.model.Account;
import JordanDominionBank.model.ChequingAccount;
import JordanDominionBank.model.SavingsAccount;
import JordanDominionBank.util.Database;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Jordan on 7/20/2017.
 */
public class AccountController {
    private User user;

    //fx:id's for the Account.fxml file.
    @FXML
    ListView<Account> accounts;
    @FXML
    TextArea accountInfo;

    @FXML
    TextField withdrawAmount;

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
        fxmlLoader.setLocation(getClass().getResource("../fxml/AddAccount.fxml"));
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
//        else {
//            AddAccount controller = fxmlLoader.getController();
//            controller.OK.setDisable(true);
//        }
        updateListView();
    }

    @FXML
    private void showAccountInfo(){
        try{accountInfo.setText("balance: " + accounts.getSelectionModel().getSelectedItem().getBalance());}
        catch(NullPointerException e){
            accountInfo.setText("");
        }
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
    private void deposit(){
        //New dialog needs a new stage, with a new scene.
        Dialog<ButtonType> depositDialog = new Dialog<>();
        depositDialog.initOwner(Main.getTheStage());
        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(getClass().getResource("../fxml/Deposit.fxml"));
        try{
            depositDialog.getDialogPane().setContent(fxmlLoader.load());

        }

        catch (IOException e){}

        depositDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        depositDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Deposit controller = fxmlLoader.getController();

        // Force the deposit text field to be numeric value only.
        controller.depositAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    controller.depositAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Optional<ButtonType> result = depositDialog.showAndWait();


        if(result.get() == ButtonType.OK && result.isPresent()){
            //Deposit controller = fxmlLoader.getController();
            accounts.getSelectionModel().getSelectedItem().deposit(Integer.parseInt(controller.depositAmount.getText()));
        }
    }

    @FXML
    private void withdraw(){
        //New dialog needs a new stage, with a new scene.
        Dialog<ButtonType> withdrawDialog = new Dialog<>();
        withdrawDialog.initOwner(Main.getTheStage());
        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(getClass().getResource("../fxml/Withdraw.fxml"));
        try{
            withdrawDialog.getDialogPane().setContent(fxmlLoader.load());}

        catch (IOException e){}

        withdrawDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        withdrawDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Withdraw controller = fxmlLoader.getController();

        // Force the withdraw text field to be numeric value only.
        controller.withdrawAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    withdrawAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Optional<ButtonType> result = withdrawDialog.showAndWait();

        if(result.get() == ButtonType.OK && result.isPresent()){
            accounts.getSelectionModel().getSelectedItem().withdraw(Integer.parseInt(controller.withdrawAmount.getText()));
        }
    }

    @FXML
    private void logOut(){
        Main.getTheStage().setTitle("Login");
        Main.getTheStage().setScene(Main.getLoginScene());
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            Main.getLoginScene().setRoot(fxmlLoader.load(getClass().getResource("../fxml/Login.fxml")));
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
}

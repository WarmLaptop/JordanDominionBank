package JordanDominionBank;

import JordanDominionBank.util.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Jordan on 7/19/2017.
 */

public class Main extends Application {

    private static Stage theStage;
    private static Scene loginScene;

    //Override the start method in the Application parent class.
    @Override
    public void start(Stage primaryStage) throws Exception{
        theStage = primaryStage;
        //The Login.fxml becomes the loader for the parent
        Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        //With the login fxml, a new Scene is instantiated using the declared root.
        loginScene = new Scene(root, 300, 275);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    //Launch a standalone application. launch method of the extended Application class is executed.
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getTheStage(){
        return theStage;
    }

    public static Scene getLoginScene() {
        return loginScene;
    }

    @Override
    public void init() throws Exception {
        Database.load();
    }

    @Override
    public void stop() throws Exception {
        Database.save();
    }
}
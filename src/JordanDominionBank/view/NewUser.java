package JordanDominionBank.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class NewUser {
    @FXML
    TextField newUsername;
    @FXML
    PasswordField newPassword;
    @FXML
    PasswordField newPasswordConf;
    @FXML
    Label pwMismatch;
}

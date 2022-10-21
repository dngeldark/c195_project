package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.jdbc.LoginDao;
import sample.models.AppState;
import sample.models.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginFormController implements Initializable {
    public TextField usernameField;
    public TextField passwordField;
    public Label zoneLabel;
    public Button loginBtn;

    public void onLogin(ActionEvent actionEvent) throws IOException {
        String username = usernameField.getText();
        String passwrd = passwordField.getText();
        User user = LoginDao.getUserPsswd(username,passwrd);
        if (user != null) {
            openMainPage();
            AppState.setUser(user);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        zoneLabel.setText(localZoneId.toString());
    }

    private void openMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/sample.fxml"));
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("Customer Form");
        stage.setScene(scene);
    }


}

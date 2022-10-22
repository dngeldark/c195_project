package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.jdbc.LoginDao;
import sample.models.AppState;
import sample.models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginFormController implements Initializable {
    public TextField usernameField;
    public TextField passwordField;
    public Label zoneLabel;
    public Button loginBtn;

    private void displayAlert(String message){
        Alert selectAppt = new Alert(Alert.AlertType.INFORMATION);
        selectAppt.setContentText(message);
        selectAppt.setHeaderText(null);
        selectAppt.show(); }

    public void onLogin(ActionEvent actionEvent) throws IOException {
        if(passwordField.getText().isEmpty()
                || usernameField.getText().isEmpty()){
            displayAlert("Enter a username and password");
        }

        String filename = "login_activity.txt";
        FileWriter fwriter = new FileWriter(filename,true);
        PrintWriter outputfile = new PrintWriter(fwriter);
        String username = usernameField.getText();
        String passwrd = passwordField.getText();
        User user = LoginDao.getUserPsswd(username,passwrd);
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        String success = "unsuccessful";

        if (user != null) {
            openMainPage();
            AppState.setUser(user);
            success = "successful";
        }

        if(!username.isEmpty()) outputfile.println(username+" "+ " " + ld + " " + lt + " " + success + " login");
        outputfile.close();
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

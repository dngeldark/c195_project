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
import sample.interfaces.Translatable;
import sample.jdbc.LoginDao;
import sample.models.AppState;
import sample.models.User;
import sample.models.UtilityLists;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

/** Controls the login form.*/
public class LoginFormController implements Initializable {
    public TextField usernameField;
    public TextField passwordField;
    public Label zoneLabel;
    public Button loginBtn;
    public Label usernameLabel;
    public Label passLabel;
    public Label loginLabel;

    /** Implements the Translatable interface
     *  Using this lamba expression makes code easier to read and reuse
     */
    Translatable resourceBundle = (filePath) -> ResourceBundle.getBundle(filePath,Locale.getDefault());
    /** Uses the lamba expression for the Translatable interface.*/
    ResourceBundle rb = resourceBundle.resource("sample/resources/Nat");

    /** Display alert
     *
     * @param message custom message for the alert
     */
    private void displayAlert(String message){
        Alert selectAppt = new Alert(Alert.AlertType.INFORMATION);
        selectAppt.setContentText(message);
        selectAppt.setHeaderText(null);
        selectAppt.show(); }

    /** handles the click even on the login button
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onLogin(ActionEvent actionEvent) throws IOException {
        String username = usernameField.getText();
        String passwrd = passwordField.getText();
        if (username.isEmpty()){
            displayAlert(rb.getString("enterUser"));
            fileLogger("No username","unsuccessful");
            return;
        }

        if(passwrd.isEmpty()){
            displayAlert(rb.getString("enterPass"));
            fileLogger(username,"unsuccessful");
            return;
        }

        User user = LoginDao.getUserPsswd(username,passwrd);


        if (user != null) {
            openMainPage();
            AppState.setUser(user);
            fileLogger(username,"successful");
            String upcomingAppt = UtilityLists.apptAtLogin();
            displayAlert(upcomingAppt);
        }else {
            displayAlert(rb.getString("missmatch"));
            fileLogger(username,"unsuccessful");
        }


    }

    /** Initializes the login form
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        zoneLabel.setText(localZoneId.toString());
        passLabel.setText(rb.getString("password"));
        usernameLabel.setText(rb.getString("username"));
        loginLabel.setText(rb.getString("login"));
        loginBtn.setText(rb.getString("login"));

    }

    /** Open the main page
     *
     * @throws IOException
     */
    private void openMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/main.fxml"));
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.setX(300);
        stage.setY(50);
        Scene scene = new Scene(fxmlLoader.load(), 880, 450);
        stage.setTitle("Customer Form");
        stage.setScene(scene);
    }

    /** Logs the login information into a document
     *
     * @param username used to try to login
     * @param message successful or unsuccessful attempt message
     * @throws IOException
     */
    private void fileLogger(String username, String message) throws IOException {
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        String filename = "login_activity.txt";
        FileWriter fwriter = new FileWriter(filename,true);
        PrintWriter outputfile = new PrintWriter(fwriter);
        outputfile.println(username+" "+ " " + ld + " " + lt + " " + message + " login");
        outputfile.close();
    }


}

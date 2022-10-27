package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.jdbc.*;
import java.sql.SQLException;

/** Main Class.*/
public class Main extends Application {

    /** Starts application.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/loginForm.fxml"));
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }


    /** Main function
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        CountriesDao.setCountries();
        CountriesDao.setDivisions();
        CustomersDao.setCustomers();
        AppointmentsDao.setAppointments();
        ContactsDao.initContacts();
        launch(args);
        JDBC.closeConnection();
    }
}

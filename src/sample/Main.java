package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.jdbc.AppointmentsDao;
import sample.jdbc.CountriesDao;
import sample.jdbc.CustomersDao;
import sample.jdbc.JDBC;
import sample.models.Customer;
import sample.models.UtilityLists;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/sample.fxml"));
        primaryStage.setTitle("Appointments");
        primaryStage.setScene(new Scene(root, 900, 450));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        JDBC.makeConnection();
        CountriesDao.setCountries();
        CountriesDao.setDivisions();
        CustomersDao.setCustomers();
        AppointmentsDao.setAppointments();
        launch(args);
        JDBC.closeConnection();
    }
}

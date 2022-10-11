package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.jdbc.CustomersDao;
import sample.jdbc.JDBC;
import sample.models.Country;
import sample.models.CountryDivision;
import sample.models.Customer;
import sample.models.UtilityLists;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    public Button cancelBtn;
    public ComboBox<Country> countryBox;
    public ComboBox<CountryDivision> subDivisionBox;
    public TextField addyField;
    public TextField nameField;
    public TextField zipcodeField;
    public TextField phoneField;
    public Button addBtn;

    public void returnToMainScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/sample.fxml"));
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),630,400);
        stage.setTitle("Customer Form");
        stage.setScene(scene);
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        returnToMainScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryBox.setItems(UtilityLists.getCountries());
        countryBox.getSelectionModel().selectFirst();
        subDivisionBox.setItems(countryBox.getSelectionModel().getSelectedItem().getDivisionList());
        subDivisionBox.getSelectionModel().selectFirst();
    }

    public void onPull(javafx.event.ActionEvent actionEvent) {
        Country country = countryBox.getSelectionModel().getSelectedItem();
        subDivisionBox.setItems(country.getDivisionList());
        subDivisionBox.getSelectionModel().selectFirst();
    }

    public void onAdd(ActionEvent actionEvent) {
        int subdivisionId = subDivisionBox.getSelectionModel().getSelectedItem().divisionId();
        String name = nameField.getText();
        String addy = addyField.getText();
        String code = zipcodeField.getText();
        String phone = phoneField.getText();
        String subName = subDivisionBox.getSelectionModel().getSelectedItem().divisionName();

        try {
            CustomersDao.addCustomer(name,addy,code,phone,subdivisionId);
            int customerId = JDBC.getLastId();
            Customer customer = new Customer(name,addy,code,phone,subdivisionId,subName);
            customer.setCustomerId(customerId);
            UtilityLists.addCustomer(customer);
            try {
                returnToMainScreen();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

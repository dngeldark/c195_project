package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    public static Customer customerToModify = null;
    private final boolean modify = customerToModify != null;
    public Label title;

    public static void setCustomer(Customer customer ){
        customerToModify = customer;
    }


    public void returnToMainScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/sample.fxml"));
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),630,400);
        stage.setTitle("Customer Form");
        stage.setScene(scene);
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        returnToMainScreen();
        customerToModify = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryBox.setItems(UtilityLists.getCountries());
        countryBox.getSelectionModel().selectFirst();
        subDivisionBox.setItems(countryBox.getSelectionModel().getSelectedItem().getDivisionList());
        subDivisionBox.getSelectionModel().selectFirst();

        if(modify){
            title.setText("Modify Customer");
            title.setText("Modify Customer");
            nameField.setText(customerToModify.getName());
            addyField.setText(customerToModify.getAddress());
            phoneField.setText(customerToModify.getPhone());
            zipcodeField.setText(customerToModify.getPhone());
            CountryDivision division = customerToModify.getDivision();
            System.out.println(customerToModify.getDivision());
            countryBox.getSelectionModel().select(division.getCountry());
            subDivisionBox.setItems(division.getCountry().getDivisionList());
            subDivisionBox.getSelectionModel().select(division);
        }

    }

    public void onPull(javafx.event.ActionEvent actionEvent) {
        Country country = countryBox.getSelectionModel().getSelectedItem();
        subDivisionBox.setItems(country.getDivisionList());
        subDivisionBox.getSelectionModel().selectFirst();
    }

    private void addCustomer(String name, String addy, String code, String phone, int subId, String subName)
            throws IOException {
        try {
            CustomersDao.addCustomer(name,addy,code,phone,subId);
            int customerId = JDBC.getLastId();
            Customer customer = new Customer(name,addy,code,phone,subId,subName);
            customer.setCustomerId(customerId);
            customer.setDivision(subId);
            UtilityLists.addCustomer(customer);
            returnToMainScreen();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateCustomer(String name, String addy, String code, String phone, int subId, String subName)
            throws IOException {
        customerToModify.setName(name);
        customerToModify.setAddress(addy);
        customerToModify.setPostalCode(code);
        customerToModify.setPhone(phone);
        customerToModify.setDivisionId(subId);
        customerToModify.setSubdivision(subName);
        CustomersDao.updateCustomer(customerToModify);
        customerToModify = null;
        returnToMainScreen();
    }

    public void onAdd(ActionEvent actionEvent) throws IOException {

        String name = nameField.getText();
        String addy = addyField.getText();
        String code = zipcodeField.getText();
        String phone = phoneField.getText();
        String subName = subDivisionBox.getSelectionModel().getSelectedItem().divisionName();
        int subdivisionId = subDivisionBox.getSelectionModel().getSelectedItem().divisionId();

        if(modify){updateCustomer(name,addy,code,phone,subdivisionId,subName);}
        else{addCustomer(name,addy,code,phone,subdivisionId,subName);}

    }
}
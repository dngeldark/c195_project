package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Test;
import sample.models.UtilityLists;
import sample.models.Country;
import sample.models.CountryDivision;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ComboBox<Country> countryBox;
    public ComboBox<CountryDivision> divisionBox;
    public TableView customersTable;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn addyColumn;
    public TableColumn phoneColumn;
    public TableColumn stateColumn;
    public Button addBtn;
    public TableColumn zipColumn;


    private void populateTable(){
        customersTable.setItems(UtilityLists.getCustomers());
        nameColumn.setText("Name");
        idColumn.setText("ID");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addyColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("subdivision"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        populateTable();
        countryBox.setItems(UtilityLists.getCountries());
        countryBox.getSelectionModel().selectFirst();
        divisionBox.setItems(countryBox.getSelectionModel().getSelectedItem().getDivisionList());
        divisionBox.getSelectionModel().selectFirst();
    }


    public void onPull(javafx.event.ActionEvent actionEvent) {
        Country country = countryBox.getSelectionModel().getSelectedItem();
        divisionBox.setItems(country.getDivisionList());
        divisionBox.getSelectionModel().selectFirst();
    }

    public void addCustomer(ActionEvent actionEvent) throws IOException {
        openCustomerForm();
    }

    public void modifyCustomer(ActionEvent actionEvent) throws IOException {
        openEditCustomerForm();
    }

    public void deleteCustomer(ActionEvent actionEvent) {
    }





    private void openCustomerForm() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/customerForm.fxml"));
            Stage stage = (Stage) customersTable.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(),300,330);
            stage.setTitle("Customer Form");
            stage.setScene(scene);
    }

    private void openEditCustomerForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/editCustomerForm.fxml"));
        Stage stage = (Stage) customersTable.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),300,330);
        stage.setTitle("Customer Form");
        stage.setScene(scene);
    }

}

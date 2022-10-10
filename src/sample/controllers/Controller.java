package sample.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.models.Test;
import sample.models.UtilityLists;
import sample.models.Country;
import sample.models.CountryDivision;

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
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

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
}

package sample.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import sample.models.UtilityLists;
import sample.models.Country;
import sample.models.CountryDivision;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ComboBox<Country> countryBox;
    public ComboBox<CountryDivision> divisionBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

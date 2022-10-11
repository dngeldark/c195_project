package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Country;
import sample.models.CountryDivision;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerFormController implements Initializable {
    public Button cancelBtn;
    public ComboBox<Country> countryBox;
    public ComboBox<CountryDivision> subDivisionBox;

    public void cancel(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/sample.fxml"));
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),630,400);
        stage.setTitle("Customer Form");
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

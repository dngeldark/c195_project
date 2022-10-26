package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.jdbc.AppointmentsDao;
import sample.jdbc.CountriesDao;
import sample.jdbc.CustomersDao;
import sample.models.Contact;
import sample.models.UtilityLists;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** Controls the reports view. */
public class ReportsController implements Initializable {
    public TextArea textField;
    public Button closeBtn;
    public TableView scheduleTable;
    public ChoiceBox contactBox;
    public TableColumn apptId;
    public TableColumn apptTitle;
    public TableColumn apptDescription;
    public TableColumn apptType;
    public TableColumn apptStart;
    public TableColumn apptEnd;
    public TableColumn apptCustomerId;
    public TextArea countryReportField;

    /** Initializes the reports view
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryReportField.setText(CustomersDao.customersByCountry());
        textField.setText(AppointmentsDao.queryApptByMonth());
        contactBox.setItems(UtilityLists.getContactsList());
        contactBox.getSelectionModel().selectFirst();
        scheduleTable.setPlaceholder(new Label("No Appointments Found"));
    }

    /** Handle the click event on close button
     *
     * @param actionEvent
     */
    public void onCloseBtn(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/sample.fxml"));
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(),900,500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Customer Form");
        stage.setScene(scene);
    }

    /** handle the pull event on the conctacts selection box
     * 
     * @param actionEvent
     */
    public void onPull(ActionEvent actionEvent) {
        Contact contact = (Contact) contactBox.getSelectionModel().getSelectedItem();
        scheduleTable.setItems(UtilityLists.getApptsbyContact(contact.contactId()));
        apptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("startTimeString"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("endTimeString"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
}

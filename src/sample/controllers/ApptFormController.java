package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Appointment;
import sample.models.Contact;
import sample.models.UtilityLists;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApptFormController implements Initializable {
    public Button cancelBtn;
    public Button addBtn;
    public TextField idFiled;
    public TextField titleField;
    public TextField locField;
    public TextField descField;
    public TextField typeField;
    public ChoiceBox<Contact> contactBox;
    public DatePicker startPicker;
    public DatePicker endPicker;
    public static Appointment apptModify;
    private final boolean modify = apptModify != null;
    public Label title;

    public static void setAppointment(Appointment appt){apptModify = appt;}


    public void onCancel(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/sample.fxml"));
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),900,500);
        stage.setTitle("Customer Form");
        stage.setScene(scene);
        apptModify = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(modify){
            title.setText("Modify Appointment");
           idFiled.setText(String.valueOf(apptModify.getAppointmentId()));
           titleField.setText(apptModify.getTitle());
           locField.setText(apptModify.getLocation());
           descField.setText(apptModify.getDescription());
           typeField.setText(apptModify.getType());
           startPicker.setValue(apptModify.getStartTime().toLocalDate());
           endPicker.setValue(apptModify.getEndTime().toLocalDate());
           contactBox.setValue(UtilityLists.getContactById(apptModify.getContactId()));
        }

        contactBox.setItems(UtilityLists.getContactsList());
    }
}

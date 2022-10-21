package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.jdbc.AppointmentsDao;
import sample.jdbc.JDBC;
import sample.models.AppState;
import sample.models.Appointment;
import sample.models.Contact;
import sample.models.UtilityLists;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
    public static Appointment apptModify;
    private final boolean modify = apptModify != null;
    public static int customerId;

    public Label title;
    public ComboBox<LocalTime> startBox;
    public ComboBox<LocalTime> endBox;

    public static void setAppointment(Appointment appt){apptModify = appt;}
    public static void setCustomerId(int id){customerId = id;}
    public int getCustomerId(){return customerId;}



    private void closeForm(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/sample.fxml"));
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 900, 500);
            stage.setTitle("Customer Form");
            stage.setScene(scene);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    public void onCancel(ActionEvent actionEvent) {
        closeForm();
        apptModify = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTimeBoxes();
        contactBox.setValue(UtilityLists.getContactsList().get(0));
        startPicker.setValue(LocalDate.now());

        if(modify){
            customerId = apptModify.getCustomerId();
            addBtn.setText("update");
            title.setText("Modify Appointment");
            idFiled.setText(String.valueOf(apptModify.getAppointmentId()));
            titleField.setText(apptModify.getTitle());
            locField.setText(apptModify.getLocation());
            descField.setText(apptModify.getDescription());
            typeField.setText(apptModify.getType());
            startPicker.setValue(apptModify.getStartTime().toLocalDate());
            contactBox.setValue(UtilityLists.getContactById(apptModify.getContactId()));
            startBox.setValue(apptModify.getStartTime().toLocalTime());
            endBox.setValue(apptModify.getEndTime().toLocalTime());
        }


        contactBox.setItems(UtilityLists.getContactsList());
    }

    private void populateTimeBoxes(){

        LocalDate estDate = LocalDate.now();
        LocalTime estTime = LocalTime.of(8,00);
        ZoneId estZoneId = ZoneId.of("America/New_York");
        ZonedDateTime estZDT = ZonedDateTime.of(estDate,estTime,estZoneId);
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

        LocalTime lc = estZDT.withZoneSameInstant(localZoneId).toLocalTime();

        LocalTime start = lc;
        LocalTime end = lc.plusHours(14);

        startBox.getSelectionModel().select(start);
        endBox.getSelectionModel().select(start.plusHours(1));

        while (start.isBefore(end.plusSeconds(1))){
            startBox.getItems().add(start);
            endBox.getItems().add(start);
            start = start.plusMinutes(10);

        }
    }

    public void onAdd(ActionEvent actionEvent) {
        Appointment appt =createAppt();
        if(modify) {
            appt.setAppointmentId(apptModify.getAppointmentId());
            AppointmentsDao.updateAppt(appt);
            UtilityLists.updateAppt(apptModify,appt);
            apptModify = null;
        }
        else{
            AppointmentsDao.addAppt(appt);
            appt.setAppointmentId(JDBC.getLastId());
            UtilityLists.addAppointmnet(appt);
        }
        closeForm();
    }

    private Appointment createAppt(){
        Appointment appt = null;
        String title = titleField.getText();
        String desc = descField.getText();
        String loc = locField.getText();
        String type = typeField.getText();
        LocalTime startTime = startBox.getSelectionModel().getSelectedItem();
        LocalTime endTime = endBox.getSelectionModel().getSelectedItem();
        LocalDate date = startPicker.getValue();
        LocalDateTime startDate = LocalDateTime.of(date,startTime);
        LocalDateTime endDate = LocalDateTime.of(date,endTime);
        int customerId = getCustomerId();
        int userId = AppState.getLoggedUser().userId();
        int contactId = contactBox.getSelectionModel().getSelectedItem().contactId();

        appt = new Appointment(title,desc,loc,type,startDate,endDate, customerId,userId,contactId);


        return appt;
    }

    public void onPull(ActionEvent actionEvent) {
        LocalTime start = startBox.getSelectionModel().getSelectedItem();
        endBox.getSelectionModel().select(start.plusHours(1));
    }
}

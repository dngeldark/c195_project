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
import sample.models.*;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

/** This class controls the appointments form controller.*/
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
    public Label errorLbl;
    public TextField userIdField;
    public ComboBox customerBox;
    public Label customerIdlabel;

    /** Sets the appointment to be modified
     *
     * @param appt
     */
    public static void setAppointment(Appointment appt){apptModify = appt;}

    /** Sets the customer to be edited
     *
     * @param id of customer to modify
     */
    public static void setCustomerId(int id){customerId = id;}

    /** returns customer id
     *
     * @return customer id
     */
    public int getCustomerId(){return customerId;}

    /** Closes the appointment form
     *
     */
    private void closeForm(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/main.fxml"));
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 880, 450);
            stage.setTitle("Customer Form");
            stage.setScene(scene);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /** event handdle for the cancel button
     *
     * @param actionEvent
     */
    public void onCancel(ActionEvent actionEvent) {
        closeForm();
        apptModify = null;
    }

    /** Initialize the form
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTimeBoxes();
        contactBox.setValue(UtilityLists.getContactsList().get(0));
        startPicker.setValue(LocalDate.now());
        userIdField.setText(String.valueOf(AppState.getLoggedUser().userId()));
        customerBox.setItems(UtilityLists.getCustomers());
        customerBox.getSelectionModel().select(UtilityLists.getCustomerById(customerId));
        customerIdlabel.setText("ID "+ customerId);

        if(modify){
            customerId = apptModify.getCustomerId();
            customerIdlabel.setText(String.valueOf("ID " + customerId));
            customerBox.getSelectionModel().select(UtilityLists.getCustomerById(customerId));
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

    /** populate the start and end time boxes
     *
     */
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

        while (start.isBefore(lc.plusHours(14))){
            startBox.getItems().add(start);
            endBox.getItems().add(start.plusMinutes(10));
            start = start.plusMinutes(10);
        }

    }

    /** handle add button click
     *
     * @param actionEvent
     */
    public void onAdd(ActionEvent actionEvent) {
        Appointment appt =createAppt();
        if(modify) appt.setAppointmentId(apptModify.getAppointmentId());
        if(!isValidAppointment(appt)) {
            errorLbl.setText("All fields needed");
            return;
        }
        boolean overlapAppt = UtilityLists.compareAppts(appt);
        if(overlapAppt) {
            errorLbl.setText("Overlapping appointment, select different hours");
            return;
        }

        if(appt.getStartTime().isEqual(appt.getEndTime())){
            errorLbl.setText("Start time and end time cant be the same");
            return;
        }

        if(appt.getEndTime().isBefore(appt.getStartTime())){
            errorLbl.setText("End time has to be after start time");
            return;
        }

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

    /** Ceate an Appointment object
     *
     * @return an Appointment object
     */
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
        Customer customer = (Customer) customerBox.getSelectionModel().getSelectedItem();
        int customerId = customer.getCustomerId();
        int userId = AppState.getLoggedUser().userId();
        int contactId = contactBox.getSelectionModel().getSelectedItem().contactId();

        appt = new Appointment(title,desc,loc,type,startDate,endDate, customerId,userId,contactId);


        return appt;
    }

    private boolean isValidAppointment(Appointment appt){
        if(appt.getTitle().isEmpty() || appt.getDescription().isEmpty() || appt.getLocation().isEmpty()
                || appt.getType().isEmpty()) return false;
        return true;
    }

    /** Handle pull event for the start time box picker
     *
     * @param actionEvent
     */
    public void onPull(ActionEvent actionEvent) {
        LocalTime start = startBox.getSelectionModel().getSelectedItem();
        errorLbl.setText("");
    }

    public void onCustomerBoxPull(ActionEvent actionEvent) {
        Customer customer = (Customer) customerBox.getSelectionModel().getSelectedItem();
        customerIdlabel.setText("ID "+customer.getCustomerId());
    }
}

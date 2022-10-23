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
import sample.jdbc.CustomersDao;
import sample.models.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView customersTable;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn addyColumn;
    public TableColumn phoneColumn;
    public TableColumn stateColumn;
    public Button addBtn;
    public TableColumn zipColumn;
    public TableView appointmentsTable;
    public TableColumn apptId;
    public TableColumn apptTitle;
    public TableColumn apptDescription;
    public TableColumn apptLocation;
    public TableColumn apptContact;
    public TableColumn apptType;
    public TableColumn apptStart;
    public TableColumn apptEnd;
    public TableColumn apptCustomerId;
    public TableColumn apptUserId;
    public Button addCustomerBtn;
    public Button modifyCustomerBtn;
    public Button deleteCustomerBtn;
    public Button addApptBtn;
    public Button modApptBtn;
    public Button deleteApptBtn;
    public RadioButton allRadio;
    public RadioButton monthRadio;
    public RadioButton weekRadio;
    public ToggleGroup filter;
    public Button reportsBtn;
    public Button logoutBtn;


    private void populateTables(){
        customersTable.setItems(UtilityLists.getCustomers());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addyColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("subdivision"));

        appointmentsTable.setItems(UtilityLists.getAppointmnets());
        apptId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptContact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        apptCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        apptStart.setCellValueFactory(new PropertyValueFactory<>("startTimeString"));
        apptEnd.setCellValueFactory(new PropertyValueFactory<>("endTimeString"));
        apptType.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateTables();
        appointmentsTable.setPlaceholder(new Label("No Appointments found"));

    }

    public void addCustomer(ActionEvent actionEvent) throws IOException {
        openCustomerForm();
    }

    public void modifyCustomer(ActionEvent actionEvent) throws IOException {
        Customer customer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        if(customer != null) {
            CustomerFormController.setCustomer(customer);
            openCustomerForm();
        }
        else selectCustomerAlert();

    }

    private void selectCustomerAlert(){
            Alert selectCustomer = new Alert(Alert.AlertType.INFORMATION);
            selectCustomer.setContentText("Must First Select a Customer from the Customers Table");
            selectCustomer.setHeaderText(null);
            selectCustomer.show();
    }

    private void selectApptAlert(){
        Alert selectAppt = new Alert(Alert.AlertType.INFORMATION);
        selectAppt.setContentText("Must First Select an Appointment from the Appointments Table");
        selectAppt.setHeaderText(null);
        selectAppt.show(); }

    private void deleteApptAlert(Appointment appt){
        Alert selectAppt = new Alert(Alert.AlertType.INFORMATION);
        selectAppt.setContentText("The "+ appt.getType()+" appointment with ID: "
                + appt.getAppointmentId() +" was deleted");
        selectAppt.setHeaderText(null);
        selectAppt.show();
    }



    public void deleteCustomer(ActionEvent actionEvent) {
        Customer customer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        if(customer != null) {
            boolean confirm = confirm("Delete customer " + customer.getName());
            if (confirm) {
                AppointmentsDao.deleteAppointmentByCustomer(customer.getCustomerId());
                CustomersDao.deleteCustomer(customer.getCustomerId());
                UtilityLists.removeCustomer(customer);
                UtilityLists.resetAppt();
                Alert selectAppt = new Alert(Alert.AlertType.INFORMATION);
                selectAppt.setContentText("Customer with id: "+customer.getCustomerId()+" has been deleted");
                selectAppt.setHeaderText(null);
                selectAppt.show();
            }
            customersTable.getSelectionModel().clearSelection();
        }
        else{
            selectCustomerAlert();
        }
    }

    private void openCustomerForm() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/customerForm.fxml"));
            Stage stage = (Stage) customersTable.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(),350,330);
            stage.setTitle("Customer Form");
            stage.setScene(scene);
    }

    public void openApptForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/apptForm.fxml"));
        Stage stage = (Stage) customersTable.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),350,500);
        stage.setTitle("Customer Form");
        stage.setScene(scene);
        System.out.println(AppState.getLoggedUser());
    }

    public void onAddAppt() throws IOException {
        Customer customer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        if (customer != null){
            ApptFormController.setCustomerId(customer.getCustomerId());
            openApptForm();
        }
        else selectCustomerAlert();
    }

    public void onModAppt(ActionEvent actionEvent) throws IOException {
        Appointment apt = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        if(apt != null){
            ApptFormController.setAppointment(apt);
            openApptForm();
        }
        else selectApptAlert();
    }

    public void onDeleteAppt(ActionEvent actionEvent) {
        Appointment appt = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        if(appt != null){
            boolean confirm = confirm(String.valueOf("Appointment with Id: "
                    +appt.getAppointmentId())+" Scheduled for: "+appt.getStartTimeString());
            if(confirm) {
                AppointmentsDao.deleteAppointment(appt.getAppointmentId());
                UtilityLists.removeAppt(appt);
                deleteApptAlert(appt);
                resetApptTable();
            }
        }
        else selectApptAlert();
        appointmentsTable.getSelectionModel().clearSelection();
    }

    public void toggleFilter(ActionEvent actionEvent) {
        resetApptTable();
    }

    private void resetApptTable(){
        if(allRadio.isSelected()) appointmentsTable.setItems(UtilityLists.getAppointmnets());
        else if(monthRadio.isSelected()) appointmentsTable.setItems(UtilityLists.appointmentsByMonth());
        else if (weekRadio.isSelected()) appointmentsTable.setItems(UtilityLists.appointmentsByWeek());
    }

    private boolean confirm(String item){
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setContentText("Do you want to delete " + item);
        deleteAlert.setHeaderText(null);
        Optional<ButtonType> result = deleteAlert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public void onReports(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/reports.fxml"));
        Stage stage = (Stage) customersTable.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),700,500);
        stage.setTitle("Reports");
        stage.setScene(scene);
    }

    public void onLogout(ActionEvent actionEvent) throws IOException {
        AppState.setUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/loginForm.fxml"));
        Stage stage = (Stage) customersTable.getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),320,260);
        stage.setTitle("Reports");
        stage.setScene(scene);
    }
}

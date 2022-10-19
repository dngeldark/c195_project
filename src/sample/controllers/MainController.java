package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Main;
import sample.jdbc.AppointmentsDao;
import sample.jdbc.CustomersDao;
import sample.models.*;

import java.io.IOException;
import java.net.URL;
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

    }

    public void deleteCustomer(ActionEvent actionEvent) {

        Customer customer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        if(customer != null) {
            AppointmentsDao.deleteAppointmentByCustomer(customer.getCustomerId());
            CustomersDao.deleteCustomer(customer.getCustomerId());
            UtilityLists.removeCustomer(customer);
            UtilityLists.resetAppt();
            customersTable.getSelectionModel().clearSelection();
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
    }

    public void onAddAppt() throws IOException {
        Customer customer = (Customer) customersTable.getSelectionModel().getSelectedItem();
        if (customer != null){
            ApptFormController.setCustomerId(customer.getCustomerId());
            openApptForm();
        }
    }

    public void onModAppt(ActionEvent actionEvent) throws IOException {
        Appointment apt = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        if(apt != null){
            ApptFormController.setAppointment(apt);
            openApptForm();
        }
    }

    public void onDeleteAppt(ActionEvent actionEvent) {
        Appointment appt = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        if(appt != null){
            AppointmentsDao.deleteAppointment(appt.getAppointmentId());
            UtilityLists.removeAppt(appt);
        }
        appointmentsTable.getSelectionModel().clearSelection();
    }
}

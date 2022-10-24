package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.jdbc.AppointmentsDao;
import sample.jdbc.JDBC;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UtilityLists {
    //public static ObservableList<Test> tests = FXCollections.observableArrayList();


    private static ObservableList<Customer> customersList = FXCollections.observableArrayList();
    private static ObservableList<Country> countries = FXCollections.observableArrayList();
    private static ObservableList<CountryDivision> countryDivisions = FXCollections.observableArrayList();
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private static ObservableList<Contact> contactsList = FXCollections.observableArrayList();

    public static void addCountry(Country country){countries.add(country);}
    public static void addCustomer(Customer customer){customersList.add(customer);}
    public static void addAppointmnet(Appointment appointment){
        appointments.add(appointment);}
    public static void addContact(Contact contact){contactsList.add(contact);}


    public static ObservableList<Country> getCountries(){return countries;}
    public static ObservableList<Customer> getCustomers(){return customersList;}
    public static ObservableList<Appointment> getAppointmnets(){return appointments;}
    public static ObservableList<Contact> getContactsList(){return contactsList;}
    public static ObservableList<Appointment> getApptsbyContact(int contactId){
        ObservableList<Appointment> appts = FXCollections.observableArrayList();
        for (Appointment appointment : appointments) {
            if (appointment.getContactId() == contactId) appts.add(appointment);
        }
        return appts;
    }
    public static boolean compareAppts(Appointment appt){
        for (Appointment appointment : appointments) {
            if(appointment.getCustomerId()== appt.getCustomerId()){
                if((appointment.getStartTime().equals(appt.getStartTime())
                        ||appointment.getStartTime().isAfter(appt.getStartTime()))
                        && appointment.getStartTime().isBefore(appt.getEndTime())){
                    return true;
                }
                else if(appointment.getEndTime().isAfter(appt.getStartTime())
                        &&(appointment.getEndTime().isBefore(appt.getEndTime())
                        ||appointment.getEndTime().isEqual(appt.getEndTime()))){
                    return true;
                }
            }
        }
        return false;
    }

    public static void updateAppt(Appointment appt, Appointment appt2){
        appointments.remove(appt);
        appointments.add(appt2);
    }

    public static Contact getContactById(int id){
        Contact contact = null;
        for (Contact cont : contactsList) {
            if(cont.contactId() == id){return cont;}
        }
        return contact;
    }

    public static String getContactName(int id){
        String name = null;
        for (Contact cont : contactsList) {
            if(cont.contactId() == id){return cont.contactName();}
        }
        return name;
    }

    public static void removeCustomer(Customer customer) {customersList.remove(customer); }
    public static void removeAppt(Appointment appt) {appointments.remove(appt);}

    public static void resetAppt(){
        appointments.clear();
        AppointmentsDao.setAppointments();
    }

    public static ObservableList<Appointment> appointmentsByMonth(){
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        LocalDate today = LocalDate.now();
        for (Appointment appointment : appointments) {
            LocalDate date = appointment.getStartTime().toLocalDate();
            if(date.isBefore(today.plusMonths(1)) && date.isAfter(today.minusDays(1))) apptList.add(appointment);
        }
        return apptList;
    }    public static ObservableList<Appointment> appointmentsByWeek(){
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        LocalDate currentDate = LocalDate.now();
        for (Appointment appointment : appointments) {
            LocalDate date = appointment.getStartTime().toLocalDate();
            if(date.isBefore(currentDate.plusWeeks(1)) && date.isAfter(currentDate.minusDays(1))) apptList.add(appointment);
        }
        return apptList;
    }

    public static CountryDivision findSubDivisionById(int id){
        CountryDivision sub = null;
        for (Country country : countries) {
            for (CountryDivision countryDivision : country.getDivisionList()) {
                if(countryDivision.divisionId() == id) sub = countryDivision;
            }
        }
        return sub;
    }

}

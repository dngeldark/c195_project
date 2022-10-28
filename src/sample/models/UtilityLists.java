package sample.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.jdbc.AppointmentsDao;
import java.time.LocalDate;
import java.time.LocalDateTime;

/** Class to hold list utilities.*/
public class UtilityLists {

    // Lists used
    private static ObservableList<Customer> customersList = FXCollections.observableArrayList();
    private static ObservableList<Country> countries = FXCollections.observableArrayList();
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private static ObservableList<Contact> contactsList = FXCollections.observableArrayList();

    // Add methods

    /** Add country to countries list
     *
     * @param country
     */
    public static void addCountry(Country country){countries.add(country);}

    /** Add customer to customers list
     *
     * @param customer
     */
    public static void addCustomer(Customer customer){customersList.add(customer);}

    /** Add appointment to appointment list
     *
     * @param appointment
     */
    public static void addAppointmnet(Appointment appointment){ appointments.add(appointment);}

    /** Add contact to contacts list
     *
     * @param contact
     */
    public static void addContact(Contact contact){contactsList.add(contact);}

    // Getter methods

    /** Get countries list
     *
     * @return countries
     */
    public static ObservableList<Country> getCountries(){return countries;}

    /** Get customers list
     *
     * @return customers
     */
    public static ObservableList<Customer> getCustomers(){return customersList;}

    /** Get appointments list
     *
     * @return appointments
     */
    public static ObservableList<Appointment> getAppointmnets(){return appointments;}

    /** Get contacts from contacts list
     *
     * @return contacts
     */
    public static ObservableList<Contact> getContactsList(){return contactsList;}

    /** Get appointments by contact
     *
     * @param contactId
     * @return list of appointments by contact id
     */
    public static ObservableList<Appointment> getApptsbyContact(int contactId){
        ObservableList<Appointment> appts = FXCollections.observableArrayList();
        for (Appointment appointment : appointments) {
            if (appointment.getContactId() == contactId) appts.add(appointment);
        }
        return appts;
    }

    public static Customer getCustomerById(int customerId) {
        for (Customer customer : customersList) {
            if(customer.getCustomerId() == customerId) return customer;
        }
        return null;
    }

    /** Compare new appointmet to check if it overlaps with existing appointments
     *
     * @param appt
     * @return true or false
     */
    public static boolean compareAppts(Appointment appt){
        boolean overlap = false;
        for (Appointment appointment : appointments) {
            if(appointment.getAppointmentId() != appt.getAppointmentId()){
                if((appointment.getStartTime().equals(appt.getStartTime())
                        ||appointment.getStartTime().isAfter(appt.getStartTime()))
                        && appointment.getStartTime().isBefore(appt.getEndTime())){
                    overlap = true;
                }
                else if(appointment.getEndTime().isAfter(appt.getStartTime())
                        &&(appointment.getEndTime().isBefore(appt.getEndTime())
                        ||appointment.getEndTime().isEqual(appt.getEndTime()))){
                    overlap = true;
                }
                else if(appointment.getStartTime().isBefore(appt.getStartTime())
                        && appointment.getEndTime().isAfter(appt.getEndTime())){
                    overlap = true;
                }
            }
        }
        return overlap;
    }

    /** Update appointment in the appointments list
     *
     * @param appt old appointment
     * @param appt2 updated appointment
     */
    public static void updateAppt(Appointment appt, Appointment appt2){
        appointments.remove(appt);
        appointments.add(appt2);
    }

    /** Get contact by id
     *
     * @param id
     * @return contact
     */
    public static Contact getContactById(int id){
        Contact contact = null;
        for (Contact cont : contactsList) {
            if(cont.contactId() == id){return cont;}
        }
        return contact;
    }

    /** Get contact name by contact id
     *
     * @param contactId
     * @return contact name
     */
    public static String getContactName(int contactId){
        String name = null;
        for (Contact cont : contactsList) {
            if(cont.contactId() == contactId){return cont.contactName();}
        }
        return name;
    }

    /** Remove customer
     *
     * @param customer instance
     */
    public static void removeCustomer(Customer customer) {customersList.remove(customer); }

    /** Remove appointment
     *
     * @param appt
     */
    public static void removeAppt(Appointment appt) {appointments.remove(appt);}

    /** Reset appointments list.*/
    public static void resetAppt(){
        appointments.clear();
        AppointmentsDao.setAppointments();
    }

    /** Get appointments within a month
     *
     * @return list of appointments within a month
     */
    public static ObservableList<Appointment> appointmentsByMonth(){
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        LocalDate today = LocalDate.now();
        for (Appointment appointment : appointments) {
            LocalDate date = appointment.getStartTime().toLocalDate();
            if(date.isBefore(today.plusMonths(1)) && date.isAfter(today.minusDays(1))) apptList.add(appointment);
        }
        return apptList;
    }

    /** Get appointments within a week
     *
     * @return list of appointments in a week
     */
    public static ObservableList<Appointment> appointmentsByWeek(){
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        LocalDate currentDate = LocalDate.now();
        for (Appointment appointment : appointments) {
            LocalDate date = appointment.getStartTime().toLocalDate();
            if(date.isBefore(currentDate.plusWeeks(1)) && date.isAfter(currentDate.minusDays(1))) apptList.add(appointment);
        }
        return apptList;
    }

    /** Find subdivision by its id
     *
     * @param id
     * @return subdivision
     */
    public static CountryDivision findSubDivisionById(int id){
        CountryDivision sub = null;
        for (Country country : countries) {
            for (CountryDivision countryDivision : country.getDivisionList()) {
                if(countryDivision.divisionId() == id) sub = countryDivision;
            }
        }
        return sub;
    }

    /** Search to see if there is an appointment within 15 minutes of loging in
     *
     * @return message weather there is an appointment or not
     */
    public static String apptAtLogin(){
        String message = "No upcoming appointments";
        LocalDateTime lt = LocalDateTime.now();
        for (Appointment appointment : appointments) {
            if (appointment.getStartTime().isBefore(lt.plusMinutes(16))
                    && appointment.getStartTime().isAfter(lt.minusMinutes(1))){
                message = "upcoming appointment: \n ID: " + appointment.getAppointmentId() +"" +
                        " Time: "+
                        appointment.getStartTimeString();
            }
        }


        return message;
    }

}

package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;

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

    public static Contact getContactById(int id){
        Contact contact = null;
        for (Contact cont : contactsList) {
            if(cont.contactId() == id){return cont;}
        }
        return contact;
    }

    public static void removeCustomer(Customer customer) {customersList.remove(customer); }

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

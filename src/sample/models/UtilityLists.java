package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UtilityLists {
    //public static ObservableList<Test> tests = FXCollections.observableArrayList();


    private static ObservableList<Customer> customersList = FXCollections.observableArrayList();
    private static ObservableList<Country> countries = FXCollections.observableArrayList();
    private static ObservableList<CountryDivision> countryDivisions = FXCollections.observableArrayList();
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    public static void addCountry(Country country){countries.add(country);}
    public static void addCustomer(Customer customer){customersList.add(customer);}
    public static void addAppointmnet(Appointment appointment){
        appointments.add(appointment);}


    public static ObservableList<Country> getCountries(){return countries;}
    public static ObservableList<Customer> getCustomers(){return customersList;}
    public static ObservableList<Appointment> getAppointmnets(){return appointments;}

    public static void removeAppointmnet(Appointment appointment){
        appointments.remove(appointment);}
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

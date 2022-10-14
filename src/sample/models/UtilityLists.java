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
    private static ObservableList<Consultation> consultationsList = FXCollections.observableArrayList();

    public static void addCountry(Country country){countries.add(country);}
    public static void addCustomer(Customer customer){customersList.add(customer);}
    public static void addAppointmnet(Appointment appointment){
        appointments.add(appointment);}


    public static ObservableList<Country> getCountries(){return countries;}
    public static ObservableList<Customer> getCustomers(){return customersList;}
    public static ObservableList<Appointment> getAppointmnets(){return appointments;}
    public static ObservableList<Consultation> getConsultationsList(){return consultationsList;}

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

    private void populateConsultationsList(){
        Consultation cons = new Consultation("Marketing Consultation",
                "Discuss Marketing Strategies",
                "Miami Office",
                "Short consultation");
        consultationsList.add(cons);
        Consultation cons2 = new Consultation("Marketing Consultation",
                "Discuss Marketing Strategies",
                "Miami Office",
                "Short consultation");
        consultationsList.add(cons2);
        Consultation cons3 = new Consultation("Marketing Consultation",
                "Discuss Marketing Strategies",
                "Miami Office",
                "Short consultation");
        consultationsList.add(cons3);
        Consultation cons4 = new Consultation("Marketing Consultation",
                "Discuss Marketing Strategies",
                "Miami Office",
                "Short consultation");
        consultationsList.add(cons4);
    }




}

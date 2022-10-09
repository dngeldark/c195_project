package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CountriesList {
    private static ObservableList<Country> countries = FXCollections.observableArrayList();
    //private static ObservableList<CountryDivision> countryDivisions = FXCollections.observableArrayList();

    public static void addCountry(Country country){countries.add(country);}
    //public static void addCountryDivision(CountryDivision division){countryDivisions.add(division);}

    public static ObservableList<Country> getCountries(){return countries;}

}

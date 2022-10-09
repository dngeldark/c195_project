package sample.models;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;

public final class Country {
    private final int countryId;
    private final String countryName;
    private ObservableList<CountryDivision> divisionList = FXCollections.observableArrayList();

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int countryId() {
        return countryId;
    }

    public String countryName() {
        return countryName;
    }

    public void addDivision(CountryDivision division){divisionList.add(division);}
    public ObservableList<CountryDivision> getDivisionList(){return divisionList;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Country) obj;
        return this.countryId == that.countryId &&
                Objects.equals(this.countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, countryName);
    }

    @Override
    public String toString() {
        return countryName;
    }


}

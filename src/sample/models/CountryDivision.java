package sample.models;

import java.util.Objects;

public final class CountryDivision {
    private final int divisionId;
    private final String divisionName;
    private final int countryId;

    public Country getCountry() {
        return country;
    }

    private Country country;

    public CountryDivision(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    public int divisionId() {
        return divisionId;
    }

    public String divisionName() {
        return divisionName;
    }

    public int countryId() {
        return countryId;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CountryDivision) obj;
        return this.divisionId == that.divisionId &&
                Objects.equals(this.divisionName, that.divisionName) &&
                this.countryId == that.countryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(divisionId, divisionName, countryId);
    }

    @Override
    public String toString() {
        return divisionName;
    }

}

package sample.models;
import java.util.Objects;

/** Class for country subdivisions.*/
public final class CountryDivision {
    private final int divisionId;
    private final String divisionName;
    private final int countryId;
    private Country country;

    public CountryDivision(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /** Get country
     *
     * @return
     */
    public Country getCountry() {
        return country;
    }

    /** Get division id.
     *
     * @return division id.
     */
    public int divisionId() {
        return divisionId;
    }

    /** Get subdivision name.
     *
     * @return subdivision name.
     */
    public String divisionName() {
        return divisionName;
    }

    /** get Country id.
     *
     * @return country id.
     */
    public int countryId() {
        return countryId;
    }

    /** Set country
     *
     * @param country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null || obj.getClass() != this.getClass()) return false;
//        var that = (CountryDivision) obj;
//        return this.divisionId == that.divisionId &&
//                Objects.equals(this.divisionName, that.divisionName) &&
//                this.countryId == that.countryId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(divisionId, divisionName, countryId);
//    }

    @Override
    /** String representation of the subdivision.*/
    public String toString() {
        return divisionName;
    }

}

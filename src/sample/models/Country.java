package sample.models;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Objects;

/** Calss for country objects.*/
public final class Country {
    private final int countryId;
    private final String countryName;
    private ObservableList<CountryDivision> divisionList = FXCollections.observableArrayList();

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /** Get country id.
     *
     * @return country id
     */
    public int countryId() {
        return countryId;
    }

    /** Get country name.
     *
     * @return country name
     */
    public String countryName() {
        return countryName;
    }

    /** add subdivision to the subdivision list property
     *
     * @param division
     */
    public void addDivision(CountryDivision division){divisionList.add(division);}

    /** Get subdivisions list.
     *
     * @return subdivision list.
     */
    public ObservableList<CountryDivision> getDivisionList(){return divisionList;}


//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null || obj.getClass() != this.getClass()) return false;
//        var that = (Country) obj;
//        return this.countryId == that.countryId &&
//                Objects.equals(this.countryName, that.countryName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(countryId, countryName);
//    }


    @Override
    /** String from of country.*/
    public String toString() {
        return countryName;
    }


}

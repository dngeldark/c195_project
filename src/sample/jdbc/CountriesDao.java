package sample.jdbc;
import sample.models.UtilityLists;
import sample.models.Country;
import sample.models.CountryDivision;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Handles the database access for the countries table.*/
public class CountriesDao {
    // get the connection to the database
    private static Connection con = JDBC.getConnection();

    /** Fetches all the countries in the countries table and adds them to the countries list.
     *
     * @throws SQLException
     */
    public static void setCountries() throws SQLException {
        String sql = "SELECT * FROM Countries";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            Country currentCountry = new Country(id,name);
            UtilityLists.addCountry(currentCountry);
        }
    }

    /** Fetches all the subdivisions from the subdivision table and adds them to the subdivision list.
     *
     * @throws SQLException
     */
    public static void setDivisions() throws SQLException {
        String sql = "SELECT division_id, division, country_id FROM first_level_divisions";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int divisionId = rs.getInt(1);
            String divisionName = rs.getString(2);
            int countryId = rs.getInt(3);
            Country country = UtilityLists.getCountries().get(countryId-1);
            CountryDivision division = new CountryDivision(divisionId,divisionName,countryId);
            division.setCountry(country);
            country.addDivision(division);
        }
    }

}

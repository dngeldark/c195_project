package sample.jdbc;

import sample.models.UtilityLists;
import sample.models.Country;
import sample.models.CountryDivision;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesDao {
    private static Connection con = JDBC.getConnection();

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

    public static void setDivisions() throws SQLException {
        String sql = "SELECT division_id, division, country_id FROM first_level_divisions";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int divisionId = rs.getInt(1);
            String divisionName = rs.getString(2);
            int countryId = rs.getInt(3);
            Country country = UtilityLists.getCountries().get(countryId-1);
            country.addDivision(new CountryDivision(divisionId,divisionName,countryId));
        }
    }

}

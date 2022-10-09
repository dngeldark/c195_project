package sample.jdbc;

import sample.models.CountriesList;
import sample.models.Country;

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
            CountriesList.addCountry(new Country(id,name));
        }
    }

    public static void setDivisions(){

    }
}

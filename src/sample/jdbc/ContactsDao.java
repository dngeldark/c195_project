package sample.jdbc;

import sample.models.Contact;
import sample.models.UtilityLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsDao {
    private static Connection con = JDBC.getConnection();

    public static void initContacts() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){

            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);

            UtilityLists.addContact(new Contact(id,name,email));
        }
    }
}

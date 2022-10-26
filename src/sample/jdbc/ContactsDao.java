package sample.jdbc;
import sample.models.Contact;
import sample.models.UtilityLists;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Handles the database access for the contacts table.*/
public class ContactsDao {
    // get the connection to the database
    private static Connection con = JDBC.getConnection();

    /** Fetch all the contacts in the database and adds them to the contacts list
     *
     * @throws SQLException
     */
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

package sample.jdbc;
import sample.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Handles the Users database access.
 *  handles password authentication.
 */
public class LoginDao {
    private static Connection con = JDBC.getConnection();

    /** Fetch the user and compares the passwords provided.
     *
     * @param username username
     * @param passwrd password
     * @return
     */
    public static User getUserPsswd(String username, String passwrd){
        User user = null;
        String sql = "SELECT * FROM users " +
                "WHERE user_name = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int userId = rs.getInt(1);
                String name = rs.getString(2);
                String pass = rs.getString(3);
                if(pass.equals(passwrd)) {
                    user = new User(userId,name);
                }


            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }


}

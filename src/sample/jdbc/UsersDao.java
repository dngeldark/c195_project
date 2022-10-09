package sample.jdbc;

import sample.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao {
    private static Connection con = JDBC.getConnection();

    public static User getUser(String name) throws SQLException {
        User user = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE user_name = ?");
        ps.setString(1,name);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            user = new User(Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    rs.getString(3));
        }
        return user;
    }
}

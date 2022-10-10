package sample.jdbc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.models.Customer;
import sample.models.UtilityLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersDao {
    private static Connection con = JDBC.getConnection();

    public static void setCustomers() throws SQLException {
        //ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String postalCode = rs.getString(4);
            String phone = rs.getString(5);
            int divisionId = rs.getInt(10);

            Customer customer = new Customer(id,name,address,postalCode,phone,divisionId);
            UtilityLists.addCustomer(customer);
        }
    }



}

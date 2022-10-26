package sample.jdbc;
import sample.models.Customer;
import sample.models.UtilityLists;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Handles the databse access to the customers table.*/
public class CustomersDao {
    // Fetch the database connection
    private static Connection con = JDBC.getConnection();

    /** Fetches all the customers from the customer table and adds them to the customers list.
     *
     * @throws SQLException
     */
    public static void setCustomers() throws SQLException {
        String sql = "SELECT * FROM customers" +
                " JOIN first_level_divisions" +
                " ON customers.division_id=first_level_divisions.division_id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            String postalCode = rs.getString(4);
            String phone = rs.getString(5);
            int divisionId = rs.getInt(10);
            String subdivision = rs.getString(12);

            Customer customer = new Customer(name,address,postalCode,phone,divisionId,subdivision);
            customer.setDivision(divisionId);
            customer.setCustomerId(id);
            UtilityLists.addCustomer(customer);
        }
    }

    /** Add customer to the customers table.
     *
     * @param name
     * @param address
     * @param code zipcode
     * @param phone
     * @param division country subdivision
     * @throws SQLException
     */
    public static void addCustomer(String name,String address, String code,String phone, int division) throws SQLException {
        String sql = "INSERT INTO customers (customer_name,address,postal_code,phone,division_id)" +
                "VALUES (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,address);
        ps.setString(3,code);
        ps.setString(4,phone);
        ps.setInt(5,division);
        ps.execute();
    }

    /** Delete customer by customer Id from the customers table
     *
     * @param customerId
     */
    public static void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customers WHERE customer_id = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,customerId);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Update customer from the customers table
     *
     * @param customer updated customer
     */
    public static void updateCustomer(Customer customer){

        String sql = "UPDATE customers " +
                "SET customer_name = ?, address = ?, postal_code = ?, phone = ?, division_id = ? " +
                "WHERE customer_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getAddress());
            ps.setString(3,customer.getPostalCode());
            ps.setString(4,customer.getPhone());
            ps.setInt(5,customer.getDivisionId());
            ps.setInt(6,customer.getCustomerId());
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /** Query a count of customers by country
     *
     * @return a formatted text of how many customers each country has
     */
    public static String customersByCountry() {
        StringBuilder report = new StringBuilder();

        String sql = "SELECT countries.Country, " +
                "count(*) FROM customers " +
                "JOIN first_level_divisions " +
                "ON customers.Division_ID=first_level_divisions.Division_ID " +
                "JOIN countries " +
                "ON first_level_divisions.Country_ID=countries.Country_ID " +
                "group by countries.Country";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                report.append(rs.getString(1)+" ");
                report.append(rs.getString(2)+ "\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return report.toString();
    }
}

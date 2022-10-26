package sample.jdbc;
import sample.models.Appointment;
import sample.models.UtilityLists;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/** Handles the MySQL data access for the appointments table.*/
public class AppointmentsDao {
    // fetches the connection to the databse
    private static Connection con = JDBC.getConnection();

    /** Delete appointment from the database by appointment id
     *
     * @param apptId appointment id
     */
    public static void deleteAppointment(int apptId){
        String sql = "DELETE FROM Appointments " +
                "WHERE appointment_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,apptId);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /** delete appointment from databse by customer id
     *
     * @param customerId
     */
    public static void deleteAppointmentByCustomer(int customerId){
        String sql = "DELETE FROM Appointments " +
                "WHERE customer_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,customerId);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /** Fetches all the appointments from the databse and add them to the appointments list.*/
    public static void setAppointments(){
        String sql = "SELECT * FROM Appointments";
        int apptId;
        String title;
        String desc;
        String loc;
        String type;
        LocalDateTime start;
        LocalDateTime end;
        int custId;
        int userId;
        int contactID;

        {
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()){

                    apptId = rs.getInt(1);
                    title = rs.getString(2);
                    desc = rs.getString(3);
                    loc = rs.getString(4);
                    type = rs.getString(5);
                    start = rs.getTimestamp(6).toLocalDateTime();
                    end = rs.getTimestamp(7).toLocalDateTime();
                    custId = rs.getInt(12);
                    userId = rs.getInt(13);
                    contactID = rs.getInt(14);

                    Timestamp ts = rs.getTimestamp(6);
                    Instant inst = ts.toInstant();
                    ZoneId localZone = ZoneId.of(TimeZone.getDefault().getID());
                    ZonedDateTime toLocal = inst.atZone(localZone);

                    Appointment appt = new Appointment(title,desc,loc,type,start,end,custId,userId,contactID);
                    appt.setAppointmentId(apptId);

                    UtilityLists.addAppointmnet(appt);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /** Add appointment to databse
     *
     * @param appt appointment to be added
     */
    public static void addAppt(Appointment appt){
        String sql = "INSERT INTO appointments " +
                "(title, description, location, type, start, end, customer_id, user_id, contact_id) " +
                "Values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, appt.getTitle());
            ps.setString(2, appt.getDescription());
            ps.setString(3, appt.getLocation());
            ps.setString(4,appt.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appt.getStartTime()));
            ps.setTimestamp(6, Timestamp.valueOf(appt.getEndTime()));
            ps.setInt(7,appt.getCustomerId());
            ps.setInt(8,appt.getUserId());
            ps.setInt(9,appt.getContactId());
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Update appointment in databse
     *
     * @param appt updated appointment
     */
    public static void updateAppt(Appointment appt){
        String sql = "UPDATE appointments " +
                "SET title = ?, description = ?, location = ?, type = ?, start = ?, " +
                "end = ?, customer_id = ?, user_id = ?, contact_id = ? " +
                "WHERE appointment_id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, appt.getTitle());
            ps.setString(2, appt.getDescription());
            ps.setString(3, appt.getLocation());
            ps.setString(4,appt.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appt.getStartTime()));
            ps.setTimestamp(6, Timestamp.valueOf(appt.getEndTime()));
            ps.setInt(7,appt.getCustomerId());
            ps.setInt(8,1);
            ps.setInt(9,appt.getContactId());
            ps.setInt(10,appt.getAppointmentId());
            ps.execute();

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** Query appointments and group them by month and type
     *
     * @return a formatted string of appointments count by month and type
     */
    public static String queryApptByMonth(){
        StringBuilder report = new StringBuilder();

        String sql = "SELECT monthname(start), type, " +
                "count(*) from appointments " +
                "group by month(start) and year(start),type";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                report.append(rs.getString(1)+" ");
                report.append(rs.getString(2)+ " ");
                report.append(rs.getString(3)+"\n");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return report.toString();
    }

}

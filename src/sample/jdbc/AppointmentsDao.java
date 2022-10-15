package sample.jdbc;

import sample.models.Appointment;
import sample.models.UtilityLists;

import java.security.PublicKey;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class AppointmentsDao {
    private static Connection con = JDBC.getConnection();

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
            ps.setInt(7,1);
            ps.setInt(8,1);
            ps.setInt(9,1);
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

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
            ps.setInt(7,1);
            ps.setInt(8,1);
            ps.setInt(9,1);
            ps.setInt(10,appt.getAppointmentId());
            ps.execute();

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

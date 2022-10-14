package sample.jdbc;

import sample.models.Appointment;
import sample.models.UtilityLists;

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

                    //System.out.println(inst);
                    //System.out.println(toLocal);



                    //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hora);
                    //Date other = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hora);
                    //String other = new SimpleDateFormat("HH-mm").format(ts);
                    //System.out.println(other);



                    Appointment appt = new Appointment(title,desc,loc,type,start,end,custId,userId,contactID);
                    appt.setAppointmentId(apptId);

                    UtilityLists.addAppointmnet(appt);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}

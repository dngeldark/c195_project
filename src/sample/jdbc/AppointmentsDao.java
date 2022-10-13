package sample.jdbc;

import sample.models.Appointment;
import sample.models.UtilityLists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AppointmentsDao {
    private static Connection con = JDBC.getConnection();

    public static void setAppointments(){
        String sql = "SELECT * FROM Appointments";
        int apptId;
        String title;
        String desc;
        String loc;
        String type;
        Date start;
        Date end;
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
                    start = rs.getDate(6);
                    end = rs.getDate(7);
                    custId = rs.getInt(12);
                    userId = rs.getInt(13);
                    contactID = rs.getInt(14);


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

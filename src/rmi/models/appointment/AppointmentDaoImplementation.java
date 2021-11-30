package rmi.models.appointment;

import rmi.database.Postgres;
import rmi.models.patient.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImplementation implements AppointmentDao {

    static Connection con = Postgres.getConnection();

    @Override
    public int add(Appointment appointment) throws SQLException {
        String query = "insert into \"Appointment\"(p_id, u_id, date, created, attended) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setInt(1, appointment.getP_id());
        ps.setInt(2, appointment.getU_id());
        ps.setDate(3, appointment.getDate());
        ps.setDate(4, appointment.getCreated());
        ps.setBoolean(5, appointment.isAttended());
        int n = ps.executeUpdate();
        return n;
    }

    //    @Override
//    public void delete(int id) throws SQLException {
//        String query
//                = "delete from appointment where id =?";
//        PreparedStatement ps
//                = con.prepareStatement(query);
//        ps.setInt(1, id);
//        ps.executeUpdate();
//    }
//
    @Override
    public Appointment getAppointment(int id) throws SQLException {
        String query = "select * from \"Appointment\" where id= ?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);
        Appointment appointment = new Appointment();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            appointment.setId(rs.getInt("id"));
            appointment.setP_id(rs.getInt("p_id"));
            appointment.setU_id(rs.getInt("u_id"));
            appointment.setDate(rs.getDate("date"));
            appointment.setCreated(rs.getDate("created"));
            appointment.setAttended(rs.getBoolean("attended"));
        }

        if (check == true) {
            return appointment;
        } else
            return null;
    }

    @Override
    public List<Appointment> getAppointments() throws SQLException {
        String query = "select * from appointment";
        PreparedStatement ps= con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Appointment> ls = new ArrayList();

        while (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt("id"));
            appointment.setP_id(rs.getInt("p_id"));
            appointment.setU_id(rs.getInt("u_id"));
            appointment.setDate(rs.getDate("date"));
            appointment.setCreated(rs.getDate("created"));
            appointment.setAttended(rs.getBoolean("attended"));
            ls.add(appointment);
        }
        return ls;
    }

    @Override
    public List<Appointment> getPatientAppointments(int p_id) throws SQLException {
        String query = "select * from appointment where p_id=?";
        PreparedStatement ps= con.prepareStatement(query);
        ps.setInt(1, p_id);
        ResultSet rs = ps.executeQuery();
        List<Appointment> ls = new ArrayList();

        while (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt("id"));
            appointment.setP_id(rs.getInt("p_id"));
            appointment.setU_id(rs.getInt("u_id"));
            appointment.setDate(rs.getDate("date"));
            appointment.setCreated(rs.getDate("created"));
            appointment.setAttended(rs.getBoolean("attended"));
            ls.add(appointment);
        }
        return ls;
    }

    @Override
    public void update(Appointment appointment) throws SQLException {

        String query = "update \"Appointment\" set p_id=?, u_id=?, date=?, created=?, attended=? where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, appointment.getP_id());
        ps.setInt(2, appointment.getU_id());
        ps.setDate(3, appointment.getDate());
        ps.setDate(4, appointment.getCreated());
        ps.setBoolean(5, appointment.isAttended());
        ps.setInt(6, appointment.getId());
        ps.executeUpdate();
    }
}

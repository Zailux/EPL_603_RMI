package rmi.models.consultation;

import rmi.database.Postgres;
import rmi.models.appointment.Appointment;
import rmi.models.treatment.Treatment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultationDaoImplementation implements ConsultationDao {

    static Connection con = Postgres.getConnection();

    @Override
    public int add(Consultation consultation) throws SQLException {
        String query = "insert into \"Consultation\"(t_id, p_id, pc_id, u_id, type, date, finished) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setInt(1, consultation.getT_id());
        ps.setInt(2, consultation.getP_id());
        ps.setInt(3, consultation.getPc_id());
        ps.setInt(4, consultation.getU_id());
        ps.setString(5, consultation.getType());
        ps.setDate(6, consultation.getDate());
        ps.setBoolean(7, consultation.isFinished());
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public Consultation getConsultation(int id) throws SQLException {
        String query = "select * from \"Consultation\" where id= ?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);
        Consultation consultation = new Consultation();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            consultation.setId(rs.getInt("id"));
            consultation.setT_id(rs.getInt("t_id"));
            consultation.setP_id(rs.getInt("p_id"));
            consultation.setPc_id(rs.getInt("pc_id"));
            consultation.setU_id(rs.getInt("u_id"));
            consultation.setType(rs.getString("type"));
            consultation.setDate(rs.getDate("date"));
            consultation.setFinished(rs.getBoolean("finished"));
        }

        if (check == true) {
            return consultation;
        } else
            return null;

    }

    @Override
    public Consultation getPatientLatestConsultation(int p_id) throws SQLException {
        String query = "select * from \"Consultation\" where p_id= ? order by date desc limit 1";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, p_id);
        Consultation consultation = new Consultation();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            consultation.setId(rs.getInt("id"));
            consultation.setT_id(rs.getInt("t_id"));
            consultation.setP_id(rs.getInt("p_id"));
            consultation.setPc_id(rs.getInt("pc_id"));
            consultation.setU_id(rs.getInt("u_id"));
            consultation.setType(rs.getString("type"));
            consultation.setDate(rs.getDate("date"));
            consultation.setFinished(rs.getBoolean("finished"));
        }

        if (check == true) {
            return consultation;
        } else
            return null;
    }

    @Override
    public void update(Consultation consultation) throws SQLException {
        String query = "update \"Consultation\" set t_id=?, p_id=?, pc_id=?, u_id=?, type=?, date=?, finished=? where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, consultation.getT_id());
        ps.setInt(2, consultation.getP_id());
        ps.setInt(3, consultation.getPc_id());
        ps.setInt(4, consultation.getU_id());
        ps.setString(5, consultation.getType());
        ps.setDate(6, consultation.getDate());
        ps.setBoolean(7, consultation.isFinished());

        ps.setInt(8, consultation.getId());
        ps.executeUpdate();
    }
}

package rmi.models.patient;

import rmi.database.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PatientDaoImplementation implements PatientDao {

    static Connection con = Postgres.getConnection();

    @Override
    public int add(Patient patient)
            throws SQLException
    {

        String query= "insert into \"Patient\" (name, historyOfSelfHarm, riskIndicator, email, alive) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, patient.getName());
        ps.setBoolean(2, patient.getHistoryOfSelfHarm());
        ps.setString(3, patient.getRiskIndicator());
        ps.setString(4, patient.getEmail());
        ps.setBoolean(5, patient.isAlive());
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public void delete(int id)
            throws SQLException
    {
        String query
                = "delete from \"Patient\" where id =?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }


    @Override
    public Patient getPatient(int id)
            throws SQLException
    {

        String query
                = "select * from \"Patient\" where id= ?";
        PreparedStatement ps
                = con.prepareStatement(query);

        ps.setInt(1, id);
        Patient patient = new Patient();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            patient.setId(rs.getInt("id"));
            patient.setName(rs.getString("name"));
            patient.setHistoryOfSelfHarm(rs.getBoolean("historyOfSelfHarm"));
            patient.setRiskIndicator(rs.getString("riskIndicator"));
            patient.setEmail(rs.getString("email"));
            patient.setAlive(rs.getBoolean("alive"));
        }

        if (check == true) {
            return patient;
        }
        else
            return null;
    }

    @Override
    public List<Patient> getPatients()
            throws SQLException
    {
        String query = "select * from \"Patient\"";
        PreparedStatement ps
                = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Patient> ls = new ArrayList();

        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setName(rs.getString("name"));
            patient.setHistoryOfSelfHarm(rs.getBoolean("historyOfSelfHarm"));
            patient.setRiskIndicator(rs.getString("riskIndicator"));
            patient.setEmail(rs.getString("email"));
            patient.setAlive(rs.getBoolean("alive"));
            ls.add(patient);
        }
        return ls;
    }

    @Override
    public void update(Patient patient)
            throws SQLException
    {

        String query
                = "update \"patient\" set name=?, historyOfSelfHarm=?, riskIndicator=?, email=?, alive=? where id = ?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, patient.getName());
        ps.setBoolean(2, patient.getHistoryOfSelfHarm());
        ps.setString(3, patient.getRiskIndicator());
        ps.setString(4, patient.getEmail());
        ps.setBoolean(5, patient.isAlive());
        ps.setInt(6, patient.getId());

        ps.executeUpdate();
    }
}

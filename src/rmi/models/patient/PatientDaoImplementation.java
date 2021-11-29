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

        String query
                = "insert into patient(name, "
                + "address) VALUES (?, ?)";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, patient.getName());
        ps.setString(2, patient.getAddress());
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public void delete(int id)
            throws SQLException
    {
        String query
                = "delete from patient where id =?";
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
                = "select * from patient where id= ?";
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
            patient.setAddress(rs.getString("address"));
            patient.setHistoryOfSelfHarm(rs.getBoolean("historyOfSelfHarm"));
            patient.setRiskIndicator(rs.getString("riskIndicator"));
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
        String query = "select * from patient";
        PreparedStatement ps
                = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Patient> ls = new ArrayList();

        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setName(rs.getString("name"));
            patient.setAddress(rs.getString("address"));
            patient.setHistoryOfSelfHarm(rs.getBoolean("historyOfSelfHarm"));
            patient.setRiskIndicator(rs.getString("riskIndicator"));
            ls.add(patient);
        }
        return ls;
    }

    @Override
    public void update(Patient patient)
            throws SQLException
    {

        String query
                = "update patient set name=?, "
                + " address=?,historyOfSelfHarm=?,riskIndicator=? where id = ?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, patient.getName());
        ps.setString(2, patient.getAddress());
        ps.setInt(3, patient.getId());
        ps.setBoolean(2, patient.getHistoryOfSelfHarm());
        ps.setString(3, patient.getRiskIndicator());
        ps.executeUpdate();
    }
}

package rmi.models.patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientDao {

    public int add(Patient patient)
            throws SQLException;
    public void delete(int id)
            throws SQLException;
    public Patient getPatient(int id)
            throws SQLException;
    public List<Patient> getPatients()
            throws SQLException;
    public void update(Patient patient)
            throws SQLException;
}

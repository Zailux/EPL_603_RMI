package rmi.models.appointment;

import rmi.models.patient.Patient;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentDao {

    public int add(Appointment appointment)
            throws SQLException;
//    public void delete(int id)
//            throws SQLException;
    public Appointment getAppointment(int id)
            throws SQLException;
    public List<Appointment> getAppointments()
            throws SQLException;
    public List<Appointment> getPatientAppointments(int p_id)
            throws SQLException;
    public void update(Appointment appointment)
            throws SQLException;
}

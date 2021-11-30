
package rmi.models.consultation;

import rmi.models.patient.Patient;
import rmi.models.treatment.Treatment;

import java.sql.SQLException;
import java.util.List;

public interface ConsultationDao {

    public int add(Consultation consultation)
            throws SQLException;
//    public void delete(int id)
//            throws SQLException;
    public Consultation getConsultation(int id)
            throws SQLException;
//    public List<Appointment> getAppointments()
//            throws SQLException;
//    public void update(Appointment appointment)
//            throws SQLException;
}

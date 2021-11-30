package rmi.models.medicine;

import java.sql.SQLException;
import java.util.List;

public interface MedicineDao {

//    public int add(Medicine medicine)
//            throws SQLException;
    //    public void delete(int id)
//            throws SQLException;
    public Medicine getMedicine(int id)
            throws SQLException;
    //    public List<Appointment> getAppointments()
//            throws SQLException;
//    public void update(Consultation consultation)
//            throws SQLException;
}

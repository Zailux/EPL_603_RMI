package rmi.services.appointment;

import rmi.models.appointment.Appointment;
import rmi.models.patient.Patient;
import rmi.models.user.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface AppointmentService extends Remote {
    public Appointment createAppointment(int p_id, int u_id, Date date, Date created, boolean attended) throws RemoteException, SQLException;

    Appointment updateAppointment(int a_id, int p_id, int u_id, Date date, Date created, boolean attended) throws RemoteException, SQLException;

    //    public Appointment updateAppointment(Date date) throws RemoteException, SQLException;
//
    public Appointment fetchAppointment(Integer id) throws RemoteException, SQLException;
//
    public List<Appointment> fetchDoctorAppointments(Integer u_id) throws RemoteException, SQLException;
}

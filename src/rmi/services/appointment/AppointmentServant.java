package rmi.services.appointment;

import rmi.models.appointment.Appointment;
import rmi.models.appointment.AppointmentDaoImplementation;
import rmi.models.patient.Patient;
import rmi.models.patient.PatientDaoImplementation;
import rmi.models.user.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class AppointmentServant extends UnicastRemoteObject implements AppointmentService {

    public AppointmentServant() throws RemoteException {
        super();
    }

    @Override
    public Appointment createAppointment(int p_id, int u_id, Date date, Date created, boolean attended) throws RemoteException, SQLException {
        Appointment appointment = new Appointment(p_id, u_id, date, created, attended);

        AppointmentDaoImplementation appDao = new AppointmentDaoImplementation();
        appDao.add(appointment);
        return appointment;
    }

//    @Override
//    public Appointment updateAppointment(Date date) throws RemoteException, SQLException {
//        Appointment appointment = new Appointment();
//        appointment.setDate(date);
//
//        AppointmentDaoImplementation appDao = new AppointmentDaoImplementation();
//        appDao.update(appointment);
//        return appointment;
//    }
//
//    @Override
//    public Appointment fetchAppointment(Integer id) throws RemoteException, SQLException {
//        AppointmentDaoImplementation appDao = new AppointmentDaoImplementation();
//        Appointment appointment = appDao.getAppointment(id);
//        return appointment;
//    }
//
//    @Override
//    public List<Appointment> fetchAppointments() throws RemoteException, SQLException {
//        AppointmentDaoImplementation appDao = new AppointmentDaoImplementation();
//        List<Appointment> appointments = appDao.getAppointments();
//        return appointments;
//    }
}

package rmi.services.consultation;

import rmi.models.appointment.Appointment;
import rmi.models.appointment.AppointmentDaoImplementation;
import rmi.models.consultation.Consultation;
import rmi.models.consultation.ConsultationDaoImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.SQLException;

public class ConsultationServant extends UnicastRemoteObject implements ConsultationService {

    public ConsultationServant() throws RemoteException {
        super();
    }

    @Override
    public Consultation createConsultation(int t_id, int p_id, int pc_id, int u_id, String type, Date date, boolean finished) throws RemoteException, SQLException {
        Consultation consultation = new Consultation(t_id, p_id, pc_id, u_id, type, date, finished);

        ConsultationDaoImplementation consDao = new ConsultationDaoImplementation();
        consDao.add(consultation);
        return consultation;
    }

    @Override
    public Consultation fetchConsultation(Integer id) throws RemoteException, SQLException {
        ConsultationDaoImplementation consDao = new ConsultationDaoImplementation();
        Consultation consultation = consDao.getConsultation(id);
        return consultation;
    }

    @Override
    public Consultation updateConsultation(int t_id, int p_id, int pc_id, int u_id, String type, Date date, boolean finished) throws RemoteException, SQLException {
        Consultation consultation = new Consultation();
        consultation.setDate(date);

        AppointmentDaoImplementation consDao = new AppointmentDaoImplementation();
        consDao.update(consultation);
        return consultation;
    }
}

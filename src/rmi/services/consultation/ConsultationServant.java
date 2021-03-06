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
    public Consultation createConsultation(int t_id, int p_id, int u_id, String type, Date date, boolean finished) throws RemoteException, SQLException {
        Consultation consultation = new Consultation(t_id, p_id, u_id, type, date, finished);

        ConsultationDaoImplementation consDao = new ConsultationDaoImplementation();
        consDao.add(consultation);
        return consultation;
    }

    @Override
    public Consultation fetchConsultation(Integer id) throws RemoteException, SQLException {
        ConsultationDaoImplementation consDao = new ConsultationDaoImplementation();
        Consultation consultation = consDao.getConsultation(id);
        System.out.println(consultation.getP_id());
        return consultation;
    }

    @Override
    public Consultation updateConsultation(int id, int t_id, int p_id, int u_id, String type, Date date, boolean finished) throws RemoteException, SQLException {
        Consultation consultation = new Consultation(t_id, p_id, u_id, type, date, finished);
        consultation.setId(id);

        ConsultationDaoImplementation consDao = new ConsultationDaoImplementation();
        consDao.update(consultation);
        return consultation;
    }
}

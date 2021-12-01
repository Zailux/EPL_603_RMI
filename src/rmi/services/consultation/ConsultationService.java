package rmi.services.consultation;

import rmi.models.consultation.Consultation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;

public interface ConsultationService extends Remote {

    public Consultation createConsultation(int t_id, int p_id, int u_id, String type, Date date, boolean finished) throws RemoteException, SQLException;

    public Consultation fetchConsultation(Integer id) throws RemoteException, SQLException;

    public Consultation updateConsultation(int id, int t_id, int p_id, int u_id, String type, Date date, boolean finished) throws RemoteException, SQLException;
}

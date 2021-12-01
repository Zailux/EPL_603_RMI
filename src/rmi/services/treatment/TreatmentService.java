package rmi.services.treatment;


import rmi.models.treatment.Treatment;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;

public interface TreatmentService extends Remote {

    public Treatment createTreatment(int u_id, int m_id, Date date, String description, String previousT, int quantity) throws RemoteException, SQLException;

    public Treatment fetchTreatment(Integer id) throws RemoteException, SQLException;

    public int cloneLastTreatment(Integer u_id, Integer c_id) throws RemoteException, SQLException;

    public Treatment fetchPatientLatestTreatment(Integer p_id) throws  RemoteException, SQLException;
}

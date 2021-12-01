package rmi.services.treatment;

import rmi.models.treatment.Treatment;
import rmi.models.treatment.TreatmentDaoImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.SQLException;

public class TreatmentServant extends UnicastRemoteObject implements TreatmentService{

    public TreatmentServant() throws RemoteException {
        super();
    }

    @Override
    public Treatment createTreatment(int u_id, int m_id, Date date, String description, String type, int previousT, int quantity) throws RemoteException, SQLException {
        Treatment treatment = new Treatment(u_id, m_id, date, description, previousT, quantity);

        TreatmentDaoImplementation treaDao = new TreatmentDaoImplementation();
        treaDao.add(treatment);
        return treatment;
    }


    @Override
    public Treatment fetchTreatment(Integer id) throws RemoteException, SQLException {
        TreatmentDaoImplementation treaDao = new TreatmentDaoImplementation();
        Treatment treatment = treaDao.getTreatment(id);
        return treatment;
    }
}

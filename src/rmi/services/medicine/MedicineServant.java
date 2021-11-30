package rmi.services.medicine;

import rmi.models.medicine.Medicine;
import rmi.models.medicine.MedicineDaoImplementation;
import rmi.models.patient.Patient;
import rmi.models.patient.PatientDaoImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class MedicineServant extends UnicastRemoteObject implements MedicineService {
    public MedicineServant() throws RemoteException {
    }

    @Override
    public Medicine fetchMedicine(Integer id) throws RemoteException, SQLException {
        MedicineDaoImplementation medDao = new MedicineDaoImplementation();
        Medicine medicine = medDao.getMedicine(id);
        return medicine;
    }
}

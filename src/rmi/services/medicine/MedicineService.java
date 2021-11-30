package rmi.services.medicine;

import rmi.models.medicine.Medicine;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface MedicineService extends Remote {

    public Medicine fetchMedicine(Integer id) throws RemoteException, SQLException;
}

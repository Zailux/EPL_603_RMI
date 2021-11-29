package rmi;
import rmi.database.models.Patient;
import rmi.database.models.PatientDaoImplementation;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public  class ApplicationServer {
    public static void main(String[] args) throws RemoteException, SQLException {

        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("hello", new rmi.HelloServant());

        Patient patient = new Patient();
        patient.setName("test");
        patient.setAddress("test");

        PatientDaoImplementation patDao = new PatientDaoImplementation();
        patDao.add(patient);
    }
}
package rmi;

import rmi.services.patient.PatientServant;
import rmi.services.user.UserServant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public  class ApplicationServer {
    public static void main(String[] args) throws RemoteException, SQLException {

        Registry registry = LocateRegistry.createRegistry(5099);
        // registry.rebind("hello", new rmi.HelloServant());
        registry.rebind("patient", new PatientServant());
        registry.rebind("user", new UserServant());

//        Patient patient = new Patient();
//        patient.setName("test");
//        patient.setAddress("test");
//
//        PatientDaoImplementation patDao = new PatientDaoImplementation();
//        patDao.add(patient);
    }
}
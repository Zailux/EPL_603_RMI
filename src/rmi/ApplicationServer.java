package rmi;

import rmi.services.consultation.ConsultationServant;
import rmi.services.patient.PatientServant;
import rmi.services.treatment.TreatmentServant;
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
        registry.rebind("consultation", new ConsultationServant());
        registry.rebind("treatment", new TreatmentServant());

//        Patient patient = new Patient();
//        patient.setName("test");
//        patient.setAddress("test");
//
//        PatientDaoImplementation patDao = new PatientDaoImplementation();
//        patDao.add(patient);
    }
}
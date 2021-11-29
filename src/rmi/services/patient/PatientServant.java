package rmi.services.patient;

import rmi.models.patient.Patient;
import rmi.models.patient.PatientDaoImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class PatientServant extends UnicastRemoteObject implements PatientService {

    public PatientServant() throws RemoteException {
        super();
    }

    @Override
    public Patient createPatient(String name, String address, Boolean historyOfSelfHarm, String riskIndicator) throws RemoteException, SQLException {
        Patient patient = new Patient(name, address, historyOfSelfHarm, riskIndicator);

        PatientDaoImplementation patDao = new PatientDaoImplementation();
        patDao.add(patient);
        return patient;
    }
}
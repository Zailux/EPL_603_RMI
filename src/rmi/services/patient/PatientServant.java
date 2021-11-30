package rmi.services.patient;

import rmi.models.patient.Patient;
import rmi.models.patient.PatientDaoImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Patient updatePatient(Integer id, String name, String address, Boolean historyOfSelfHarm, String riskIndicator) throws RemoteException, SQLException {
        Patient patient = new Patient(name, address, historyOfSelfHarm, riskIndicator);
        patient.setId(id);

        PatientDaoImplementation patDao = new PatientDaoImplementation();
        patDao.update(patient);
        return patient;
    }

    @Override
    public Patient fetchPatient(Integer id) throws RemoteException, SQLException {
        PatientDaoImplementation patDao = new PatientDaoImplementation();
        Patient patient = patDao.getPatient(id);
        return patient;
    }

    @Override
    public List<Patient> fetchPatients() throws RemoteException, SQLException {
        PatientDaoImplementation patDao = new PatientDaoImplementation();
        List<Patient> patients = patDao.getPatients();
        return patients;
    }
}
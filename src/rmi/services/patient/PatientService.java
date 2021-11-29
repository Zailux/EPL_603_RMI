package rmi.services.patient;

import rmi.models.patient.Patient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface PatientService extends Remote {
    public Patient createPatient(String name, String address, Boolean historyOfSelfHarm, String riskIndicator) throws RemoteException, SQLException;

    public Patient updatePatient(Integer id, String name, String address, Boolean historyOfSelfHarm, String riskIndicator) throws RemoteException, SQLException;

    public Patient fetchPatient(Integer id, String name) throws RemoteException, SQLException;

    public List<Patient> fetchPatients() throws RemoteException, SQLException;
}

package rmi.services.patient;

import rmi.models.patient.Patient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface PatientService extends Remote {
    public Patient createPatient(String name, String address, Boolean historyOfSelfHarm, String riskIndicator) throws RemoteException, SQLException;
}

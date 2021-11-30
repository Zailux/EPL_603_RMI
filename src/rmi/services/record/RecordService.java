package rmi.services.record;


import rmi.models.treatment.Treatment;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RecordService extends Remote {

    public ResultSet fetchPatientRecords(int id) throws RemoteException, SQLException;
}

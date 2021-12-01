package rmi.services.record;


import rmi.models.record.Record;
import rmi.models.treatment.Treatment;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RecordService extends Remote {

    public List<Record> fetchPatientRecords(int id) throws RemoteException, SQLException;
}

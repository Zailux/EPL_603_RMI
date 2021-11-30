package rmi.services.record;

import rmi.database.Postgres;
import rmi.models.consultation.Consultation;
import rmi.models.treatment.Treatment;
import rmi.models.treatment.TreatmentDaoImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordServant extends UnicastRemoteObject implements RecordService{
    public RecordServant() throws RemoteException {
    }

    static Connection con = Postgres.getConnection();

    @Override
    public ResultSet fetchPatientRecords(int id) throws RemoteException, SQLException {

        String query = "select * from \"Consultation\" C inner join \"Treatment\" T on C.t_id=T.id  where C.p_id=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
}

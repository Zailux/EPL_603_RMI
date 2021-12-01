package rmi.services.record;

import rmi.database.Postgres;
import rmi.models.appointment.Appointment;
import rmi.models.consultation.Consultation;
import rmi.models.record.Record;
import rmi.models.treatment.Treatment;
import rmi.models.treatment.TreatmentDaoImplementation;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordServant extends UnicastRemoteObject implements RecordService, Serializable {
    public RecordServant() throws RemoteException {
    }

    static Connection con = Postgres.getConnection();

    @Override
    public List<Record> fetchPatientRecords(int id) throws RemoteException, SQLException {

        String query = "select C.id AS Consultation_id, C.type AS Consultation_type, C.date AS Consultation_date, finished, T.date AS Treatment_date, T.Description AS Treatment_description, T.quantity AS Treatment_quantity from \"Consultation\" C inner join \"Treatment\" T on C.t_id=T.id  where C.p_id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        List<Record> ls = new ArrayList();

        while (rs.next()) {
            Record record = new Record();
            record.setConsultation_id(rs.getInt("consultation_id"));
            record.setConsultation_type(rs.getString("consultation_type"));
            record.setConsultation_date(rs.getDate("consultation_date"));
            record.setFinished(rs.getBoolean("finished"));
            record.setTreatment_date(rs.getDate("treatment_date"));
            record.setTreatment_description(rs.getString("treatment_description"));
            record.setTreatment_quantity(rs.getInt("treatment_quantity"));
            ls.add(record);
        }
        return ls;
    }
}

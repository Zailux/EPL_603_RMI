package rmi.services.treatment;

import rmi.database.Postgres;
import rmi.models.treatment.Treatment;
import rmi.models.treatment.TreatmentDaoImplementation;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TreatmentServant extends UnicastRemoteObject implements TreatmentService, Serializable {

    static Connection con = Postgres.getConnection();

    public TreatmentServant() throws RemoteException {
        super();
    }

    @Override
    public Treatment createTreatment(int u_id, int m_id, Date date, String description, String previousT, int quantity) throws RemoteException, SQLException {
        PreparedStatement ps;
        Treatment treatment;
        if (previousT.equals("null")) {
            String query= "insert into \"Treatment\"(u_id, m_id, date, description, previousT, quantity) VALUES (?, ?, ?, ?, NULL, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, u_id);
            ps.setInt(2, m_id);
            ps.setDate(3,date);
            ps.setString(4, description);
            ps.setInt(5, quantity);
            treatment = new Treatment(u_id,m_id, date,description,0,quantity);
        }
        else {
        String query= "insert into \"Treatment\"(u_id, m_id, date, description, previousT, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        ps = con.prepareStatement(query);
        ps.setInt(1, u_id);
        ps.setInt(2, m_id);
        ps.setDate(3,date);
        ps.setString(4, description);
        ps.setInt(5, Integer.parseInt(previousT));
        ps.setInt(6, quantity);
            treatment = new Treatment(u_id,m_id, date,description,Integer.parseInt(previousT),quantity);
        }

        int id = ps.executeUpdate();

        treatment.setId(id);
        return treatment;
    }


    @Override
    public Treatment fetchTreatment(Integer id) throws RemoteException, SQLException {
        TreatmentDaoImplementation treaDao = new TreatmentDaoImplementation();
        Treatment treatment = treaDao.getTreatment(id);
        return treatment;
    }
}

package rmi.services.treatment;

import rmi.database.Postgres;
import rmi.models.treatment.Treatment;
import rmi.models.treatment.TreatmentDaoImplementation;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

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

    @Override
    public int cloneLastTreatment(Integer u_id, Integer c_id) throws RemoteException, SQLException {
        String query= "Insert into \"Treatment\" (u_id, m_id, date, description, quantity, previoust) select T.u_id, T.m_id, T.date, T.description, T.quantity, T.previoust from \"Consultation\" C INNER JOIN \"Treatment\" T on C.t_id=T.id where C.p_id=? AND T.u_id=? order by T.date desc limit 1";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, u_id);
        ps.setInt(2, c_id);
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public Treatment fetchPatientLatestTreatment(Integer p_id) throws RemoteException, SQLException {
        String query = "select T.* from \"Consultation\" C inner join \"Treatment\" T on C.t_id=T.id where c.p_id=? ORDER BY T.date DESC LIMIT 1";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, p_id);
        Treatment treatment = new Treatment();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            treatment.setId(rs.getInt("id"));
            treatment.setU_id(rs.getInt("u_id"));
            treatment.setM_id(rs.getInt("m_id"));
            treatment.setDate(rs.getDate("date"));
            treatment.setDescription(rs.getString("description"));
            treatment.setPreviousT(rs.getInt("previousT"));
        }

        if (check == true) {
            return treatment;
        } else
            return null;
    }
}

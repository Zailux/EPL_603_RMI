package rmi.models.treatment;

import rmi.database.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentDaoImplementation implements TreatmentDao{

    static Connection con = Postgres.getConnection();

    @Override
    public int add(Treatment treatment) throws SQLException {

        if (treatment.getPreviousT() == null) {

            String query= "insert into \"Treatment\"(u_id, m_id, date, \"description\", \"previousT\", quantity) VALUES (?, ?, ?, ?, NULL, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, treatment.getU_id());
            ps.setInt(2, treatment.getM_id());
            ps.setDate(3, treatment.getDate());;
            ps.setString(4, treatment.getDescription());
            ps.setInt(5, treatment.getQuantity());
        }
        else {

        }
        String query= "insert into \"Treatment\"(u_id, m_id, date, description, \"previousT\", quantity) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, treatment.getU_id());
        ps.setInt(2, treatment.getM_id());
        ps.setDate(3, treatment.getDate());;
        ps.setString(4, treatment.getDescription());
        ps.setInt(5, treatment.getPreviousT());
        ps.setInt(6, treatment.getQuantity());

        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public Treatment getTreatment(int id) throws SQLException {
        String query = "select * from \"Treatment\" where id= ?";
        PreparedStatement ps= con.prepareStatement(query);

        ps.setInt(1, id);
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
        }
        else
            return null;
    }
}

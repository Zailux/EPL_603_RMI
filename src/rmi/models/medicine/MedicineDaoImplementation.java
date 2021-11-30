package rmi.models.medicine;

import rmi.database.Postgres;
import rmi.models.consultation.Consultation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicineDaoImplementation implements MedicineDao{

    static Connection con = Postgres.getConnection();

    @Override
    public Medicine getMedicine(int id) throws SQLException {
        String query = "select * from \"Medicine\" where id= ?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);
        Medicine medicine = new Medicine();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            medicine.setId(rs.getInt("id"));
            medicine.setDescription(rs.getString("description"));
            medicine.setBrand(rs.getString("brand"));
        }

        if (check == true) {
            return medicine;
        } else
            return null;
    }
}

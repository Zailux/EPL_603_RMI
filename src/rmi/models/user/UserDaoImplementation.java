package rmi.models.user;

import rmi.database.Postgres;
import rmi.models.treatment.Treatment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImplementation implements UserDao {

    static Connection con = Postgres.getConnection();


    @Override
    public User loginUser(String email)
            throws SQLException {

        String query = "select * from user where email =?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, email);
        User user = new User();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
        }

        if (check == true) {
            return user;
        } else
            return null;
    }

    @Override
    public User fetchUser(int id) throws SQLException {
        String query = "select * from \"User\" where id= ?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, id);
        User user = new User();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
        }

        if (check == true) {
            return user;
        } else
            return null;
    }

}

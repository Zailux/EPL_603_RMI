package rmi.models.user;

import rmi.database.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImplementation implements UserDao {

    static Connection con = Postgres.getConnection();


    @Override
    public User loginUser(String password, String username)
            throws SQLException
    {

        String query
                = "select * from user where password=? and username =?";
        PreparedStatement ps
                = con.prepareStatement(query);

        ps.setString(1, password);
        ps.setString(2,username);
        User user = new User();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setAddress(rs.getString("address"));
            user.setRole(rs.getString("role"));
        }

        if (check == true) {
            return user;
        }
        else
            return null;
    }
}

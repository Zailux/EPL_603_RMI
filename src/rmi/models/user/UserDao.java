package rmi.models.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public User loginUser(String password, String username)
            throws SQLException;
}

package rmi.models.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    public User loginUser(String email) throws SQLException;

    public User fetchUser(int id) throws SQLException;
}

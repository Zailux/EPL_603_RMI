package rmi.services.user;

import rmi.models.user.User;
import rmi.models.user.UserDaoImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class UserServant extends UnicastRemoteObject implements UserService {

    public UserServant() throws RemoteException {
        super();
    }

    @Override
    public User loginUser(String username, String password) throws RemoteException, SQLException {
        UserDaoImplementation userDao = new UserDaoImplementation();
        User user = userDao.loginUser(username, password);
        return user;
    }
}

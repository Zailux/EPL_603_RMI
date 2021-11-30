package rmi.services.user;

import rmi.models.treatment.Treatment;
import rmi.models.treatment.TreatmentDaoImplementation;
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
    public User loginUser(String email) throws RemoteException, SQLException {
        UserDaoImplementation userDao = new UserDaoImplementation();
        User user = userDao.loginUser(email);
        return user;
    }

    @Override
    public User fetchUser(int id) throws RemoteException, SQLException {
        UserDaoImplementation userDao = new UserDaoImplementation();
        User user = userDao.fetchUser(id);
        return user;
    }
}

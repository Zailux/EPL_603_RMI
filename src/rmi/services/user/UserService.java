package rmi.services.user;

import rmi.models.patient.Patient;
import rmi.models.user.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface UserService extends Remote {
    public User loginUser(String email) throws RemoteException, SQLException;

    public User fetchUser(int id) throws RemoteException,SQLException;
}

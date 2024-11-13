package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    void hallo() throws RemoteException;

    int getPlayersOnline() throws RemoteException;

    void authenticate(SpielInterface client, String password) throws RemoteException;

    void logout() throws RemoteException;
}

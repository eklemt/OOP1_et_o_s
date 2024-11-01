package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpielInterface extends Remote {
    void hallo() throws RemoteException;

    int getPlayersOnline() throws RemoteException;
}

package common;

import client.SpielClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpielInterface extends Remote {
    void hallo() throws RemoteException;

    int getPlayersOnline() throws RemoteException;

    void authenticate(SpielClient client, String password) throws RemoteException;
}

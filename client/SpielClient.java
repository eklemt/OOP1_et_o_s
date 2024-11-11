package client;

import common.ServerInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SpielClient {
    private ServerInterface serverSpiel;

    public SpielClient() throws RemoteException {
        try
        {
            Registry registry = LocateRegistry.getRegistry();
            serverSpiel = (ServerInterface) registry.lookup("Quizspiel");
            System.err.println("Connected to: " + serverSpiel);

            // Begin client-side game logic
            Spiel quizSpiel = new Spiel(serverSpiel);
            quizSpiel.authenticate();
        }
        catch (RemoteException e)
        { // Fehler bei Aufrufvermittlung behandeln
            System.err.println(e);
        }
        catch (NotBoundException e)
        { // kein Server mit logischem Namen �?Adder“ registriert
            System.err.println(e);
        }
    }

    public static void main(String[] args) throws RemoteException {
        SpielClient client = new SpielClient();

    }


}

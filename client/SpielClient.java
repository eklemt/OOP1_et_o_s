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
            String serverIP = "192.168.249.175";
            int port = 1099;
            Registry registry = LocateRegistry.getRegistry(serverIP, port);
            serverSpiel = (ServerInterface) registry.lookup("rmi://localhost/Quizspiel");
            System.err.println("Connected to: " + serverSpiel);

            // Begin client-side game logic
            Spiel quizSpiel = new Spiel(serverSpiel, this);
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

    public void logout() {
        try {
            if (serverSpiel != null) {
                // Optional: define server-side cleanup if needed
                serverSpiel.logout();  // Optional if defined on the server side
                serverSpiel = null;
                System.out.println("Client logged out successfully.");
            } else {
                System.out.println("Already logged out or not connected.");
            }
        } catch (RemoteException e) {
            System.err.println("Error during logout: " + e);
        } finally {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws RemoteException {
        SpielClient client = new SpielClient();

    }


}

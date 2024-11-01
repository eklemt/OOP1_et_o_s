package client;

import common.SpielInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SpielClient {
    private SpielInterface serverSpiel;

    public SpielClient() throws RemoteException {
        try
        {
            Registry registry = LocateRegistry.getRegistry();
            serverSpiel = (SpielInterface) registry.lookup("Quizspiel");
            System.err.println("Connected to: " + serverSpiel);
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

    public void starten() {
        try {
            this.serverSpiel.hallo();  // Calls the server method to start the game
            System.out.println("Client: Game has started on the server.");
            int currentPlayersInGame = this.serverSpiel.getPlayersOnline();

            if(currentPlayersInGame == 1) {
                System.out.println("At the moment " + currentPlayersInGame + " player is online - you are alone.");
            } else {
                System.out.println("At the moment " + currentPlayersInGame + " players are online");
            }



            // Begin client-side game logic
            Spiel quizSpiel = new Spiel();
            quizSpiel.spielen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {
        SpielClient client = new SpielClient();
        client.starten();  // Start the game sequence

    }


}

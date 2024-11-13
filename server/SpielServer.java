package server;


import common.ServerInterface;
import common.SpielInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SpielServer extends UnicastRemoteObject implements ServerInterface {
    private int playerCount = 0;
    private String password = "VS";

    public SpielServer() throws RemoteException
    {
        super();
    }

    public void hallo() throws RemoteException {
        System.out.println("Hello from Server ...");
        playerCount++;
    }

    public int getPlayersOnline() throws RemoteException {
        return playerCount;
    }

    public void authenticate(SpielInterface client, String passwort)
            throws RemoteException
    {
        assert(passwort.equals(this.password));
        System.err.println("Passwort korrekt!");
        client.starten();
    }

    public void logout() {
        if(playerCount > 0) {
            playerCount--;
        }
        System.out.println("A player has logged off. Current player online: " + playerCount);
    }

    public static void main(String[] args) {
        try {
            // Start the registry
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            System.out.println("Registry started...");

            // Create and bind the SpielServer instance
            SpielServer server = new SpielServer();
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Quizspiel", server);
            System.out.println("SpielServer is running and bound to registry...");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

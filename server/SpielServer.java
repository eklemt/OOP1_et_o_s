package server;

import common.SpielInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

public class SpielServer extends UnicastRemoteObject implements SpielInterface {

    public SpielServer() throws RemoteException
    {
        super();
    }

    public void hallo() {
        System.out.println("Starting the game on the server...");
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

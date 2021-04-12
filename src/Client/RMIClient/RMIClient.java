package Client.RMIClient;

import Server.RMIServer.ServerImpl;
import Server.server.Server;
import callbackexample.shared.UpperCaseClient;
import callbackexample.shared.UpperCaseServer;
import shared.transferobjects.LogEntry;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClient implements ClientModel
{
    private ServerImpl server;
    private PropertyChangeSupport support;
    /** Client constructor, requires a name/username for client */
    public RMIClient(String name)
    {
        this.name = name;
        support = new PropertyChangeSupport(this);


    }

    @Override
    public void startClient() {

        try {
            UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (ServerImpl) registry.lookup("ChatServer");
            server.registerClient(this);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<LogEntry> getLog() {
        try {
            return server.getLogs();
        } catch (RemoteException e) {
            throw new RuntimeException("Could not contact server");
        }

    }


    @Override public void addPropertyChangeListener(String name,
                                                    PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(name, listener);
    }

    public void toUpperCase(String argument) {
        try {
            server.toUpperCase(argument, this);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not contact server");
        }
    }

    @Override
    public void uppercaseResult(String result) {
        System.out.println("Result > " + result);
    }
}

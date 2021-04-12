package Server.RMIServer;

import Client.networking.ClientModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpperCaseServer extends Remote {
    void toUpperCase(String str, ClientModel client) throws RemoteException;
}

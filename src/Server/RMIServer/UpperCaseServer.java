package Server.RMIServer;

import Client.networking.ClientModel;
import shared.Message;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpperCaseServer extends Remote
{

  void connectedMessageFromClient(Message text) throws RemoteException;
  void disconnectionMessageFromClient(ClientModel dontBroadcastToMe)
      throws RemoteException;
  void normalMessageFromClient(String result, ClientModel dontBroadcastToMe)
      throws RemoteException;
  void getUsersMessageFromClient(ClientModel dontBroadcastToMe) throws RemoteException;
  void registerClient(ClientModel clientToRegister) throws RemoteException;

}

package Server.server;

import Client.networking.ClientModel;
import shared.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote
{
  void connectedMessageFromClient(Message text) throws RemoteException;
  void normalMessageFromClient(Message result)
      throws RemoteException;
  void getUsersMessageFromClient(ClientModel clientModel)
      throws RemoteException;
  void registerClient(ClientModel clientToRegister) throws RemoteException;
  void closeChat(ClientModel clientToDelete) throws RemoteException;
}

package Server.server;

import Client.networking.ClientModel;
import shared.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote
{
  /** Method to run when a client connects, send a message to clients */
  void connectedMessageFromClient(Message text) throws RemoteException;

  /** Receive message from clients */
  void normalMessageFromClient(Message result)
      throws RemoteException;

  /** Send amount of users to client requesting it */
  void getUsersMessageFromClient(ClientModel clientModel)
      throws RemoteException;

  /** Register a client to the ArrayList object containing clients **/
  void registerClient(ClientModel clientToRegister) throws RemoteException;

  /** Method to run when client disconnects, sends a message to clients */
  void closeChat(ClientModel clientToDelete) throws RemoteException;
}

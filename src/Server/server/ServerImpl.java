package Server.server;

import Client.networking.ClientModel;
import shared.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements ChatServer
{
  private List<ClientModel> clientsForBroadcast;

  /** Constructor **/
  public ServerImpl() throws RemoteException
  {
    /** Export this class to clients **/
    UnicastRemoteObject.exportObject(this, 0);
    /** Initialize ArrayList object for clients **/
    clientsForBroadcast = new ArrayList<>();
  }

  @Override public void registerClient(ClientModel clientToRegister)
  {
    clientsForBroadcast.add(clientToRegister);
  }

  /** Method to run when client disconnects, sends a message to clients */
  @Override public void closeChat(ClientModel clientToDelete)
      throws RemoteException
  {
    clientsForBroadcast.remove(clientToDelete);

    for (ClientModel client : clientsForBroadcast)
    {
      client.receiveMessage(new Message("Server>>>",
          clientToDelete.getUsername() + " disconnected to he server ", false));
    }
  }

  /** Receive message from clients */
  @Override public void normalMessageFromClient(Message result)
      throws RemoteException
  {
    for (ClientModel client : clientsForBroadcast)
    {
      System.out.println(client.getUsername());
      client.receiveMessage(result);
    }
  }

  /** Method to run when a client connects, send a message to clients */
  @Override public void connectedMessageFromClient(Message text)
      throws RemoteException
  {
    for (ClientModel client : clientsForBroadcast)
    {

      client.receiveMessage(
          new Message("Server>>>", text.getUser() + " connected to the server ",
              false));
    }
  }

  /** Send amount of users to client requesting it */
  @Override public void getUsersMessageFromClient(ClientModel clientModel)
      throws RemoteException
  {
    String str = "";

    for (ClientModel client : clientsForBroadcast)
    {
      try
      {
        str += client.getUsername() + ", ";
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
    clientModel.receiveMessage(new Message("Server>>>",
        "There is  " + clientsForBroadcast.size() + " user connected \n" + str,
        false));
  }
}

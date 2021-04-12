package Server.RMIServer;

import Client.RMIClient.ClientModel;
import shared.Message;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements ChatServer
{
  private List<ClientModel> clientsForBroadcast;

  public ServerImpl() throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    clientsForBroadcast = new ArrayList<>();
  }

  @Override public void registerClient(ClientModel clientToRegister)
  {

    try
    {
      System.out.println(clientToRegister.getUsername());
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    clientsForBroadcast.add(clientToRegister);
    System.out.println(clientsForBroadcast.size()+"Size");
  }

  /** Receive message from clients */
  @Override public void normalMessageFromClient(Message result,
      ClientModel dontBroadcastToMe)
  {
    for (ClientModel client : clientsForBroadcast)
    {
      try
      {
        System.out.println(client.getUsername());
        client.receiveMessage(result);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
    System.out.println(result);
  }

  /** Method to run when a client connects, send a message to clients */
  @Override public void connectedMessageFromClient(Message text)
  {
    text.getUser();

    for (ClientModel client : clientsForBroadcast)
    {
      try
      {
        client.sendMessage(new Message("Server>>>",
            text.getUser() + " connected to he server ", false));
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }

  }

  /** Method to run when client disconnects, sends a message to clients */
  @Override public void disconnectionMessageFromClient(
      ClientModel dontBroadcastToMe)
  {

    for (ClientModel client : clientsForBroadcast)
    {
      if (client.equals(dontBroadcastToMe))
        continue;

      try
      {
        client.sendMessage(
            new Message("Server>>>", "username" + " disconnected to he server ",
                false));
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }
    }
  }

  /** Send amount of users to client requesting it */
  @Override public void getUsersMessageFromClient(ClientModel dontBroadcastToMe)
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

    System.out.println(str);
    try
    {
      dontBroadcastToMe.sendMessage(new Message("Server>>>",
          "There is  " + clientsForBroadcast.size() + " user connected \n"
              + str, false));
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }

  }

}

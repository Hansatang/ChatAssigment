package Client.networking;

import Server.server.ChatServer;
import shared.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient implements ClientModel
{
  private boolean running = true;
  private String name;
  private PropertyChangeSupport support;
  private ChatServer server;
  private Registry registry;

  /** Client constructor, requires a name/username for client */
  public RMIClient(String name) throws RemoteException
  {
    this.name = name;
    UnicastRemoteObject.exportObject(this, 0);
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient() throws RemoteException
  {
    registry = LocateRegistry.getRegistry("localhost", 1099);
    try
    {
      server = (ChatServer) registry.lookup("ChatServer");
    }
    catch (NotBoundException e)
    {
      e.printStackTrace();
    }
    server.registerClient(this);
    server.connectedMessageFromClient(new Message(name, "Connection", true));
  }

  /** Send a Message object from client to server */
  @Override public void sendMessage(Message text)
  {
    try
    {
      server.normalMessageFromClient(text, this);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public String getUsername()
  {
    return name;
  }

  /** Sends a property change to listeners to check if there are any unread messages */
  @Override public void receiveMessage(Message message)
  {
    System.out.println("Client receive" + message);
    support.firePropertyChange("NewMessage", null, message);
  }

  @Override public void getUsers()
  {
    try
    {
      server.getUsersMessageFromClient(this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
  /** Close the client-server connection from the client side */
  @Override public void closeChat() throws RemoteException
  {
    server.closeChat(this);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void deactivateClient()
  {
//    try
//    {
//      registry.unbind("ChatServer");
//    }
//    catch (RemoteException | NotBoundException e)
//    {
//      e.printStackTrace();
//    }
  }
}

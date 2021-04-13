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
  /** Declaring objects **/
  private Registry registry;
  private ChatServer server;
  private String name;
  private PropertyChangeSupport support;

  /** Client constructor, requires a name/username for client */
  public RMIClient(String name) throws RemoteException
  {
    this.name = name;
    UnicastRemoteObject.exportObject(this, 0);
    support = new PropertyChangeSupport(this);
  }

  /** Start client side of RMI **/
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
      server.normalMessageFromClient(text);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /** Get method for client username **/
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

  /** Request information about how many users there are connected **/
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
  /** Add a listener to the client **/
  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }
}

package Client.model;

import Client.networking.RMIClient;
import Client.networking.ClientModel;
import shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class DataModelManager implements DataModel
{
  /** Declare objects **/
  private ClientModel client;
  private String name;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  /** Client sends a message **/
  @Override public void sendMessage(Message text)
  {
    try
    {
      client.sendMessage(text);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /** Update when evt occurs **/
  @Override public void update(PropertyChangeEvent evt)
  {
    support.firePropertyChange("NewMessage", null, evt.getNewValue());
  }

  /** Create a client with the name of the parameter **/
  @Override public void createClient(String name)
  {
    try
    {
      client = new RMIClient(name);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    try
    {
      client.addPropertyChangeListener("NewMessage", this::update);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
    this.name = name;
    try
    {
      client.startClient();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /** Add a listener to client **/
  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }


  /** Get method for client username **/
  @Override public String getUsername()
  {
    return name;
  }

  /** Method to send when client wants to retrieve the amount of users connected in the chat **/
  @Override public void getUsers()
  {
    try
    {
      client.getUsers();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  /** Close client side of the chat **/
  @Override public void closeChat()
  {
    try
    {
      client.closeChat();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }
}

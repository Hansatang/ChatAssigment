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
  private ClientModel client;
  private String name;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);


  @Override public void sendMessage(Message text)
  {
    try
    {
      System.out.println(33);
      client.sendMessage(text);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void update(PropertyChangeEvent evt)
  {
    support.firePropertyChange("NewMessage", null, evt.getNewValue());
  }

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

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void deactivateClient()
  {
    try
    {
      client.deactivateClient();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public String getUsername()
  {
    return name;
  }

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

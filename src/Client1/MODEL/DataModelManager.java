package Client1.MODEL;

import Client1.Networking.Client;
import Shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DataModelManager implements DataModel
{


  private Client client;
  private String name;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  @Override public void sendMessage(Message text)
  {
    client.sendMessage(text);
  }

  @Override public void update(Message message)
  {
    support.firePropertyChange("received", null, message);
  }

  @Override public void createClient(String name)
  {
    client = new Client(this, name);
    this.name = name;
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void decreateClient()
  {
    client.deactivateUser();
  }

  @Override public String getUsername()
  {
    return name;
  }

}

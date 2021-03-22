package Client.model;

import Client.networking.Client;
import Client.networking.ClientModel;
import shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DataModelManager implements DataModel
{
  private ClientModel client;
  private String name;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  @Override public void sendMessage(Message text)
  {
    client.sendMessage(text);
  }

  @Override public void update(PropertyChangeEvent evt)
  {
    support.firePropertyChange("NewMessage", null, evt.getNewValue());
  }

  @Override public void createClient(String name)
  {
    client = new Client(name);
    client.addPropertyChangeListener("NewMessage", this::update);
    this.name = name;
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void decreateClient()
  {
    client.deactivateClient();
  }

  @Override public String getUsername()
  {
    return name;
  }

}

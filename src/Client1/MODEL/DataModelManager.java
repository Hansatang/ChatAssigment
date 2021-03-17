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
    client.sendMsg(text);
  }

  @Override public void update(PropertyChangeEvent propertyChangeEvent)
  {
    System.out.println("Datamodel receives"+propertyChangeEvent.getNewValue());
    support.firePropertyChange(propertyChangeEvent);
  }

  @Override public void createClient(String name)
  {
    client = new Client(this, name);
    this.name = name;
    System.out.println(1);
    client.addPropertyChangeListener("updated", this::update);
    System.out.println(2);
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public String getUsername()
  {
    return name;
  }

}

package Client1.MODEL;

import Client1.Networking.Client;

import Shared.Message;

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

  @Override public void update()
  {

  }

  @Override public void createClient(String name)
  {
    client = new Client(this, name);
    this.name = name;
  }

  @Override public String getUsername()
  {
    return name;
  }

}

package Client1.MODEL;

import Client1.Networking.Client;

import Shared.Message;

import java.beans.PropertyChangeSupport;

public class DataModelManager implements DataModel
{

  private Client client;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  @Override public void sendMessage(String text)
  {
      Message message=new Message(client.)

  }

  @Override public void update()
  {

  }

  @Override public void createClient()
  {
    client = new Client(this);
  }

  @Override public void setUserName(String name)
  {
    client.setUsername(name);
  }
}

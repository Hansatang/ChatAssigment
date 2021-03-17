package Client1.MODEL;

import Client1.Networking.Client;

import java.beans.PropertyChangeSupport;

public class DataModelManager implements DataModel
{

  private Client client;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  @Override public void sendMessage(String text)
  {

  }

  @Override public void update()
  {

  }

  @Override public void createClient()
  {
    client = new Client(2910);
  }
}

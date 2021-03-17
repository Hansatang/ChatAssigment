package Client1.Networking;

import Client1.MODEL.DataModelManager;
import Client1.ViewModel.ViewModelLogin;
import Shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements ClientModel, PropertyChangeListener
{

  private String hostname = "localhost";
  private Socket socket;
  private ObjectOutputStream objectOutputStream;
  private boolean running = true;
  private DataModelManager manager;
  private String name;

  public Client(DataModelManager manager, String name)
  {
    this.manager = manager;
    this.name = name;

    try
    {
      this.socket = new Socket("localhost", 2910);
      this.objectOutputStream = new ObjectOutputStream(
          socket.getOutputStream());
    }
    catch (IOException e)
    {
      throw new RuntimeException("Unable to connect to the server");
    }
    sendMsg(new Message(name, "Fuck me", false));
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {

  }

  @Override public void sendMsg(Message text)
  {
    try
    {
      objectOutputStream.writeObject(text);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

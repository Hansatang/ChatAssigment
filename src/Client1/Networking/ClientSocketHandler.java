package Client1.Networking;

import Client1.PropertyChange.PropertyChangeSubject;
import Shared.Message;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable, PropertyChangeSubject {
  private ObjectInputStream inputStream;
  private Socket socket;
  private Client client;

  public ClientSocketHandler(Socket socket, Client client)
  {
    this.socket = socket;
    this.client = client;
  }

  @Override public void run()
  {
    try
    {
      inputStream = new ObjectInputStream(socket.getInputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    while (true)
    {
      try
      {
        Message message = (Message) inputStream.readObject();
        System.out.println(message.getMessage());
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void addPropertyChangeListener(String name, PropertyChangeListener listener) {

  }
}

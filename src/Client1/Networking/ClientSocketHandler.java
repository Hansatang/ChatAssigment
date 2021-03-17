package Client1.Networking;

import Shared.Message;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{

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
      this.inputStream = new ObjectInputStream(socket.getInputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    while (client.isRunning())
    {
      try
      {
        Message message = (Message) inputStream.readObject();
        System.out.println(message.getMessage());
        client.receive(message);
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
    }
  }


}

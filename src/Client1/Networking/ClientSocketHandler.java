package Client1.Networking;

import Shared.Message;

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
      inputStream = new ObjectInputStream(socket.getInputStream());
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
        client.receiveMessage(message);
      }
      catch (IOException | ClassNotFoundException e)
      {
        System.out.println("Connection lost");
      }
    }
  }

}

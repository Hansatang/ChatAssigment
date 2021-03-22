package Client.networking;

import shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
  private ObjectInputStream inputStream;
  private Socket socket;
  private Client client;

  /** Constructor  */
  public ClientSocketHandler(Socket socket, Client client)
  {
    this.socket = socket;
    this.client = client;
  }

  /** Run method for the thread */
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
        System.out.println("Receive message " + message.getMessage());
        client.receiveMessage(message);
      }
      catch (IOException | ClassNotFoundException e)
      {
        System.out.println("Connection lost");
      }
    }
  }

}

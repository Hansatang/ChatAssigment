package Client.networking;

import shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
  private ObjectInputStream inputStream;
  private Socket socket;
  private RMIClient RMIClient;

  /** Constructor  */
  public ClientSocketHandler(Socket socket, RMIClient RMIClient)
  {
    this.socket = socket;
    this.RMIClient = RMIClient;
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
    while (RMIClient.isRunning())
    {
      try
      {
        Message message = (Message) inputStream.readObject();
        System.out.println("Receive message " + message.getMessage());
        RMIClient.receiveMessage(message);
      }
      catch (IOException | ClassNotFoundException e)
      {
        System.out.println("Connection lost");
      }
    }
  }

}

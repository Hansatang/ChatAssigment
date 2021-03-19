package Networking;


import com.google.gson.Gson;
import model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{

  private BufferedReader inputStream;
  private Socket socket;
  private Client client;
  private Gson json;

  public ClientSocketHandler(Socket socket, Client client)
  {
    this.socket = socket;
    this.client = client;
    json = new Gson();
    try
    {
      this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {

    while (client.isRunning())
    {
      try
      {
        String str = inputStream.readLine();
        Message msg = json.fromJson(str, Message.class);
        client.receiveMessage(msg);
      }
      catch (IOException e)
      {
        System.out.println("Connection lost");
      }
    }
  }

}

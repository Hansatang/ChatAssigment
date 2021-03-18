package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class User
{
  private Socket socket;
  private String username;
  private ObjectOutputStream out;
  private ObjectInputStream in;

  public User(Socket socket)
  {
    this.socket = socket;

    try
    {
      this.out = new ObjectOutputStream(socket.getOutputStream());
      this.in = new ObjectInputStream(socket.getInputStream());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void sendMessage(Message text)
  {
    try
    {
      out.writeObject(text);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public Message receiveMessage()
  {
    try
    {
      return (Message) in.readObject();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }
}

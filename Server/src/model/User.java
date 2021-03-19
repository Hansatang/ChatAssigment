package model;

import com.google.gson.Gson;
import server.Server;

import java.io.*;
import java.net.Socket;

public class User
{
  private Socket socket;
  private String username;
  private Gson json;
  private PrintWriter out;
  private BufferedReader in;

  public User(Socket socket)
  {
    this.socket = socket;
    json = new Gson();

    try
    {
      this.in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      this.out = new PrintWriter(socket.getOutputStream(), true);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  public void sendMessage(Message text)
  {
    out.println(json.toJson(text));
  }

  public Message receiveMessage()
  {
    try
    {
      String msg = in.readLine();
      Message message = json.fromJson(msg, Message.class);

      if (message.isCommand())
      {
        if (message.getMessage().equals("Listener"))
        {
          this.username = message.getUser();
          message = new Message("Server>>>", username + " connected to he server ", false);
        }
        else  if (message.getMessage().equals("exit"))
        {
          message = new Message("Server>>>", username + " disconnected from the server ", true);
        }
      }
      return message;
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public boolean gotMessage()
  {
    try
    {
      return in.ready();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  public String getUsername()
  {
    return username;
  }

  public void close()
  {
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }


  }
}

package Server1.Server;

import Shared.Message;

import java.io.*;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{

  private Socket socket;
  private ObjectOutputStream out;
  private ObjectInputStream in;

  private String username;
  private Message message;
  private Server server;
  private boolean connected;

  public ServerSocketHandler(String name, Socket socket, ObjectInputStream in,
      ObjectOutputStream out, Server server)
  {
    this.socket = socket;
    this.in = in;
    this.out = out;
    this.username = name;
    this.server = server;

    this.connected = true;
  }

  public void userConnected()
  {
    for (ServerSocketHandler client : server.getPool().getConnections())
    {
      try
      {
        client.out.writeObject(
            new Message("Server>>>", username + " connected to he server ",
                false));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void run()
  {
    while (connected)
    {
      try
      {
        message = (Message) in.readObject();
        System.out.println("Message"+message.getMessage());
        try
        {
          if (!message.isCommand())
          {
            for (ServerSocketHandler client : server.getPool().getConnections())
            {
              client.out.writeObject(message);
            }
          }
          else
          {
            if (message.getMessage().equals("exit"))
            {
              System.out.println("LALA");
              for (ServerSocketHandler client : server.getPool().getConnections())
              {
                client.out.writeObject(   new Message("Server>>>", message.getUser() + " has left the server ",
                    false));
              }
              close();
            }
            if (message.getMessage().equals("Users"))
            {
              String str = "";
              for (ServerSocketHandler client : server.getPool()
                  .getConnections())
              {
                str += client.username + ", ";
              }
              System.out.println(str);

              for (ServerSocketHandler client : server.getPool()
                  .getConnections())
              {
                if (client.username.equals(username))
                {
                  client.sendMsg(
                      "There is  " + server.getPool().getConnections().size()
                          + " user connected \n" + str);
                }
              }
            }
          }
        }
        catch (NullPointerException e)
        {
        }
      }
      catch (IOException | ClassNotFoundException e)
      {
        //    System.out.println("No streams are incoming");
      }
    }
  }

  public void sendMsg(String msg)
  {
    try
    {
      out.writeObject(new Message("Server>>>", msg, false));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public String getUsername()
  {
    return username;
  }

  private void close()
  {
    try
    {
      System.out.println(5);
      this.connected = false;
      System.out.println(6);

      server.getPool().removeConn(this);
      System.out.println(7);
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

package Server1.Server;

import Server1.Domain.Message;

import java.io.*;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{

  private Socket socket;
  private ObjectOutputStream out;
  private ObjectInputStream in;

  private String username;
  private Message message;
  private boolean connected;

  public ServerSocketHandler(String name, Socket socket, ObjectInputStream in,
      ObjectOutputStream out)
  {
    this.socket = socket;
    this.in = in;
    this.out = out;
    this.username = name;

    this.connected = true;
  }

  public void run()
  {
    while (connected)
    {
      try
      {
        message = (Message) in.readObject();
        System.out.println(message.getMessage());
        try
        {
          if (!message.isCommand())
          {
            for (ServerSocketHandler client : Server.clientList)
            {
              client.out.writeObject(message);
            }
          }
          else
          {
            if (message.getMessage().equals("exit"))
            {
              close();
            }
            if (message.getMessage().equals("Users"))
            {
              String str = "";
              for (ServerSocketHandler client : Server.clientList)
              {
                str += client.username + ", ";
              }
              System.out.println(str);

              for (ServerSocketHandler client : Server.clientList)
              {
                if (client.username.equals(username))
                {
                  client.sendMsg("There is  " + Server.clientList.size()
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
        System.out.println("No streams are incoming");
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
    ;
  }

  public String getUsername()
  {
    return username;
  }

  private void close()
  {
    try
    {
      this.connected = false;
      in.close();
      out.close();
      socket.close();
      Server.remove(this);

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

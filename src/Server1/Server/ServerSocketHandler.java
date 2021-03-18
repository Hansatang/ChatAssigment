package Server1.Server;

import Server1.MODEL.DataModelS;
import Shared.Message;

import java.beans.PropertyChangeEvent;
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
  private DataModelS dataModelS;
  private boolean connected;

  public ServerSocketHandler(Socket socket, DataModelS dataModelS,
      Server server)
  {
    this.dataModelS = dataModelS;

    this.socket = socket;
    try
    {
      this.in = new ObjectInputStream(socket.getInputStream());
      this.out = new ObjectOutputStream(socket.getOutputStream());

      System.out.println(server.getPool().getConnections().size());
      server.getPool().addConn(this);
      System.out.println(server.getPool().getConnections().size());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    this.server = server;

    this.connected = true;
  }

  public void run()
  {

    while (connected)
    {
      try
      {
        message = (Message) in.readObject();
        System.out.println("Message " + message.getMessage());
        try
        {
          if (message.getMessage().equals("Listener"))
          {
            this.username = message.getUser();
            dataModelS.addListener("NewMessage", this::NewMessage);
          }
          else if (message.getMessage().equals("Null"))
          {
            for (ServerSocketHandler client : server.getPool().getConnections())
            {
              client.dataModelS.sendMessage(new Message("Server>>>",
                  username + " connected to he server ", false));
            }
          }
          else if (!message.isCommand())
          {
            for (ServerSocketHandler client : server.getPool().getConnections())
            {
              client.dataModelS.sendMessage(message);
            }
          }
          else
          {
            if (message.getMessage().equals("exit"))
            {
              System.out.println(8);

              for (ServerSocketHandler client : server.getPool()
                  .getConnections())
              {
                if (!client.username.equals(message.getUser()))
                {
                  client.out.writeObject(new Message("Server>>>",
                      username + " disconnected to he server ", false));
                }
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
              System.out.println(username);
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

  public void NewMessage(PropertyChangeEvent propertyChangeEvent)
  {
    try
    {
      for (ServerSocketHandler client : server.getPool().getConnections())
      {
        if (client.username.equals(username))
        {
          client.out.writeObject(propertyChangeEvent.getNewValue());
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
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
      server.getPool().removeConn(this);
      in.close();
      out.close();
      socket.close();
      this.connected = false;
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

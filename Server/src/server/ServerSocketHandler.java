package server;

import model.Message;
import utility.ServerOperationModel;

import java.beans.PropertyChangeEvent;
import java.io.*;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private Socket socket;
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private String username;
  private ServerOperationModel serverOperationModel;
  private boolean connected;

  public ServerSocketHandler(Socket socket, ServerOperationModel dataModelS)
  {
    this.serverOperationModel = dataModelS;

    this.socket = socket;
    try
    {
      this.in = new ObjectInputStream(socket.getInputStream());
      this.out = new ObjectOutputStream(socket.getOutputStream());

      System.out.println(Server.pool.getConnections().size());
      Server.pool.addConn(this);
      System.out.println(Server.pool.getConnections().size());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    this.connected = true;
  }

  public void run()
  {
    while (connected)
    {
      try
      {

        Message message = (Message) in.readObject();
        System.out.println("Message " + message.getMessage());
        if (message.getMessage().equals("Listener"))
        {
          username = message.getUser();
          serverOperationModel
              .addListener(ServerOperationModel.newMessagePropertyName,
                  this::broadcast);
          serverOperationModel
              .addListener(ServerOperationModel.newServerMessagePropertyName,
                  this::serverMessage);
          serverOperationModel
              .addListener(ServerOperationModel.newGreetingMessagePropertyName,
                  this::greetingMessage);
        }
        else if (message.getMessage().equals("Null"))
        {
          for (ServerSocketHandler client : Server.pool.getConnections())
          {
            if (client.username.equals(message.getUser()))
            {
              System.out.println("Username" + username);
              serverOperationModel.greetingMessage(message.getUser(),
                  new Message("Server>>>",
                      username + " connected to he server ", false));
            }
          }
        }
        else if (!message.isCommand())
        {
          for (ServerSocketHandler client : Server.pool.getConnections())
          {
            if (client.username.equals(message.getUser()))
            {
              client.serverOperationModel.sendMessage(message);
            }
          }
        }
        else
        {
          if (message.getMessage().equals("exit"))
          {
            for (ServerSocketHandler client : Server.pool.getConnections())
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
            StringBuilder str = new StringBuilder();
            for (ServerSocketHandler client : Server.pool.getConnections())
            {
              str.append(client.username).append(", ");
            }
            System.out.println(str);

            serverOperationModel.serverMessage(message.getUser(),
                new Message("Server>>>",
                    "There is  " + Server.pool.getConnections().size()
                        + " user connected \n" + str, false));
          }
        }

      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
        //    System.out.println("No streams are incoming");
      }
    }
  }

  private void greetingMessage(PropertyChangeEvent propertyChangeEvent)
  {
    if (username.equals(propertyChangeEvent.getOldValue()))
    {
      for (ServerSocketHandler client : Server.pool.getConnections())
      {
        try
        {
          client.out.writeObject(propertyChangeEvent.getNewValue());
        }
        catch (IOException ioException)
        {
          ioException.printStackTrace();
        }
      }
    }
  }

  public void broadcast(PropertyChangeEvent propertyChangeEvent)
  {
    if (username
        .equals(((Message) propertyChangeEvent.getNewValue()).getUser()))
    {
      for (ServerSocketHandler client : Server.pool.getConnections())
      {
        try
        {
          client.out.writeObject(propertyChangeEvent.getNewValue());
        }
        catch (IOException ioException)
        {
          ioException.printStackTrace();
        }
      }
    }
  }

  public void serverMessage(PropertyChangeEvent evt)
  {
    if (username.equals((evt.getOldValue())))
    {
      try
      {
        out.writeObject(evt.getNewValue());
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  private void close()
  {
    try
    {
      Server.pool.removeConn(this);
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

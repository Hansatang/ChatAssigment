package Server.server;

import Server.model.DataModelS;
import shared.Message;

import java.beans.PropertyChangeEvent;
import java.io.*;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private Socket socket;
  private Server server;
  private DataModelS dataModelS;
  private ObjectOutputStream out;
  private ObjectInputStream in;

  private String username;
  private Message message;
  private boolean connected;

  /** Constructor for Server Socket Handler */
  public ServerSocketHandler(Socket socket, DataModelS dataModelS,
      Server server)
  {
    this.dataModelS = dataModelS;
    this.socket = socket;
    this.server = server;
    this.connected = true;

    try
    {
      this.in = new ObjectInputStream(socket.getInputStream());
      this.out = new ObjectOutputStream(socket.getOutputStream());
      server.getPool().addConn(this);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /** Method to run when thread is started */
  public void run()
  {
    while (connected)
    {
      try
      {
        message = (Message) in.readObject();
        System.out.println("Message " + message.getMessage());
        if (message.getMessage().equals("Connection"))
        {
          connectedMessageFromClient();
        }
        else if (message.getMessage().equals("Users"))
        {
          getUsersMessageFromClient();
        }
        else if (message.getMessage().equals("exit"))
        {
          disconnectionMessageFromClient();
        }
        else if (!message.isCommand())
        {
          normalMessageFromClient();
        }
      }
      catch (IOException | ClassNotFoundException e)
      {
        //    System.out.println("No streams are incoming");
      }
    }
  }

  /** Method to run when a client connects, send a message to server */
  private void connectedMessageFromClient()
  {
    this.username = message.getUser();
    dataModelS.addPropertyChangeListener("NewMessage", this::newMessage);
    for (ServerSocketHandler client : server.getPool().getConnections())
    {
      client.dataModelS.sendMessage(
          new Message("Server>>>", username + " connected to he server ",
              false));
    }
  }

  /** Method to run when client disconnects, sends a message to server */
  private void disconnectionMessageFromClient()
  {
    for (ServerSocketHandler client : server.getPool().getConnections())
    {
      if (!client.username.equals(message.getUser()))
      {
        try
        {
          client.out.writeObject(
              new Message("Server>>>", username + " disconnected to he server ",
                  false));
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    close();
  }

  /** Receive message from clients */
  private void normalMessageFromClient()
  {
    for (ServerSocketHandler client : server.getPool().getConnections())
    {
      client.dataModelS.sendMessage(message);
    }
  }

  /** Send amount of users to client requesting it */
  private void getUsersMessageFromClient()
  {
    String str = "";
    for (ServerSocketHandler client : server.getPool().getConnections())
    {
      str += client.username + ", ";
    }
    System.out.println(str);
    System.out.println(username);
    for (ServerSocketHandler client : server.getPool().getConnections())
    {
      if (client.username.equals(username))
      {
        try
        {
          client.out.writeObject(new Message("Server>>>",
              "There is  " + server.getPool().getConnections().size()
                  + " user connected \n" + str, false));
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }

      }
    }
  }

  /** Event when a new message is received */
  public void newMessage(PropertyChangeEvent propertyChangeEvent)
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

  /** Close all connections from the server */
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

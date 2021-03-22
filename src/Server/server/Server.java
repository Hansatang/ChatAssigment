package Server.server;

import Server.model.DataModelManagerS;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable
{
  private ServerSocket serverSocket;
  private Socket socket;
  private Pool pool = new Pool();
  private int port;

  private boolean running = true;

  public Server(int port)
  {
    this.port = port;

  }

  @Override public void run()
  {
    try
    {
      serverSocket = new ServerSocket(port);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    while (running)
    {
      try
      {
        socket = serverSocket.accept();
        ServerSocketHandler socketHandler = new ServerSocketHandler(socket,
            new DataModelManagerS(), this);
        Thread tr = new Thread(socketHandler);
        tr.start();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void closeServerSocket()
  {
    try
    {
      serverSocket.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void setRunning(boolean running)
  {
    this.running = running;
  }

  public Pool getPool()
  {
    return pool;
  }
}




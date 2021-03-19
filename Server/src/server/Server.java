package server;


import model.User;
import utility.ServerOperationModel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server implements Runnable
{
  private ServerSocket serverSocket;
  private Socket socket;

  static Set<User> userSet;
  private int port;
  private ServerOperationModel serverOperationModel;

  private boolean running = true;

  public Server(int port, ServerOperationModel dataModelS)
  {
    this.port = port;
    this.serverOperationModel = dataModelS;
   userSet = new HashSet<User>();

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
    Thread tr = new Thread(new ServerReadSave());
    tr.start();
    while (running)
    {
      try
      {
        socket = serverSocket.accept();
        userSet.add(new User(socket));
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

  public static void remove(User user) {
    userSet.remove(user);
  }
}




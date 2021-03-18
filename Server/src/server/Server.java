package server;



import utility.ServerOperationModel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable
{
  private ServerSocket serverSocket;
  private Socket socket;
  static ClientPool pool = new ClientPool();

  private int port;
  private ServerOperationModel serverOperationModel;

  private boolean running = true;

  public Server(int port, ServerOperationModel dataModelS)
  {
    this.port = port;
    this.serverOperationModel = dataModelS;

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
            serverOperationModel);
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

}




package Server1.Server;

import Server1.Domain.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server implements Runnable
{
  private ServerSocket serverSocket;
  private Socket socket;
  private Pool pool = new Pool();
  private boolean running = true;

  public int port;

  public Server(int port)
  {
    this.port = port;
  }

  @Override public void run()
  {
    try
    {
      serverSocket = new ServerSocket(2910);
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
        System.out
            .println("User " + socket.getInetAddress() + " joined the chat");
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Message name = (Message) in.readObject();
        String username = name.getUser();
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        ServerSocketHandler client = new ServerSocketHandler(username, socket, in, out,this);
        pool.addConn(client);
        client.sendMsg("Hello " + client.getUsername() + "! \n");
        System.out.println("User " + client.getUsername() + " has been added");
        Thread tr = new Thread(client);
        tr.start();
      }
      catch (IOException | ClassNotFoundException e)
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




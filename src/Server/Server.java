package Server;

import Domain.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server implements Runnable
{
  private ServerSocket serverSocket;
  private Socket socket;
  private boolean running = true;
  public static Set<ClientHandler> clientList;
  public int port;

  public Server(int port)
  {
    this.port = port;
    clientList = new HashSet<ClientHandler>();
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
        ClientHandler client = new ClientHandler(username, socket, in, out);
        clientList.add(client);
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

  public static void remove(ClientHandler client)
  {
    clientList.remove(client);
  }

}




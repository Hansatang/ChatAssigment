package Client.networking;

import shared.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements ClientModel
{
  private Socket socket;
  private ObjectOutputStream objectOutputStream;
  private boolean running = true;
  private String name;
  private PropertyChangeSupport support;

  /** Client constructor, requires a name/username for client */
  public Client(String name)
  {
    this.name = name;
    support = new PropertyChangeSupport(this);
    try
    {
      this.socket = new Socket("localhost", 2910);
      this.objectOutputStream = new ObjectOutputStream(
          socket.getOutputStream());
    }
    catch (IOException e)
    {
      throw new RuntimeException("Unable to connect to the server");
    }

    ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket,
        this);
    Thread thread = new Thread(clientSocketHandler);
    thread.setDaemon(true);
    thread.start();

    sendMessage(new Message(name, "Connection", true));
  }

  /** Send a Message object from client to server */
  @Override public void sendMessage(Message text)
  {
    try
    {
      objectOutputStream.writeObject(text);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  /** Sends a property change to listeners to check if there are any unread messages */
  @Override public void receiveMessage(Message message)
  {
    System.out.println("Client receive" + message);
    support.firePropertyChange("NewMessage", null, message);
  }

  /** Close the client-server connection from the client side */
  @Override public void deactivateClient()
  {
    running = false;
    try
    {
      objectOutputStream.close();
      socket.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /** Check if client is still running/online  */
  public boolean isRunning()
  {
    return running;
  }
}

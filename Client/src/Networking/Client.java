package networking;


import com.google.gson.Gson;
import model.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements ClientModel
{
  private Gson json;
  private Socket socket;
  private PrintWriter objectOutputStream;
  private boolean running = true;
  private String name;
  private PropertyChangeSupport support;

  public Client(String name)
  {
    this.name = name;
    support = new PropertyChangeSupport(this);
    json = new Gson();
    try
    {
      this.socket = new Socket("localhost", 2910);
      this.objectOutputStream = new PrintWriter(socket.getOutputStream(), true);
      ClientSocketHandler clientSocketHandler = new ClientSocketHandler(socket,
          this);

      Thread thread = new Thread(clientSocketHandler);
      thread.setDaemon(true);
      thread.start();
    }
    catch (IOException e)
    {
      throw new RuntimeException("Unable to connect to the server");
    }
    sendMessage(new Message(name, "Listener", true));
    System.out.println("coooo");

  }

  @Override public void sendMessage(Message text)
  {
      objectOutputStream.println(json.toJson(text));
  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  @Override public void receiveMessage(Message message)
  {
    System.out.println("Client receive" + message);
    support.firePropertyChange("NewMessage", null, message);
  }

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

  public boolean isRunning()
  {
    return running;
  }
}

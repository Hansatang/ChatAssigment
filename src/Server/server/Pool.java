package Server.server;

import java.util.ArrayList;
import java.util.List;

public class Pool
{
  private List<ServerSocketHandler> connections;

  /** Pool constructor */
  public Pool()
  {
    connections = new ArrayList<>();
  }

  /** Add a connection to the server's pool of connections */
  public synchronized void addConn(ServerSocketHandler ssh)
  {
    connections.add(ssh);
  }

  /** Remove a connection from the server's pool of connections */
  public synchronized void removeConn(ServerSocketHandler ssh)
  {
    for (int i = 0; i < connections.size(); i++)
    {
      if (connections.get(i) == ssh)
      {
        connections.remove(ssh);
      }
    }
  }

  /** Return a list containing all the connections */
  public List<ServerSocketHandler> getConnections()
  {
    return connections;
  }
}

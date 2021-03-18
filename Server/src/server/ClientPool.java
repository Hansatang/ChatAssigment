package server;

import java.util.ArrayList;
import java.util.List;

public class ClientPool
{
  private List<ServerSocketHandler> connections;

  public ClientPool()
  {
    connections = new ArrayList<>();
  }

  public synchronized void addConn(ServerSocketHandler serverSocketHandler)
  {
    connections.add(serverSocketHandler);
  }

  public synchronized void removeConn(ServerSocketHandler serverSocketHandler)
  {
    for (int i = 0; i < connections.size(); i++)
    {
      if (connections.get(i) == serverSocketHandler)
      {
        connections.remove(serverSocketHandler);
      }
    }
  }

  public List<ServerSocketHandler> getConnections()
  {
    return connections;
  }
}

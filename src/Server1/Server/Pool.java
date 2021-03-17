package Server1.Server;

import java.util.ArrayList;
import java.util.List;

public class Pool
{
  private List<ServerSocketHandler> connections;

  public Pool()
  {
    connections =  new ArrayList<>();
  }

  public synchronized void  addConn(ServerSocketHandler ssh)
  {
    connections.add(ssh);
  }
  public synchronized void  removeConn(ServerSocketHandler ssh)
  {
    for (int i = 0; i <connections.size() ; i++)
    {
      if (connections.get(i) ==ssh){
        connections.remove(ssh);
      }
    }
  }

  public List<ServerSocketHandler> getConnections()
  {
    return connections;
  }
}

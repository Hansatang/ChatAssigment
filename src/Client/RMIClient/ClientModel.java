package Client.RMIClient;

import shared.Message;
import shared.transferobjects.LogEntry;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientModel extends Remote
{

  void addPropertyChangeListener(String name, PropertyChangeListener listener);
  void deactivateClient();
  List<LogEntry> getLog();
  void startClient();
  void sendMessage(Message text);
  void update(LogEntry log) throws RemoteException;

}

package Client.networking;

import shared.Message;
import shared.transferobjects.LogEntry;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface ClientModel
{
  void sendMessage(Message text);
  void receiveMessage(Message message);
  void addPropertyChangeListener(String name, PropertyChangeListener listener);
  void deactivateClient();
  List<LogEntry> getLog();
  void startClient();
}

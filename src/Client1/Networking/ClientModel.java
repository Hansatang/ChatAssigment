package Client1.Networking;

import Shared.Message;

import java.beans.PropertyChangeListener;

public interface ClientModel
{
  void sendMessage(Message text);
  void addPropertyChangeListener(String name, PropertyChangeListener listener);
}

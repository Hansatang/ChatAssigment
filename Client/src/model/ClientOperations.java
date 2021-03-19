package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface ClientOperations
{
  void sendMessage(Message text);
  void update(PropertyChangeEvent evt);
  void createClient(String name);
  void addPropertyChangeListener(String name, PropertyChangeListener listener);
  void decreateClient();
  String getUsername();
}

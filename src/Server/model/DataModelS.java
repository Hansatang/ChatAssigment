package Server.model;

import shared.Message;

import java.beans.PropertyChangeListener;

public interface DataModelS
{
  void sendMessage(Message text);
  void addPropertyChangeListener(String name, PropertyChangeListener listener);

}

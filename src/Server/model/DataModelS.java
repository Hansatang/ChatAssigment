package Server.model;

import shared.Message;

import java.beans.PropertyChangeListener;

public interface DataModelS
{
  void sendMessage(Message text);
  void addListener(String name, PropertyChangeListener listener);

}

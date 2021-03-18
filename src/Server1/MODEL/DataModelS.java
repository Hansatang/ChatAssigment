package Server1.MODEL;

import Shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface DataModelS
{
  void sendMessage(Message text);
  void addListener(String name, PropertyChangeListener listener);

}

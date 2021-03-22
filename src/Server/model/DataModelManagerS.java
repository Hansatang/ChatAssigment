package Server.model;


import shared.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DataModelManagerS implements DataModelS
{
  private PropertyChangeSupport support;

  public DataModelManagerS()
  {
    this.support =  new PropertyChangeSupport(this);
  }

  @Override public void sendMessage(Message text)
  {
    support.firePropertyChange("NewMessage", null, text);
  }

  @Override public void addListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }


}

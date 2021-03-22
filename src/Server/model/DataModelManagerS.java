package Server.model;

import shared.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DataModelManagerS implements DataModelS
{
  private PropertyChangeSupport support;

  /** Constructor for DataModelManagerS */
  public DataModelManagerS()
  {
    this.support = new PropertyChangeSupport(this);
  }

  /** Send a property change (event) to the property listeners */
  @Override public void sendMessage(Message text)
  {
    support.firePropertyChange("NewMessage", null, text);
  }

  /** Add listener to the Change Support initialized in this class */
  @Override public void addListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

}

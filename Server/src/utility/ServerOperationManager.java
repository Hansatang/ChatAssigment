package utility;

import model.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ServerOperationManager implements ServerOperationModel
{
  private PropertyChangeSupport support;

  public ServerOperationManager()
  {
    this.support = new PropertyChangeSupport(this);
  }

  @Override public void sendMessage(Message text)
  {
    support.firePropertyChange(newMessagePropertyName, null, text);
  }

  @Override public void serverMessage(String name, Message text)
  {
    support.firePropertyChange(newServerMessagePropertyName, name, text);
  }

  @Override public void greetingMessage(String name, Message text)
  {
    support.firePropertyChange(newGreetingMessagePropertyName, name, text);
  }

  @Override public void addListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  public String getNewMessagePropertyName()
  {
    return newMessagePropertyName;
  }
}

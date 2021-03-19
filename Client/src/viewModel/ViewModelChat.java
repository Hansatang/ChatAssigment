package viewModel;

import model.ClientOperations;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Message;

import java.beans.PropertyChangeEvent;

public class ViewModelChat
{
  private StringProperty message;
  private StringProperty chat;
  private StringProperty User;
  private ClientOperations model;

  public ViewModelChat(ClientOperations model)
  {
    this.model = model;
    chat = new SimpleStringProperty();
    User = new SimpleStringProperty();
    message = new SimpleStringProperty();
    model.addPropertyChangeListener("NewMessage", this::updated);
  }

  public StringProperty messageProperty()
  {
    return message;
  }

  public StringProperty chatProperty()
  {
    return chat;
  }

  public Property<String> userProperty()
  {
    return User;
  }

  public void sendMessage()
  {
    if (message.get() != null)
    {
      model.sendMessage(
          (new Message(model.getUsername(), message.get(), false)));
    }
    else
    {
      model.sendMessage(new Message(model.getUsername(),
          "I wanted to send you an empty message", false));
    }
  }

  public void getUsers()
  {
    model.sendMessage(new Message(model.getUsername(), "Users", true));
  }

  public void updated(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (chat.getValue() == null)
      {
        User.set(model.getUsername());
        chat.setValue(evt.getNewValue().toString());
      }
      else
      {
        chat.setValue(chat.getValue() + "\n" + evt.getNewValue().toString());
      }
    });
  }

  public void closeChat()
  {
    model.sendMessage(new Message(model.getUsername(), "exit", true));
    model.decreateClient();
  }
}

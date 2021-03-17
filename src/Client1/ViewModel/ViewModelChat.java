package Client1.ViewModel;

import Client1.MODEL.DataModel;
import Shared.Message;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class ViewModelChat
{
  private StringProperty message;
  private StringProperty chat;
  private DataModel model;

  public ViewModelChat(DataModel model)
  {
    this.model = model;
    chat = new SimpleStringProperty();
    message = new SimpleStringProperty();
    model.addPropertyChangeListener("updated", this::updated);

  }

  public StringProperty messageProperty()
  {
    return message;
  }

  public StringProperty chatProperty()
  {
    return chat;
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

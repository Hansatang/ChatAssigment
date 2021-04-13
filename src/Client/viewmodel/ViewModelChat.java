package Client.viewmodel;

import Client.model.DataModel;
import shared.Message;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class ViewModelChat
{

  /** Declaring objects **/
  private StringProperty message;
  private StringProperty chat;
  private StringProperty User;
  private DataModel model;

  /** Constructor takes DataModel object as parameter **/
  public ViewModelChat(DataModel model)
  {
    this.model = model;
    chat = new SimpleStringProperty();
    User = new SimpleStringProperty();
    message = new SimpleStringProperty();
    /** Listener to update the chat when a new message is detected **/
    model.addPropertyChangeListener("NewMessage", this::updated);
  }

  /** Get method for message **/
  public StringProperty messageProperty()
  {
    return message;
  }

  /** Get method for the chat **/
  public StringProperty chatProperty()
  {
    return chat;
  }

  /** Get method for the username **/
  public Property<String> userProperty()
  {
    return User;
  }

  /** Method to run when user sends a message **/
  public void sendMessage()
  {
    /** Checks if message is empty and should send a default message **/
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

  /** Method from DataModel **/
  public void getUsers()
  {
    model.getUsers();
  }


  /** Update the chat **/
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

  /** Close client chat **/
  public void closeChat()
  {
    model.closeChat();
  }
}

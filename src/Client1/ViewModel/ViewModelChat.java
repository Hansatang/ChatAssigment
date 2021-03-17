package Client1.ViewModel;

import Client1.MODEL.DataModel;
import Client1.View.ViewHandler;
import Shared.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelChat
{
  private StringProperty message;
  private ViewHandler viewHandler;
  private StringProperty chat;
  private DataModel model;

  public ViewModelChat(DataModel model)
  {
    this.model = model;
    chat = new SimpleStringProperty();
    message = new SimpleStringProperty();
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

  }

  public void update()
  {
    model.update();

  }

}

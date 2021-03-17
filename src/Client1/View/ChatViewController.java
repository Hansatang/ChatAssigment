package Client1.View;

import Client1.ViewModel.ViewModelChat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class ChatViewController
{


  private ViewModelChat viewModelChat;
  public void init(ViewModelChat viewModelChat)
  {

    this.viewModelChat=viewModelChat;

  }

  @FXML public void sendMessage()
  {
  }

  @FXML public void getUsers()
  {
  }
}

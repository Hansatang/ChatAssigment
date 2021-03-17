package Client1.View;

import Client1.ViewModel.ViewModelChat;

import Client1.ViewModel.ViewModelChat;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ChatViewController
{
@FXML
private TextField sentMessageTextField;

@FXML
private TextArea messagesTextArea;

  private ViewModelChat viewModelChat;


  public void init(ViewModelChat viewModelChat)
  {
    this.viewModelChat=viewModelChat;
    sentMessageTextField.textProperty().bindBidirectional(viewModelChat.chatProperty());


  }

  @FXML public void sendMessage()
  {

  }

  @FXML public void getUsers()
  {
  }
}

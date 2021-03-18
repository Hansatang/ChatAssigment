package Client1.View;

import Client1.ViewModel.ViewModelChat;

import Shared.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ChatViewController
{
  @FXML private Label UsernameLabel;
  @FXML private TextField sentMessageTextField;

  @FXML private TextArea messagesTextArea;

  private ViewModelChat viewModelChat;

  public void init(ViewModelChat viewModelChat)
  {
    this.viewModelChat = viewModelChat;
    sentMessageTextField.textProperty()
        .bindBidirectional(viewModelChat.messageProperty());
    messagesTextArea.textProperty()
        .bindBidirectional(viewModelChat.chatProperty());
    UsernameLabel.textProperty().bindBidirectional(viewModelChat.userProperty());
  }

  @FXML public void sendMessage()
  {
    viewModelChat.sendMessage();
    sentMessageTextField.setText(null);
  }

  @FXML public void getUsers()
  {
    viewModelChat.getUsers();
  }

}

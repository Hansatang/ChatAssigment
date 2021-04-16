package Client.view;

import Client.viewmodel.ViewModelChat;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatViewController
{
  @FXML private Label UsernameLabel;
  @FXML private TextField sentMessageTextField;
  @FXML private TextArea messagesTextArea;

  private ViewModelChat viewModelChat;

  /** Method to run when opening the client GUI */
  public void init(ViewModelChat viewModelChat)
  {
    this.viewModelChat = viewModelChat;
    sentMessageTextField.textProperty()
        .bindBidirectional(viewModelChat.messageProperty());
    messagesTextArea.textProperty()
        .bindBidirectional(viewModelChat.chatProperty());
    UsernameLabel.textProperty()
        .bindBidirectional(viewModelChat.userProperty());
  }

  /** Method to run when user presses enter/clicks on send message button */
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

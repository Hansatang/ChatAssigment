package Client1.View;

import Client1.ViewModel.ViewModelLogin;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class LoginViewController
{

  private ViewModelLogin viewModelLogin;
  private ViewHandler viewHandler;

  @FXML private TextField usernameTextField;

  public void init(ViewModelLogin viewModelLogin, ViewHandler viewHandler)
  {
    this.viewModelLogin = viewModelLogin;
    this.viewHandler = viewHandler;

    usernameTextField.textProperty()
        .bindBidirectional(viewModelLogin.usernameProperty());
  }

  @FXML public void startChat(javafx.event.ActionEvent actionEvent)
  {
    viewModelLogin.startChat();
    viewHandler.openChatView();
  }
}

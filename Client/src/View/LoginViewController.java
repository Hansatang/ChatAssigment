package view;

import viewModel.ViewModelLogin;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class LoginViewController
{
  @FXML private TextField usernameTextField;

  private ViewModelLogin viewModelLogin;
  private ViewHandler viewHandler;

  public void init(ViewModelLogin viewModelLogin, ViewHandler viewHandler)
  {
    this.viewModelLogin = viewModelLogin;
    this.viewHandler = viewHandler;

    usernameTextField.textProperty()
        .bindBidirectional(viewModelLogin.usernameProperty());
  }

  @FXML public void startChat()
  {
    viewModelLogin.startChat();
    viewHandler.openChatView();
  }
}

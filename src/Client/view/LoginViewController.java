package Client.view;

import Client.viewmodel.ViewModelLogin;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class LoginViewController
{
  @FXML private TextField usernameTextField;

  private ViewModelLogin viewModelLogin;
  private ViewHandler viewHandler;

  /** Method to run when opening the client login GUI */
  public void init(ViewModelLogin viewModelLogin, ViewHandler viewHandler)
  {
    this.viewModelLogin = viewModelLogin;
    this.viewHandler = viewHandler;

    usernameTextField.textProperty()
        .bindBidirectional(viewModelLogin.usernameProperty());
  }

  /** Method to use when the user has pressed the login button */
  @FXML public void startChat()
  {
    viewModelLogin.startChat();
    viewHandler.openChatView();
  }
}

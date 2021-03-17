package Client1.View;

import Client1.ViewModel.ViewModelLogin;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class LoginViewController
{

  private ViewModelLogin viewModelLogin;

  @FXML
  private TextField usernameTextField;

  public void init(ViewModelLogin viewModelLogin)
  {
    this.viewModelLogin = viewModelLogin;

    usernameTextField.textProperty().bindBidirectional(viewModelLogin.usernameProperty());
  }


  @FXML public void startChat(ActionEvent event)
  {
    viewModelLogin.startChat();
  }
}

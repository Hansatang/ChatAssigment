package Client1.View;

import Client1.ViewModel.ViewModelLogin;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class LoginViewController
{
  private Scene scene;
  private ViewHandler viewHandler;
  private ViewModelLogin viewModelLogin;
  @FXML
  private TextField usernameTextField;

  public void init(ViewHandler viewHandler,ViewModelLogin viewModelLogin,Scene scene)
  {
    this.viewHandler = viewHandler;
    this.viewModelLogin = viewModelLogin;
    this.scene=scene;
    usernameTextField.textProperty().bindBidirectional(viewModelLogin.usernameProperty());
  }

  public Scene getScene()
  {
    return scene;
  }

  @FXML public void startChat()
  {
    viewHandler.openChatView();
  }
}

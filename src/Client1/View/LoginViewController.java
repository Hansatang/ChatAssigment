package Client1.View;

import Client1.ViewModel.ViewModelLogin;

import javafx.fxml.FXML;
import javafx.scene.Scene;

public class LoginViewController
{
  private Scene scene;
  private ViewHandler viewHandler;
  private ViewModelLogin viewModelLogin;

  public void init(ViewHandler viewHandler,ViewModelLogin viewModelLogin,Scene scene)
  {
    this.viewHandler = viewHandler;
    this.viewModelLogin = viewModelLogin;
    this.scene=scene;
  }

  public Scene getScene()
  {
    return scene;
  }

  @FXML public void startChat()
  {
  }
}

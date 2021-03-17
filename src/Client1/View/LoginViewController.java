package Client1.View;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;


public class LoginViewController
{
  private Scene scene;
  private ViewHandler viewHandler;
  private
  @FXML
  TextField  usernameTextField;


  public void init(ViewHandler viewHandler,Scene scene)
  {
    this.viewHandler = viewHandler;
    this.scene=scene;
  }

  public Scene getScene()
  {
    return scene;
  }
}

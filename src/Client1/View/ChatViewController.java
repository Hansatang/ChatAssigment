package Client1.View;

import Client1.ViewModel.ViewModelChat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class ChatViewController
{
  private Scene scene;
  private ViewHandler viewHandler;
  private ViewModelChat viewModelChat;
  public void init(ViewHandler viewHandler,ViewModelChat viewModelChat,Scene scene)
  {
    this.viewHandler=viewHandler;
    this.viewModelChat=viewModelChat;
    this.scene = scene;
  }

  public Scene getScene()
  {
    return scene;
  }

  @FXML public void sendMessage()
  {
  }

  @FXML public void getUsers()
  {
  }
}

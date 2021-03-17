package Client1.View;

import Client1.ViewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{

  private Stage stage;
  private LoginViewController loginViewController;
  private ChatViewController chatViewController;
  private ViewModelFactory viewModelFactory;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.stage = primaryStage;
    stage.setResizable(false);
    openLoginView();
  }

  public void openLoginView()
  {
    if (loginViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        loginViewController = loader.getController();
        loginViewController.init(this,viewModelFactory.getViewModelLogin(),scene);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    stage.setScene(loginViewController.getScene());
    stage.setTitle("Login View");
    stage.show();
  }

  public void openChatView()
  {
    if (chatViewController == null)
    {
      try
      {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/chat2.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        chatViewController = loader.getController();
        chatViewController.init(this,viewModelFactory.getViewModelChat(),scene);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    stage.setScene(chatViewController.getScene());
    stage.setTitle("Chat view");
    stage.show();
  }

}

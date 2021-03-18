package Client1.View;

import Client1.ViewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
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
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../FXML/login.fxml"));
    Parent root = null;
    try
    {
      root = loader.load();

    }
    catch (IOException e)
    {
      e.printStackTrace();

    }
    LoginViewController viewController = loader.getController();
    viewController.init(viewModelFactory.getViewModelLogin(), this);
    stage.setTitle("Login View");
    Scene scene = new Scene(root);
    stage.setScene(scene);
    scene.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER)
      {
        viewController.startChat();
      }
    });
    stage.show();
  }

  public void openChatView()
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("../FXML/chat.fxml"));
    Parent root = null;
    try
    {
      root = loader.load();

    }
    catch (IOException e)
    {
      e.printStackTrace();

    }
    ChatViewController viewController = loader.getController();
    viewController.init(viewModelFactory.getViewModelChat());
    stage.setTitle("Chat View");
    Scene scene = new Scene(root);
    stage.setScene(scene);
    scene.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER)
      {
        viewController.sendMessage();
      }
    });
    stage.show();

  }

}




package view;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import server.Server;
import utility.ServerOperationManager;

import java.io.IOException;

public class ServerView
{
  @FXML Label label;
  private Server server;

  private Thread serverThread;

  public void init(Stage stage)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../FXML/server.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("server");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @FXML public void startServer()
  {
    label.setText("Server is running...");
    this.server = new Server(2910, new ServerOperationManager());

    serverThread = new Thread(server);
    serverThread.start();
  }

  @FXML public void stopServer()
  {
    label.setText("Server is turned off");
    server.setRunning(false);
    server.closeServerSocket();
  }

}

package Server.server;

import Server.RMIServer.ServerImpl;
import Server.RMIServer.ChatServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerView
{
  @FXML Label label;
  /** Method to run when server gui is opened */
  public void init(Stage stage)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../fxml/server.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Server");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /** Method to run when user click on "Start server" JavaFX button */
  @FXML public void startServer() throws RemoteException, AlreadyBoundException
  {
    ChatServer server = new ServerImpl();
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", server);
    System.out.println("Server started");
  }

  /** Method to run when user click on "Stop server" JavaFX button */
  @FXML public void stopServer()
  {
    label.setText("Server is turned off");
//    server.setRunning(false);
//    server.closeServerSocket();
  }

}

package Server;

import Server.server.ServerView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
  /** Object declaring **/
  ServerView view = new ServerView();

  /** Method to run when GUI starts **/
  @Override public void start(Stage primaryStage)
  {
    view.init(primaryStage);
  }

  /** Method to run when program ends **/
  @Override public void stop()
  {
    try
    {
      view.stopServer();
    }
    catch (NullPointerException e)
    {
      System.out.println("Server closed");
    }
  }
}

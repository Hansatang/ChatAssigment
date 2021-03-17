package Client1.View;

import javafx.scene.Scene;

public class ChatViewController
{
  private Scene scene;
  private ViewHandler viewHandler;
  public void init(ViewHandler viewHandler,Scene scene)
  {
    this.viewHandler=viewHandler;
    this.scene = scene;
  }

  public Scene getScene()
  {
    return scene;
  }
}

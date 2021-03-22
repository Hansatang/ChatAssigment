package Client;

import Client.model.DataModel;

import Client.model.DataModelManager;
import Client.view.ViewHandler;
import Client.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
  private DataModel model;
  private ViewModelFactory viewModelFactory;
  private ViewHandler viewHandler;

  /** Method to run when client GUI starts */
  @Override public void start(Stage stage)
  {
    model = new DataModelManager();
    viewModelFactory = new ViewModelFactory(model);
    viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(stage);
  }

  /** Method ro run before client GUI closes */
  @Override public void stop()
  {
    try
    {
      viewModelFactory.getViewModelChat().closeChat();
    }
    catch (NullPointerException e)
    {
      System.out.println("Everything closed");
    }
  }
}

import model.DataModel;

import model.DataModelManager;
import view.ViewHandler;
import viewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{

  private DataModel model;
  private ViewModelFactory viewModelFactory;
  private ViewHandler viewHandler;

  @Override public void start(Stage stage)
  {
    model = new DataModelManager();
    viewModelFactory = new ViewModelFactory(model);
    viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(stage);
  }

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

package Client1;

import Client1.MODEL.DataModel;

import Client1.MODEL.DataModelManager;
import Client1.View.ViewHandler;
import Client1.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{


	private DataModel model;
	private ViewModelFactory viewModelFactory;
	private ViewHandler viewHandler;

	@Override
	public void start(Stage stage) {
		model = new DataModelManager();
		viewModelFactory = new ViewModelFactory(model);
		viewHandler = new ViewHandler(viewModelFactory);
		viewHandler.start(stage);
	}
}

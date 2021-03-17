package Client1.View;

import ViewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{

	private Stage stage;
	private LoginView loginView;
	private ChatView chatView;
	private ViewModelFactory viewModelFactory;

	public ViewHandler(ViewModelFactory viewModelFactory) {
		this.viewModelFactory = viewModelFactory;
	}

	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		stage.setResizable(false);
		openLoginView();
	}

	public void openLoginView() {
		if (loginView == null) {
			try{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../FXML/login.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
		//		loginView = loader.getController();
		//		loginView.init(??);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		stage.setScene(loginView.getScene());
		stage.setTitle(loginView.getTitle());
		stage.show();
	}

	public void openChatView() {
		if(chatView == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../FXML/chat2.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
			//	chatView = loader.getController();
			//	chatView.init(???);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		stage.setScene(chatView.getScene());
		stage.setTitle(chatView.getTitle());
		stage.show();
	}

}

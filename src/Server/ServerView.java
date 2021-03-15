package Server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class ServerView {

	@FXML
	Label label;
	@FXML
	ImageView img;
	private Server server;
	private Stage stage;
	private Thread tr1;


	public ServerView() {
	}

	public void init(Stage stage) {
		try {
			this.stage = stage;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("../FXML/server.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Server");
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML public void startServer() {
		label.setText("Server is running...");
		this.server = new Server(2910);
		tr1 = new Thread(server);
		tr1.start();
	}

	@FXML public void stopServer() {
		label.setText("Server is turned off");
		server.setRunning(false);
		server.closeServerSocket();
	}

}

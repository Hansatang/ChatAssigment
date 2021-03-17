package Client1.MODEL;

import Shared.Message;
import javafx.scene.Scene;

public interface DataModel {
    void sendMessage(Message text);
    void update();
    void createClient(String name);

    String getUsername();
}

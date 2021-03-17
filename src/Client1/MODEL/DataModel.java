package Client1.MODEL;

import javafx.scene.Scene;

public interface DataModel {
    void sendMessage(String text);
    void update();
    void createClient(String name);
    void setUserName(String name);
}

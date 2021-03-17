package Client1.ViewModel;

import Client1.MODEL.DataModel;
import Client1.View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelChat
{
    private StringProperty message;
    private ViewHandler viewHandler;
    private StringProperty chat;
    private DataModel model;

    public ViewModelChat( ViewHandler viewHandler) {

        this.viewHandler = viewHandler;
        chat=new SimpleStringProperty();
        message=new SimpleStringProperty();
    }


    public StringProperty messageProperty() {
        return message;
    }


    public void setChat(String chat) {
        this.chat.set(chat);
    }

    public void sendMessage()
    {
     model.sendMessage(message.getValue());
     message.setValue("");


    }

    public void getUsers()
    {

    }

    public void update()
    {
        model.update();

    }
}

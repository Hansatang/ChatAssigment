package Client1.ViewModel;

import Client1.MODEL.LoginModel;
import Client1.View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelLogin
{
    private StringProperty username;
    private LoginModel model;
    private ViewHandler viewHandler;

    public ViewModelLogin(LoginModel model,ViewHandler viewHandler)
    {
        this.model=model;
        this.viewHandler=viewHandler;
        username=new SimpleStringProperty();
    }

    public ViewModelLogin(StringProperty username) {
        this.username = username;
    }

    public void startChat()
    {
        viewHandler.openChatView();
    }
}

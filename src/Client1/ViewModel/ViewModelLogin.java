package Client1.ViewModel;

import Client1.MODEL.DataModelManager;
import Client1.View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelLogin
{
    private StringProperty username;
    private DataModelManager model;
    private ViewHandler viewHandler;

    public ViewModelLogin(DataModelManager model, ViewHandler viewHandler)
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

package Client1.ViewModel;

import Client1.MODEL.DataModel;
import Client1.View.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelLogin
{
    private StringProperty username;
    private DataModel model;
    private ViewHandler viewHandler;

    public ViewModelLogin(DataModel model )
    {
        this.model=model;
        username=new SimpleStringProperty();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void startChat()
    {
        viewHandler.openChatView();
    }

}

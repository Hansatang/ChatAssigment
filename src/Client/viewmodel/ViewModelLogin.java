package Client.viewmodel;

import Client.model.DataModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelLogin
{
  private StringProperty username;
  private DataModel model;


  public ViewModelLogin(DataModel model)
  {
    this.model = model;
    username = new SimpleStringProperty();
  }

  public StringProperty usernameProperty()
  {
    return username;
  }

  public void startChat()
  {
    if (username.get() == null)
    {
      model.createClient("Anonymous");
    }
    else
    {
      model.createClient(username.get());
    }
  }
}
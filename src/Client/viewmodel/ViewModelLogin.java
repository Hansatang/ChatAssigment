package Client.viewmodel;

import Client.model.DataModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelLogin
{

  /** Declaring objects **/
  private StringProperty username;
  private DataModel model;

  /** Constructor which initialize username and saves the parameter to a declared DataModel object **/
  public ViewModelLogin(DataModel model)
  {
    this.model = model;
    username = new SimpleStringProperty();
  }

  /** Get method to retrieve username **/
  public StringProperty usernameProperty()
  {
    return username;
  }

  /** Checks if username will by custom or anonymous and then starts chat with username **/
  public void startChat()
  {
    if (username.get() == null)
    {
      model.createClient("Anonymous user");
    }
    else
    {
      model.createClient(username.get());
    }
  }
}

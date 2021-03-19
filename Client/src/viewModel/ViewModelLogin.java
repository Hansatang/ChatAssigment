package viewModel;

import model.ClientOperations;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ViewModelLogin
{
  private StringProperty username;
  private ClientOperations clientOperations;

  public ViewModelLogin(ClientOperations model)
  {
    this.clientOperations = model;
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
      clientOperations.createClient("Anonymous");
    }
    else
    {
      clientOperations.createClient(username.get());
    }
  }
}

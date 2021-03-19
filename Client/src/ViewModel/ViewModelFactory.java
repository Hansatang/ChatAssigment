package viewModel;

import model.ClientOperations;

public class ViewModelFactory
{
  private ViewModelLogin viewModelLogin;
  private ViewModelChat viewModelChat;
  private ClientOperations dataModel;

  public ViewModelFactory(ClientOperations dataModel)
  {
    this.dataModel = dataModel;
    this.viewModelChat = new ViewModelChat(dataModel);
    this.viewModelLogin = new ViewModelLogin(dataModel);
  }

  public ViewModelLogin getViewModelLogin()
  {
    return viewModelLogin;
  }

  public ViewModelChat getViewModelChat()
  {
    return viewModelChat;
  }
}

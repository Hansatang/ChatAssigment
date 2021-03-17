package Client1.ViewModel;

import Client1.MODEL.DataModel;

public class ViewModelFactory
{
  private ViewModelLogin viewModelLogin;
  private ViewModelChat viewModelChat;
  private DataModel dataModel;

  public ViewModelFactory(DataModel dataModel)
  {
    this.dataModel=dataModel;
    this.viewModelChat = new ViewModelChat(dataModel);
    this.viewModelLogin = new ViewModelLogin(dataModel);
  }

  public ViewModelLogin getViewModelLogin() {
    return viewModelLogin;
  }

  public ViewModelChat getViewModelChat() {
    return viewModelChat;
  }
}

package Client1.ViewModel;

public class ViewModelFactory
{
  private ViewModelLogin viewModelLogin;
  private ViewModelChat viewModelChat;

  public ViewModelLogin getViewModelLogin() {
    return viewModelLogin;
  }

  public ViewModelChat getViewModelChat() {
    return viewModelChat;
  }
}

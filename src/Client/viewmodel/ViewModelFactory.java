package Client.viewmodel;

import Client.model.DataModel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewModelFactory
{
  private DataModel localDataModel;
  private ViewModelLogin viewModelLogin;
  private ViewModelChat viewModelChat;

  private static ViewModelFactory instance;
  private static Lock lock = new ReentrantLock();

  private ViewModelFactory(DataModel dataModel)
  {
    this.localDataModel = dataModel;
    this.viewModelLogin = new ViewModelLogin(dataModel);
    this.viewModelChat = new ViewModelChat(dataModel);
  }

  public synchronized static ViewModelFactory getInstance(DataModel dataModel)
  {
    if (instance == null)
    {
      synchronized (lock)
      {
        if (instance == null)
        {
          instance = new ViewModelFactory(dataModel);
        }
      }
    }
    return instance;
  }

  public synchronized ViewModelLogin getModelLogin()
  {
    return viewModelLogin;
  }

  public synchronized ViewModelChat getModelChat()
  {
    return viewModelChat;
  }
}
package Client.viewmodel;

import Client.model.DataModel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewModelFactory
{
  /** Initialize ViewModel object **/
  private ViewModelLogin viewModelLogin;
  private ViewModelChat viewModelChat;

  /** Initialize singleton and lock variables **/
  private static ViewModelFactory instance;
  private static Lock lock = new ReentrantLock();

  /** Constructor to instantiate ViewModel objects which takes a DataModel object as parameter **/
  private ViewModelFactory(DataModel dataModel)
  {
    this.viewModelLogin = new ViewModelLogin(dataModel);
    this.viewModelChat = new ViewModelChat(dataModel);
  }

  /** Singleton method to return an object of this class **/
  public synchronized static ViewModelFactory getInstance(DataModel dataModel)
  {
    if (instance == null)
    {
      /** Lock to ensure that there won't be more classes able to get information the same time. **/
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

  /** Get method for ViewModels, this returns ViewModelLogin **/
  public synchronized ViewModelLogin getModelLogin()
  {
    return viewModelLogin;
  }

  /** Get method for ViewModels, this returns ViewModelChat **/
  public synchronized ViewModelChat getModelChat()
  {
    return viewModelChat;
  }
}
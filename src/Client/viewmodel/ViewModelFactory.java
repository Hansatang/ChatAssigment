package Client.viewmodel;

import Client.model.DataModel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewModelFactory
{
  private static DataModel localDataModel;
  private static ViewModelLogin viewModelLogin;
  private static ViewModelChat viewModelChat;

  private static ViewModelFactory instance;
  private static Lock lock = new ReentrantLock();

  public synchronized static ViewModelFactory getInstance(DataModel dataModel){
    if(instance == null){
      synchronized (lock){
        instance = new ViewModelFactory();
        localDataModel = dataModel;
        viewModelLogin = new ViewModelLogin(dataModel);
        viewModelChat = new ViewModelChat(dataModel);
      }
    }
    return instance;
  }

  public synchronized ViewModelLogin getModelLogin(){
    return viewModelLogin;
  }

  public synchronized ViewModelChat getModelChat(){
    return viewModelChat;
  }
}
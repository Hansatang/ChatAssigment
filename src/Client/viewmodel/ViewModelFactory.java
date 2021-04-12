package Client.viewmodel;

import Client.model.DataModel;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ViewModelFactory implements Runnable
{
  private DataModel dataModel;
  private ViewModelLogin viewModelLogin;
  private ViewModelChat viewModelChat;

  private static ViewModelFactory instance;
  private static Lock lock = new ReentrantLock();

  public synchronized void setDataModel(DataModel dataModel){
    this.dataModel = dataModel;
  }

  public synchronized DataModel getDataModel(){
    return this.dataModel;
  }

  public synchronized static ViewModelFactory getInstance(){
    if(instance == null){
      synchronized (lock){
        instance = new ViewModelFactory();
      }
    }
    return instance;
  }

  public synchronized void setModelLogin(ViewModelLogin viewModelLogin){
    this.viewModelLogin = new ViewModelLogin(dataModel);
  }

  public synchronized void setModelChat(ViewModelChat viewModelChat){
    this.viewModelChat = new ViewModelChat(dataModel);
  }

  public synchronized ViewModelLogin getModelLogin(){
    return viewModelLogin;
  }

  public synchronized ViewModelChat getModelChat(){
    return viewModelChat;
  }

  @Override
  public void run() {
    while(true){
    }
  }
}
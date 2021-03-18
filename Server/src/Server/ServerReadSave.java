package Server;

import model.Message;
import model.User;

public class ServerReadSave implements Runnable
{

  @Override public void run()
  {
    Message message;
    while (true)
    {
      for (User user : Server.userSet)
      {
        if ((message = user.receiveMessage()) != null)
        {
          broadcast(user.receiveMessage());
        }
      }
    }
  }

  private void broadcast(Message text)
  {
    for (User user : Server.userSet)
    {
      user.sendMessage(text);
    }
  }

}

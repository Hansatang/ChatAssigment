package server;

import model.Message;
import model.User;

public class ServerReadSave implements Runnable
{

  @Override public void run()
  {
    while (true)
    {
      for (User user : Server.userSet)
      {
      //  System.out.println("First loop amount "+Server.userSet.size());
        if (user.gotMessage())
        {
          Message message = user.receiveMessage();
          System.out.println("Test msg "+message.getUser());
          System.out.println("Test msg "+message.getMessage());
          System.out.println("Test msg "+message.isCommand());
          if (message.isCommand())
          {
            if (message.getMessage().equals("Users"))
            {
              String str = "";
              for (User robot : Server.userSet)
              {
                System.out.println("Second loop amount "+Server.userSet.size());
                str += (robot.getUsername());
                str += (", ");
              }
              serverMessage(new Message(message.getUser(),
                  "There is  " + Server.userSet.size() + " user connected \n"
                      + str, false));
            }
            if (message.getMessage().contains("disconnected"))
            {
              System.out.println("Do kasacji user "+message.getUser());
              System.out.println("Do wyjebaaaaaaaaania user "+user);
              Server.remove(user);
              user.close();
              broadcast(message);
            }
          }
          else
          {
            System.out.println("HMMM");
            broadcast(message);
          }
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

  private void serverMessage(Message text)
  {
    for (User user : Server.userSet)
    {
      if (user.getUsername().equals(text.getUser()))
      {
        Message message = new Message("Server>>>", text.getMessage(), false);
        user.sendMessage(message);
      }
    }
  }

}

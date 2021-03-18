package Shared;

import java.io.Serializable;
import java.time.LocalTime;

public class Message implements Serializable
{

  private String user;
  private String message;
  private boolean command;

  public Message(String user, String message, boolean command)
  {
    this.user = user;
    this.message = message;
    this.command = command;
  }

  private String getHour()
  {
    if (LocalTime.now().getHour() > 9)
      return LocalTime.now().getHour() + "";
    else
      return "0" + LocalTime.now().getHour();
  }

  private String getMinutes()
  {
    if (LocalTime.now().getMinute() < 10)
      return "0" + LocalTime.now().getMinute();
    else
      return LocalTime.now().getMinute() + "";
  }

  private String getSeconds()
  {
    if (LocalTime.now().getSecond() < 10)
      return "0" + LocalTime.now().getSecond();
    else
      return LocalTime.now().getSecond() + "";
  }

  public String getUser()
  {
    return user;
  }

  public String getMessage()
  {
    return message;
  }

  public boolean isCommand()
  {
    return command;
  }

  private String getTime()
  {
    return getHour() + ":" + getMinutes() + ":" + getSeconds();
  }

  public String toString()
  {
    return getTime() + " " + user + " says: " + message;
  }
}

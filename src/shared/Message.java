package shared;

import java.io.Serializable;
import java.time.LocalTime;

public class Message implements Serializable
{

  private String user;
  private String message;
  private boolean command;

  /** Constructor for Message class, needs username, message to send and a
   *  boolean to check if the message is a command or just text message */
  public Message(String user, String message, boolean command)
  {
    this.user = user;
    this.message = message;
    this.command = command;
  }

  /** Return current hour of system's clock */
  private String getHour()
  {
    if (LocalTime.now().getHour() > 9)
      return LocalTime.now().getHour() + "";
    else
      return "0" + LocalTime.now().getHour();
  }

  /** Return current minutes of system's clock */
  private String getMinutes()
  {
    if (LocalTime.now().getMinute() < 10)
      return "0" + LocalTime.now().getMinute();
    else
      return LocalTime.now().getMinute() + "";
  }

  /** Return current seconds of system's clock */
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

  /** Return a more formatted String object of the current time */
  private String getTime()
  {
    return getHour() + ":" + getMinutes() + ":" + getSeconds();
  }

  /** Translate Message object into String object */
  public String toString()
  {
    return getTime() + " " + user + " says: " + message;
  }
}

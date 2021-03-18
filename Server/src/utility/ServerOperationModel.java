package utility;

import model.Message;

import java.beans.PropertyChangeListener;

public interface ServerOperationModel
{
  String newMessagePropertyName = "NewMessage";
  String newServerMessagePropertyName = "serverMessage";
  String newGreetingMessagePropertyName = "greetingMessage";

  void sendMessage(Message text);
  void serverMessage(String name, Message text);
  void greetingMessage(String name, Message text);
  void addListener(String name, PropertyChangeListener listener);


}

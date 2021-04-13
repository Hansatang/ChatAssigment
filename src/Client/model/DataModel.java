package Client.model;

import shared.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface DataModel
{
  /** Client sends a message **/
  void sendMessage(Message text);

  /** Update when evt occurs **/
  void update(PropertyChangeEvent evt);

  /** Create a client with the name of the parameter **/
  void createClient(String name);

  /** Add a listener to client **/
  void addPropertyChangeListener(String name, PropertyChangeListener listener);

  /** Get method for client username **/
  String getUsername();

  /** Method to send when client wants to retrieve the amount of users connected in the chat **/
  void getUsers();

  /** Close client side of the chat **/
  void closeChat();
}

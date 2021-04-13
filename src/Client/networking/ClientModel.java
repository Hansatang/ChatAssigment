package Client.networking;

import shared.Message;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientModel extends Remote
{
  /** Add a listener to the client **/
  void addPropertyChangeListener(String name, PropertyChangeListener listener)
      throws RemoteException;

  /** Start client side of RMI **/
  void startClient() throws RemoteException;

  /** Send a Message object from client to server */
  void sendMessage(Message text) throws RemoteException;

  /** Get method for client username **/
  String getUsername() throws RemoteException;

  /** Sends a property change to listeners to check if there are any unread messages */
  void receiveMessage(Message message) throws RemoteException;

  /** Request information about how many users there are connected **/
  void getUsers() throws RemoteException;

  /** Close the client-server connection from the client side */
  void closeChat() throws RemoteException;
}

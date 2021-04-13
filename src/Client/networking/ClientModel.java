package Client.networking;

import shared.Message;
import shared.transferobjects.LogEntry;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientModel extends Remote
{
  void addPropertyChangeListener(String name, PropertyChangeListener listener)
      throws RemoteException;
  void startClient() throws RemoteException;
  void sendMessage(Message text) throws RemoteException;
  String getUsername() throws RemoteException;
  void receiveMessage(Message message) throws RemoteException;
  void getUsers() throws RemoteException;
  void closeChat() throws RemoteException;
}

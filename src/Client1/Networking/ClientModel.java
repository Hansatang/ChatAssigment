package Client1.Networking;

import Client1.PropertyChange.PropertyChangeSubject;
import Shared.Message;

public interface ClientModel extends PropertyChangeSubject
{
void sendMsg(Message text);
}

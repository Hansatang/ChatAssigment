package Client1.Networking;

import Client1.MODEL.DataModelManager;
import Client1.ViewModel.ViewModelLogin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;

public class Client implements ClientModel, Runnable, PropertyChangeListener
{

    private String hostname = "localhost";
    private int port;
    private Socket socket;
    private boolean running = true;
    private DataModelManager manager;
    private String name;
    private ViewModelLogin viewModelLogin;

    public Client(DataModelManager manager) {
        this.manager = manager;

    }

    @Override
    public void run() {
        try {
            socket = new Socket(hostname, port);
        } catch (IOException e){
            e.printStackTrace();
        }
        while(running){

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override public void setUsername()
    {
        name =viewModelLogin.usernameProperty().getValue();
    }
}

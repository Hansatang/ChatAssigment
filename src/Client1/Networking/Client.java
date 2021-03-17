package Client1.Networking;

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

    public Client(int port) {
        this.port = port;
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
}

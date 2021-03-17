package Client1.MODEL;

import java.beans.PropertyChangeSupport;

public class DataModelManager implements DataModel {

    private PropertyChangeSupport support=new PropertyChangeSupport(this);




    @Override
    public void sendMessage(String text) {

    }

    @Override
    public void update() {

    }
}

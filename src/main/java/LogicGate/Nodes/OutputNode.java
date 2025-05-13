package LogicGate.Nodes;

import LogicGate.Connection.Connection;
import javafx.scene.layout.Pane;


public class OutputNode extends Node {
    private Connection connection;

    public OutputNode(Pane pane){
        super(pane);
    }

    @Override
    public boolean isOutputNode() {
        return true;
    }

    @Override
    public void delete() {
        if (connection != null){
            connection.remove();
        }

        super.delete();
    }

    public void connected(Connection connection){
        this.connection = connection;
    }

    public boolean isConnected(){
        return connection != null;
    }

    public void disconnect() {
        this.connection = null;
    }

    @Override
    public void changeState(){
        if (connection != null) {
            state = connection.getState();
        } else {
            state = false;
        }
        checkState();


    }
}

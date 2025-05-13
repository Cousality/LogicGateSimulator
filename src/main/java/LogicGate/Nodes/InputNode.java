package LogicGate.Nodes;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import LogicGate.Connection.Connection;
import java.util.ArrayList;

public class InputNode extends Node {

    private Connection connection;
    private final ArrayList<Connection> connectionList;

    public InputNode(Pane pane){
        super(pane);

        connectionList = new ArrayList<>();
    }

    @Override
    public void nodeClickHandler(Circle circle){
        circle.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                delete();
            } else if (event.getButton() == MouseButton.PRIMARY) {
                changeState();
            }
        });
        circle.setOnMousePressed(event ->{
            if (event.getButton() == MouseButton.PRIMARY) {
                connection = new Connection(parentPane, this);
                connectionList.add(connection);
                connection.draw(circle.getCenterX(),circle.getCenterY());
            }
        });
        circle.setOnMouseDragged(event ->{
            if (event.getButton() == MouseButton.PRIMARY) {
                connection.update(event.getX(), event.getY());
            }
        });
        circle.setOnMouseReleased(event ->{
            if (event.getButton() == MouseButton.PRIMARY) {
                if (!connection.getConnected()){
                    connection.remove();
                    connectionList.remove(connection);
                }else{
                    connection.connect();
                }
            }
        });

    }
    @Override
    public void changeState(){
        state = !state;
        checkState();
        for (Connection con : connectionList ){
            con.updateState();
        }
    }

    @Override
    public void delete() {
        for (Connection con : connectionList ){
            con.remove();
        }
        super.delete();
    }
}

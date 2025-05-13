package LogicGate.Nodes;

import LogicGate.Connection.Connection;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseButton;

public abstract class Node{
    protected Connection connection;
    protected Circle circle;
    protected boolean state = false;
    public final Pane parentPane;


    public Node(Pane pane){
        this.parentPane = pane;
    }

    public void draw(double x, double y) {

        circle = new Circle(x, y, 10, Color.WHITE);
        circle.setUserData(this);
        nodeClickHandler(circle);
        parentPane.getChildren().add(circle);
    }

    public void nodeClickHandler(Circle circle){
        circle.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                delete();
            }
        });

    }

    public void delete() {
        if (parentPane != null && circle != null) {
            parentPane.getChildren().remove(circle);
        }
    }

    public boolean getState(){
      return state;
    }

    public void disconnect() {
        this.connection = null;
    }

    public Circle getCircle() {
        return circle;
    }

    public void changeState(){
        state = !state;
        checkState();
    }

    public void checkState(){
        if (state){
            circle.setFill(Color.RED);
        }
        else{
            circle.setFill(Color.WHITE);
        }
    }

    public boolean isOutputNode() {
        return false;
    }
}
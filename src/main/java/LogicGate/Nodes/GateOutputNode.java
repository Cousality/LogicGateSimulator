package LogicGate.Nodes;

import LogicGate.Connection.Connection;
import LogicGate.Gates.Gate;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GateOutputNode extends InputNode {
    private Gate parentGate;

    public GateOutputNode(Pane pane){
        super(pane);

    }

    public void setParentGate(Gate gate) {
        this.parentGate = gate;
    }

    @Override
    public void draw(double x, double y) {

        circle = new Circle(x, y, 5, Color.WHITE);
        circle.setUserData(this);
        nodeClickHandler(circle);
        parentPane.getChildren().add(circle);
    }

    @Override
    public void nodeClickHandler(Circle circle){

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
    public void changeState() {
        boolean oldState = state;
        state = parentGate.getState();

        if (oldState != state) {
            checkState();

            if (shouldLimitChanges()) {
                for (Connection con : connectionList) {
                    con.updateState();
                }
                endEvaluation();
            }
        }
    }

    public void update(double x, double y){
        circle.setCenterX(x);
        circle.setCenterY(y);
        for (Connection con : connectionList ){
            con.updateStart(x,y);
        }

    }
}

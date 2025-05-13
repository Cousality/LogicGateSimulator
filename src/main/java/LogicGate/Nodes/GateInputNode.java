package LogicGate.Nodes;

import LogicGate.Gates.Gate;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GateInputNode extends OutputNode {
    private Gate parentGate;

    public GateInputNode(Pane pane) {
        super(pane);

    }
    @Override
    public void draw(double x, double y) {

        circle = new Circle(x, y, 5, Color.WHITE);
        circle.setUserData(this);
        parentPane.getChildren().add(circle);
    }

    public void update(double x, double y){
        circle.setCenterX(x);
        circle.setCenterY(y);
        if (connection != null){
            connection.updateWithGate();
        }
    }

    @Override
    public void changeState(){
        if (connection != null) {
            state = connection.getState();
        } else {
            state = false;
        }
        checkState();
        parentGate.checkGateState();


    }



    public void setParentGate(Gate gate) {
        this.parentGate = gate;
    }
}

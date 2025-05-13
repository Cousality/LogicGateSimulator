package LogicGate.Nodes;

import LogicGate.Gates.AndGate;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GateInputNode extends OutputNode {
    private boolean topInput;
    private AndGate parentGate;

    public GateInputNode(Pane pane, boolean topInput) {
        super(pane);
        this.topInput = topInput;
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



    public void setParentGate(AndGate gate) {
        this.parentGate = gate;
    }



}

package LogicGate.Gates;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public abstract class Gate {
    protected double height, width;
    protected double sceneHeight, sceneWidth;
    public final Pane parentPane;
    protected final int lineThiccness = 3;
    protected Group gateGroup;
    protected boolean moved = false;


    public Gate(Pane pane, double height, double width){
        this.height = height/15;
        this.width = width/25;
        this.sceneHeight = height;
        this.sceneWidth = width;
        parentPane = pane;
    }

    protected void setLineProperties(Line line) {
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(lineThiccness);
    }



    abstract void draw();

}

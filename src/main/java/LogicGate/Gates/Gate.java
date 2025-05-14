package LogicGate.Gates;

import LogicGate.Nodes.GateInputNode;
import LogicGate.Nodes.GateOutputNode;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;

public abstract class Gate {
    protected double height, width;
    protected double sceneHeight, sceneWidth;
    public final Pane parentPane;
    protected final int lineThiccness = 3;
    protected Group gateGroup;
    protected boolean moved = false;
    protected boolean state = false;

    protected GateInputNode inputNode1;
    protected GateInputNode inputNode2;
    protected GateOutputNode outputNode;
    protected Line inputLine1;
    protected Line inputLine2;
    protected Line outputLine;

    public Gate(Pane pane, double height, double width){
        this.height = height/15;
        this.width = width/25;
        this.sceneHeight = height;
        this.sceneWidth = width;
        parentPane = pane;
    }
    abstract void draw();
    abstract void createDuplicate();

    protected void setLineProperties(Line line) {
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(lineThiccness);
    }

    protected void setCurveProperties(CubicCurve curve) {
        curve.setStroke(Color.WHITE);
        curve.setStrokeWidth(lineThiccness);
    }

    protected void setArcProperties(Arc arc) {
        arc.setStroke(Color.WHITE);
        arc.setStrokeWidth(lineThiccness);
    }

    public void gateClickHandler(Group gateGroup){
        gateGroup.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (moved){
                    delete();
                }
            }
        });

        gateGroup.setOnMouseDragged(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                update(event.getSceneX()-width/2,event.getSceneY()-height/2);
            }
        });

        gateGroup.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (!moved){
                    createDuplicate();
                    checkGateState();
                }
                moved = true;


            }
        });
    }

    public void checkGateState(){
        System.out.println("Never see");
    }

    public boolean getState(){
        return state;
    }

    public void update(double x, double y){
        gateGroup.setTranslateX(x);
        gateGroup.setTranslateY(y);

        if (inputNode1 != null && inputNode2 != null && outputNode != null) {
            inputNode1.update(x + inputLine1.getStartX(), y +inputLine1.getStartY());
            inputNode2.update(x + inputLine2.getStartX(), y +inputLine2.getStartY());
            outputNode.update(x + outputLine.getEndX(), y + outputLine.getEndY());
        }
    }

    protected void createInputNodes(){
        inputNode1 = new GateInputNode(parentPane);
        inputNode2 = new GateInputNode(parentPane);

        inputNode1.draw(-100, -100);
        inputNode2.draw(-100, -100);

        inputNode1.setParentGate(this);
        inputNode2.setParentGate(this);

    }

    protected void createOutPutNode(){
        outputNode = new GateOutputNode(parentPane);
        outputNode.draw(-100, -100);

        outputNode.setParentGate(this);
    }

    public void delete(){
        if (inputNode1 != null) {
            inputNode1.delete();
        }
        if (inputNode2 != null) {
            inputNode2.delete();
        }
        if (outputNode != null) {
            outputNode.delete();
        }

        parentPane.getChildren().remove(gateGroup);

    }
}

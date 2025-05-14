package LogicGate.Gates;

import LogicGate.Nodes.GateInputNode;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class BufferGate extends Gate {

    public BufferGate(Pane pane, double height, double width){
        super(pane, height, width);

    }

    public void draw(){
        gateGroup = new Group();
        inputLine1 = new Line(-width/2 , (height/2), 0 , (height/2));

        outputLine = new Line(width, height/2, width*1.5, height / 2 );

        Line backLine = new Line(0,0,0,height);
        Line topLine = new Line(0, 0, width, height/2);
        Line bottomLine = new Line(0, height, width, height/2);

        Rectangle hitBox = new Rectangle(0,0,height,width);
        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.TRANSPARENT);


        setLineProperties(topLine);
        setLineProperties(bottomLine);
        setLineProperties(backLine);
        setLineProperties(inputLine1);
        setLineProperties(outputLine);

        gateGroup.getChildren().addAll(
                hitBox, outputLine,inputLine1,backLine, topLine,bottomLine);

        createInputNodes();
        createOutPutNode();

        gateClickHandler(gateGroup);
        parentPane.getChildren().add(gateGroup);

        gateGroup.setTranslateX(sceneWidth/35);
        gateGroup.setTranslateY(sceneHeight/5*4);
    }

    @Override
    protected void createInputNodes(){
        inputNode1 = new GateInputNode(parentPane);

        inputNode1.draw(-100, -100);

        inputNode1.setParentGate(this);


    }

    @Override
    public void update(double x, double y){
        gateGroup.setTranslateX(x);
        gateGroup.setTranslateY(y);

        if (inputNode1 != null && outputNode != null) {
            inputNode1.update(x + inputLine1.getStartX(), y +inputLine1.getStartY());
            outputNode.update(x + outputLine.getEndX(), y + outputLine.getEndY());
        }
    }

    protected void createDuplicate() {
        BufferGate duplicate = new BufferGate(parentPane, sceneHeight, sceneWidth);
        duplicate.draw();
    }

    public void checkGateState(){
        if (inputNode1.getState()){
            state = true;
            outputNode.changeState();
        }else{
            state = false;
            outputNode.changeState();
        }
    }
}

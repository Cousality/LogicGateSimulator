package LogicGate.Gates;

import LogicGate.Nodes.GateInputNode;
import LogicGate.Nodes.GateOutputNode;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class OrGate extends Gate{

    public OrGate(Pane pane, double height, double width){
        super(pane, height, width);

    }

    public void draw() {
        gateGroup = new Group();
        inputLine1 = new Line(-width/2 , (height/4), 0 , (height/4));
        inputLine2 = new Line(-width/2, (height/4)*3, 0, (height/4)*3);

        outputLine = new Line(width, height/2, width*1.5, height / 2 );


        Rectangle hitBox = new Rectangle(0,0,height,width);
        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.TRANSPARENT);

        setLineProperties(inputLine1);
        setLineProperties(inputLine2);
        setLineProperties(outputLine);


        gateGroup.getChildren().addAll(hitBox, outputLine,inputLine1,inputLine2);

        createInputNodes();
        createOutPutNode();

        gateClickHandler(gateGroup);
        parentPane.getChildren().add(gateGroup);

        gateGroup.setTranslateX(sceneWidth/35);
        gateGroup.setTranslateY((sceneHeight/5)*2);
    }

    private void createInputNodes(){
        inputNode1 = new GateInputNode(parentPane);
        inputNode2 = new GateInputNode(parentPane);

        inputNode1.draw(-100, -100);
        inputNode2.draw(-100, -100);

        inputNode1.setParentGate(this);
        inputNode2.setParentGate(this);


    }



    protected void createDuplicate() {
        OrGate duplicate = new OrGate(parentPane, sceneHeight, sceneWidth);
        duplicate.draw();
    }

    public void createOutPutNode(){
        outputNode = new GateOutputNode(parentPane);
        outputNode.draw(-100, -100);

        outputNode.setParentGate(this);
    }

    public void checkGateState(){
        if (inputNode1.getState() || inputNode2.getState()){
            state = true;
            outputNode.changeState();
        }else{
            state = false;
            outputNode.changeState();
        }
    }
}

package LogicGate.Gates;

import LogicGate.Nodes.GateInputNode;
import LogicGate.Nodes.GateOutputNode;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

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

        double radius = height /1.5;
        CubicCurve topArc = new CubicCurve();
        topArc.setStartX(-width/7);
        topArc.setStartY(0);
        topArc.setControlX1(width/10);
        topArc.setControlY1(0.0f);
        topArc.setControlX2(width/5);
        topArc.setControlY2(0.0f);
        topArc.setEndX(width);
        topArc.setEndY(height/2);
        topArc.setFill(null);

        CubicCurve bottomArc = new CubicCurve();
        bottomArc.setStartX(-width/7);
        bottomArc.setStartY(height);
        bottomArc.setControlX1(width/10);
        bottomArc.setControlY1(height);
        bottomArc.setControlX2(width/5);
        bottomArc.setControlY2(height);
        bottomArc.setEndX(width);
        bottomArc.setEndY(height/2);
        bottomArc.setFill(null);

        // Right arc (main body)
        Arc backArc = new Arc();
        backArc.setCenterX(-width + radius/1.5);
        backArc.setCenterY(height / 2);
        backArc.setRadiusX(radius);
        backArc.setRadiusY(radius);
        backArc.setStartAngle(315);
        backArc.setLength(90);
        backArc.setType(ArcType.OPEN);
        backArc.setFill(null);

        setCurveProperties(topArc);
        setCurveProperties(bottomArc);
        setArcProperties(backArc);

        setLineProperties(inputLine1);
        setLineProperties(inputLine2);
        setLineProperties(outputLine);


        gateGroup.getChildren().addAll(
                hitBox, outputLine, inputLine1, inputLine2, backArc, topArc,bottomArc);

        createInputNodes();
        createOutPutNode();

        gateClickHandler(gateGroup);
        parentPane.getChildren().add(gateGroup);

        gateGroup.setTranslateX(sceneWidth/35);
        gateGroup.setTranslateY((sceneHeight/5)*2);
    }

    protected void createInputNodes(){
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

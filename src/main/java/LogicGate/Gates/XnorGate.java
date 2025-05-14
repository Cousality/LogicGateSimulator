package LogicGate.Gates;

import LogicGate.Nodes.GateInputNode;
import LogicGate.Nodes.GateOutputNode;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class XnorGate extends XorGate{

    public XnorGate(Pane pane, double height, double width){
        super(pane, height, width);

    }
    @Override
    public void draw() {
        gateGroup = new Group();
        inputLine1 = new Line(-width/2 , (height/4), 0 , (height/4));
        inputLine2 = new Line(-width/2, (height/4)*3, 0, (height/4)*3);

        outputLine = new Line(width, height/2, width*1.5, height / 2 );

        Circle circle = new Circle(width+5,height/2,8, Color.WHITE);

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

        Arc backerArc = new Arc();
        backerArc.setCenterX(-width + radius/2);
        backerArc.setCenterY(height / 2);
        backerArc.setRadiusX(radius);
        backerArc.setRadiusY(radius);
        backerArc.setStartAngle(315);
        backerArc.setLength(90);
        backerArc.setType(ArcType.OPEN);
        backerArc.setFill(null);



        setCurveProperties(topArc);
        setCurveProperties(bottomArc);
        setArcProperties(backArc);
        setArcProperties(backerArc);
        setLineProperties(inputLine1);
        setLineProperties(inputLine2);
        setLineProperties(outputLine);


        gateGroup.getChildren().addAll(
                hitBox, outputLine, inputLine1, inputLine2, backArc,backerArc, topArc,bottomArc,circle);

        createInputNodes();
        createOutPutNode();

        gateClickHandler(gateGroup);
        parentPane.getChildren().add(gateGroup);

        gateGroup.setTranslateX(sceneWidth/1.075);
        gateGroup.setTranslateY((sceneHeight/5)*3);
    }




    @Override
    protected void createDuplicate() {
        XnorGate duplicate = new XnorGate(parentPane, sceneHeight, sceneWidth);
        duplicate.draw();
    }


    @Override
    public void checkGateState(){
        if (inputNode1.getState() && !inputNode2.getState() || !inputNode1.getState() && inputNode2.getState()){
            state = false;
            outputNode.changeState();
        }else{
            state = true;
            outputNode.changeState();
        }
    }
}

package LogicGate.Gates;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class NandGate extends AndGate {

    public NandGate(Pane pane, double height, double width){
        super(pane, height, width);

    }
    @Override
    public void draw() {
        gateGroup = new Group();
        double radius = height / 2.0;

        inputLine1 = new Line(-width/2 , (height/4), 0 , (height/4));
        inputLine2 = new Line(-width/2, (height/4)*3, 0, (height/4)*3);

        outputLine = new Line(width, height/2, width*1.5, height / 2 );

        Circle circle = new Circle(width+5,height/2,8, Color.WHITE);

        Line topLine = new Line(0, 0, width - radius, 0);
        Line bottomLine = new Line(0, height, width - radius, height);
        Line backLine = new Line(0,0,0,height);

        Rectangle hitBox = new Rectangle(0,0,height,width);
        hitBox.setFill(Color.TRANSPARENT);
        hitBox.setStroke(Color.TRANSPARENT);

        Arc arc = new Arc();
        arc.setCenterX(width - radius);
        arc.setCenterY(height / 2);
        arc.setRadiusX(radius);
        arc.setRadiusY(radius);
        arc.setStartAngle(270);
        arc.setLength(180);
        arc.setType(ArcType.OPEN);
        arc.setFill(null);
        arc.setStroke(Color.WHITE);
        arc.setStrokeWidth(lineThiccness);

        setLineProperties(inputLine1);
        setLineProperties(inputLine2);
        setLineProperties(topLine);
        setLineProperties(bottomLine);
        setLineProperties(backLine);
        setLineProperties(outputLine);

        gateGroup.getChildren().addAll(
                hitBox, outputLine,inputLine1,inputLine2, backLine, topLine, bottomLine, arc, circle);

        createInputNodes();
        createOutPutNode();

        gateClickHandler(gateGroup);
        parentPane.getChildren().add(gateGroup);

        gateGroup.setTranslateX(sceneWidth/1.075);
        gateGroup.setTranslateY(sceneHeight/5);
    }


    @Override
    protected void createDuplicate() {
        NandGate duplicate = new NandGate(parentPane, sceneHeight, sceneWidth);
        duplicate.draw();
    }


    @Override
    public void checkGateState(){
        if (inputNode1.getState() && inputNode2.getState()){
            state = false;
            outputNode.changeState();
        }else{
            state = true;
            outputNode.changeState();
        }
    }




}
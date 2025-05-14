package LogicGate.Gates;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class NotGate extends BufferGate {

    public NotGate(Pane pane, double height, double width){
        super(pane, height, width);

    }
    @Override
    public void draw(){
        gateGroup = new Group();
        inputLine1 = new Line(-width/2 , (height/2), 0 , (height/2));

        outputLine = new Line(width, height/2, width*1.5, height / 2 );

        Circle circle = new Circle(width+5,height/2,8, Color.WHITE);

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
                hitBox, outputLine,inputLine1,backLine, topLine,bottomLine, circle);

        createInputNodes();
        createOutPutNode();

        gateClickHandler(gateGroup);
        parentPane.getChildren().add(gateGroup);

        gateGroup.setTranslateX(sceneWidth/1.075);
        gateGroup.setTranslateY(sceneHeight/5*4);
    }

    @Override
    protected void createDuplicate() {
        NotGate duplicate = new NotGate(parentPane, sceneHeight, sceneWidth);
        duplicate.draw();
    }
    @Override
    public void checkGateState(){
        if (inputNode1.getState()){
            state = false;
            outputNode.changeState();
        }else{
            state = true;
            outputNode.changeState();
        }
    }

}

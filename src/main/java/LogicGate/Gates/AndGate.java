package LogicGate.Gates;

import LogicGate.Nodes.GateInputNode;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class AndGate extends Gate {
    private GateInputNode inputNode1;
    private GateInputNode inputNode2;
    private Line inputLine1;
    private Line inputLine2;
    private Line outputLine;


    public AndGate(Pane pane, double height, double width){
        super(pane, height, width);

    }

    public void draw() {
        gateGroup = new Group();
        double radius = height / 2.0;

        inputLine1 = new Line(-width/2 , (height/4), 0 , (height/4));
        inputLine2 = new Line(-width/2, (height/4)*3, 0, (height/4)*3);

        outputLine = new Line(width, height/2, width*1.5, height / 2 );

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

        gateGroup.getChildren().addAll(hitBox, outputLine,inputLine1,inputLine2, backLine, topLine, bottomLine, arc);

        createInputNodes();

        gateClickHandler(gateGroup);
        parentPane.getChildren().add(gateGroup);

        gateGroup.setTranslateX(sceneWidth/35);
        gateGroup.setTranslateY(sceneHeight/5);
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
                }
                moved = true;


            }
        });
    }

    private void createInputNodes(){
        inputNode1 = new GateInputNode(parentPane, true);
        inputNode2 = new GateInputNode(parentPane, false);

        double initialX = gateGroup.getTranslateX();
        double initialY = gateGroup.getTranslateY();

        inputNode1.draw(initialX + inputLine1.getStartX(), initialY + inputLine1.getStartY());
        inputNode2.draw(initialX + inputLine2.getStartX(), initialY + inputLine2.getStartY());





    }
    private void createDuplicate() {
        AndGate duplicate = new AndGate(parentPane, sceneHeight, sceneWidth);
        duplicate.draw();
    }

    public void update(double x, double y){
        gateGroup.setTranslateX(x);
        gateGroup.setTranslateY(y);

        if (inputNode1 != null && inputNode2 != null) {
            inputNode1.update(x + inputLine1.getStartX(), y +inputLine1.getStartY());
            inputNode2.update(x + inputLine2.getStartX(), y +inputLine2.getStartY());
        }
    }

    public void delete(){
        if (inputNode1 != null) {
            inputNode1.delete();
        }
        if (inputNode2 != null) {
            inputNode2.delete();
        }

        parentPane.getChildren().remove(gateGroup);

    }


}
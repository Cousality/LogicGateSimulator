package LogicGate.Connection;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import LogicGate.Nodes.InputNode;
import LogicGate.Nodes.OutputNode;
import LogicGate.Nodes.Node;


public class Connection {

    private Line line;
    private final Pane parentPane;
    private final InputNode sourceNode;
    private OutputNode targetNode;
    private boolean state = false;
    private boolean connected = false;
    private OutputNode closestOutputNode;

    private static final double SNAP_DISTANCE = 20.0;

    public Connection(Pane pane, InputNode sourceNode){
        this.parentPane = pane;
        this.sourceNode = sourceNode;
    }

    public void draw(double startX, double startY){
        line = new Line();
        state = sourceNode.getState();
        line.setStroke(getColor());

        line.setStrokeWidth(3);
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(startX);
        line.setEndY(startY);

        connectionClickHandler(line);
        parentPane.getChildren().add(line);
    }

    public void connectionClickHandler(Line line){
        line.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                remove();
            }
        });

    }
    public void update(double endX, double endY){
        closestOutputNode = findClosestOutputNode(endX,endY);
        if (closestOutputNode != null && !closestOutputNode.isConnected()){
            Circle nodeCircle = closestOutputNode.getCircle();
            line.setEndX(nodeCircle.getCenterX());
            line.setEndY(nodeCircle.getCenterY());
            connected = true;
            targetNode = closestOutputNode;

        }else{
            line.setEndX(endX);
            line.setEndY(endY);
            connected = false;
            targetNode = null;
        }
    }

    public void updateWithGate(){
        Circle nodeCircle = closestOutputNode.getCircle();
        line.setEndX(nodeCircle.getCenterX());
        line.setEndY(nodeCircle.getCenterY());
    }
    public void connect(){
        targetNode.connected(this);
        targetNode.changeState();
    }

    public void updateState(){
        state = sourceNode.getState();
        line.setStroke(getColor());
        targetNode.changeState();
    }

    private OutputNode findClosestOutputNode(double x, double y){
        OutputNode closest = null;
        double closestDistance = SNAP_DISTANCE;

        // Look through all nodes in the pane
        for (javafx.scene.Node javaFxNode : parentPane.getChildren()) {
            if (javaFxNode instanceof Circle circle) {

                // Check if this circle has user data that is a Node
                if (circle.getUserData() instanceof Node node) {

                    // Use the isOutputNode method to check if it's an output node
                    if (node.isOutputNode()) {
                        double distance = calculateDistance(x, y, circle.getCenterX(), circle.getCenterY());

                        if (distance < closestDistance) {
                            closest = (OutputNode) node;
                            closestDistance = distance;
                        }
                    }
                }
            }
        }

        return closest;
    }


    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    private Color getColor(){
        if (state){
            return Color.RED;
        }
        return Color.WHITE;
    }


    public boolean getState(){
        return state;
    }

    public void setState(boolean state){
        this.state = state;
    }


    public boolean getConnected(){
        return connected;
    }

    public void remove(){
        if (targetNode != null) {
            setState(false);
            targetNode.changeState();
            targetNode.disconnect();
        }

        parentPane.getChildren().remove(line);

    }
}

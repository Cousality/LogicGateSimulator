package LogicGate.Main;

import LogicGate.Gates.AndGate;
import LogicGate.Gates.BufferGate;
import LogicGate.Gates.OrGate;
import LogicGate.Gates.XorGate;
import LogicGate.Nodes.InputNode;
import LogicGate.Nodes.OutputNode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LogicGateMain extends Application {
    @Override
    public void start(Stage stage){
        Pane root = new Pane();
        Scene scene = new Scene(root, 320, 240, Color.rgb(30,30,35));

        stage.setTitle("Logic Gate Application");
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        double screenWidth = scene.getWidth();
        double screenHeight = scene.getHeight();

        Line leftLine = createLine(0.1,screenWidth,screenHeight);
        Line rightLine = createLine(0.9,screenWidth,screenHeight);

        CreateGates(root, screenHeight, screenWidth);




        root.getChildren().addAll(leftLine, rightLine);

        lineClickHandler(leftLine, "input", root);
        lineClickHandler(rightLine,"output",root);

    }

    public Line createLine(double multiplier, double screenWidth, double screenHeight) {
        Line createdLine = new Line(screenWidth * multiplier, 0, screenWidth * multiplier, screenHeight);
        createdLine.setStroke(Color.WHITE);
        createdLine.setStrokeWidth(5);
        return createdLine;
    }

    private void CreateGates(Pane pane, double height, double width){
        AndGate andGate = new AndGate(pane,height,width);
        OrGate orGate = new OrGate(pane, height,width);
        XorGate xorGate = new XorGate(pane, height,width);
        BufferGate bufferGate = new BufferGate(pane, height,width);
        andGate.draw();
        orGate.draw();
        xorGate.draw();
        bufferGate.draw();

    }

    private static void lineClickHandler(Line line, String type, Pane root) {
        line.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                switch (type) {
                    case "input":
                        InputNode inputNode = new InputNode(root);
                        inputNode.draw(line.getStartX(), event.getY());
                        break;
                    case "output":
                        OutputNode outputNode = new OutputNode(root);
                        outputNode.draw(line.getStartX(), event.getY());
                        break;

                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
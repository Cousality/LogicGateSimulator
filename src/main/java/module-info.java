module logicgate {
    requires javafx.controls;
    requires javafx.fxml;


    opens LogicGate.Main to javafx.fxml;
    exports LogicGate.Main;
}
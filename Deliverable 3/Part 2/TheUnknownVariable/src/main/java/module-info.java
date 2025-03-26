module com.example.theunknownvariable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.theunknownvariable to javafx.fxml;
    exports com.example.theunknownvariable;
    exports com.example.theunknownvariable.UI;
    opens com.example.theunknownvariable.UI to javafx.fxml;
    exports com.example.theunknownvariable.Controller;
    opens com.example.theunknownvariable.Controller to javafx.fxml;
    exports Model;
    opens Model to javafx.fxml;
}
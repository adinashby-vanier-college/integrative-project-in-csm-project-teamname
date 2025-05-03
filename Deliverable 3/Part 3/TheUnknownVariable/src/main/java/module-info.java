module com.example.theunknownvariable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;

    exports com.example.theunknownvariable;
    opens com.example.theunknownvariable to javafx.fxml;
    exports com.example.theunknownvariable.UI;
    opens com.example.theunknownvariable.UI to javafx.fxml;
    exports com.example.theunknownvariable.Controller;
    opens com.example.theunknownvariable.Controller to javafx.fxml;
    exports com.example.theunknownvariable.Model;
    opens com.example.theunknownvariable.Model to javafx.fxml;
}
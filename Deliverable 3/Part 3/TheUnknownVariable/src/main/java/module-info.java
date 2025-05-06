module com.example.theunknownvariable {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;

    // For testing (even though in main module-info)
    requires org.junit.jupiter.api;
    requires org.mockito;

    exports com.example.theunknownvariable;
    exports com.example.theunknownvariable.UI;
    exports com.example.theunknownvariable.Controller;
    exports com.example.theunknownvariable.Model;

    opens com.example.theunknownvariable.Controller to javafx.fxml, org.junit.platform.commons, TheUnknownVariable;
    opens com.example.theunknownvariable.Model to javafx.fxml;
    opens com.example.theunknownvariable.UI to TheUnknownVariable, javafx.fxml, org.junit.platform.commons;
    opens com.example.theunknownvariable to TheUnknownVariable, javafx.fxml, org.junit.platform.commons;
}
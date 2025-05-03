module test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires org.junit.jupiter.api;
    requires org.testfx.junit5;
    requires org.testfx;
    requires com.example.theunknownvariable;

    opens com.example.theunknownvariable.ModelTest to org.junit.platform.commons;
    opens com.example.theunknownvariable.UITest to org.junit.platform.commons;
    opens com.example.theunknownvariable.ControllerTest to org.junit.platform.commons;
}

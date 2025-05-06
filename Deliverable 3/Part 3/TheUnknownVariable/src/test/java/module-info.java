module TheUnknownVariable {
    requires com.example.theunknownvariable;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.junit.jupiter.api;
    requires org.mockito;
    requires org.mockito.junit.jupiter;
    requires org.testfx;
    requires org.testfx.junit5;

    exports com.example.theunknownvariable.ControllerTest to org.junit.platform.commons;
    opens com.example.theunknownvariable.ControllerTest to org.junit.platform.commons;
    exports com.example.theunknownvariable.ModelTest to org.junit.platform.commons;
}
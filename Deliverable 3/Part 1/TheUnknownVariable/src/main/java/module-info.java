module com.example.theunknownvariable {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.theunknownvariable to javafx.fxml;
    exports com.example.theunknownvariable;
}
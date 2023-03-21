module com.t.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires static lombok;
    requires com.google.gson;
    requires org.controlsfx.controls;
    requires annotations;


    exports com.t.library;
    exports com.t.library.Controller;
    exports com.t.library.Entity;
    exports com.t.library.Response;
    exports com.t.library.Servise;
    exports com.t.library.Utils;
    exports com.t.library.Model;
    opens com.t.library.Controller to javafx.fxml;
    opens com.t.library.Entity to com.google.gson;
    opens com.t.library.Response;

}
module com.t.library {
    requires javafx.controls; requires javafx.fxml;
    requires static lombok;
    requires okhttp3;

    requires com.google.gson;

    exports com.t.library;
    exports com.t.library.Controller;
    exports com.t.library.Entity;
    exports com.t.library.Response;
    exports com.t.library.Servise;
    exports com.t.library.Utils;
    opens com.t.library.Controller to javafx.fxml;
}
module com.hyfi.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires slf4j.api;
    requires lombok;
    requires org.json;
//    requires javafx.base;
//    requires javafx.graphics;
//    requires javafx.media;
//    requires javafx.web;
//    requires javafx.swing;
//    requires java.desktop;
    exports com.hyfi.tictactoe;
    opens com.hyfi.tictactoe to javafx.fxml;
    exports com.hyfi.tictactoe.Dijkstra;
    opens com.hyfi.tictactoe.Dijkstra to javafx.fxml;

}
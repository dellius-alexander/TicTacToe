module tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires slf4j.api;
    requires java.desktop;
    requires lombok;

    opens com.hyfi.tictactoe to javafx.fxml;
    exports com.hyfi.tictactoe;
}
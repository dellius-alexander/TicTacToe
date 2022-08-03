module com.hyfi.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires slf4j.api;
    requires java.desktop;
    requires lombok;
    exports com.hyfi.tictactoe;
    opens com.hyfi.tictactoe to javafx.fxml;
    exports com.hyfi.tictactoe.UI;
    opens com.hyfi.tictactoe.UI to javafx.fxml;

}
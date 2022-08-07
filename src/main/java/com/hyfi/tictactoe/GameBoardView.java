package com.hyfi.tictactoe;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GameBoardView extends Application implements GameBoardViewInterface {
    private static final Logger log = LoggerFactory.getLogger(GameBoardView.class);
    @FXML
    private FXMLBoardController fxmlGameBoard;
    @FXML
    private FXMLLoader gridBoardView;

    private Parent gameBoard;

    private Scene scene;

    private Stage primaryStage;

    public GameBoardView(){}

    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        log.info("Start method......");
        try {

            // Display our window, using the scene graph.
            stage.setTitle("Tic Tac Toe");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
        try {

            fxmlGameBoard = new FXMLBoardController();
            /////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////
            gridBoardView = new FXMLLoader();
            gridBoardView.setLocation(
                    getClass().getResource("/GridBoardView.fxml"));
            gridBoardView.setResources(fxmlGameBoard.getResources());
            /////////////////////////////////////////////////////////
            // Loads an object hierarchy from a FXML document to be the base of this scene
            gameBoard = gridBoardView.load();
            /////////////////////////////////////////////////////////
            // Build the scene graph.
            scene = new Scene(gameBoard);


        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        log.info("Initialization complete.........");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public FXMLBoardController getFxmlGameBoard() {
        return this.fxmlGameBoard;
    }

    public FXMLLoader getGridBoardView() {
        return this.gridBoardView;
    }

    public Parent getGameBoard() {
        return this.gameBoard;
    }

    public Scene getScene() {
        return this.scene;
    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

    public void setFxmlGameBoard(FXMLBoardController fxmlGameBoard) {
        this.fxmlGameBoard = fxmlGameBoard;
    }

    public void setGridBoardView(FXMLLoader gridBoardView) {
        this.gridBoardView = gridBoardView;
    }

    public void setGameBoard(Parent gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GameBoardView)) return false;
        final GameBoardView other = (GameBoardView) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$fxmlGameBoard = this.getFxmlGameBoard();
        final Object other$fxmlGameBoard = other.getFxmlGameBoard();
        if (this$fxmlGameBoard == null ? other$fxmlGameBoard != null : !this$fxmlGameBoard.equals(other$fxmlGameBoard))
            return false;
        final Object this$gridBoardView = this.getGridBoardView();
        final Object other$gridBoardView = other.getGridBoardView();
        if (this$gridBoardView == null ? other$gridBoardView != null : !this$gridBoardView.equals(other$gridBoardView))
            return false;
        final Object this$gameBoard = this.getGameBoard();
        final Object other$gameBoard = other.getGameBoard();
        if (this$gameBoard == null ? other$gameBoard != null : !this$gameBoard.equals(other$gameBoard)) return false;
        final Object this$scene = this.getScene();
        final Object other$scene = other.getScene();
        if (this$scene == null ? other$scene != null : !this$scene.equals(other$scene)) return false;
        final Object this$primaryStage = this.getPrimaryStage();
        final Object other$primaryStage = other.getPrimaryStage();
        if (this$primaryStage == null ? other$primaryStage != null : !this$primaryStage.equals(other$primaryStage))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GameBoardView;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $fxmlGameBoard = this.getFxmlGameBoard();
        result = result * PRIME + ($fxmlGameBoard == null ? 43 : $fxmlGameBoard.hashCode());
        final Object $gridBoardView = this.getGridBoardView();
        result = result * PRIME + ($gridBoardView == null ? 43 : $gridBoardView.hashCode());
        final Object $gameBoard = this.getGameBoard();
        result = result * PRIME + ($gameBoard == null ? 43 : $gameBoard.hashCode());
        final Object $scene = this.getScene();
        result = result * PRIME + ($scene == null ? 43 : $scene.hashCode());
        final Object $primaryStage = this.getPrimaryStage();
        result = result * PRIME + ($primaryStage == null ? 43 : $primaryStage.hashCode());
        return result;
    }

    public String toString() {
        return "GameBoardView{" +
                "\n\tfxmlGameBoard:" + this.getFxmlGameBoard() +
                ", \n\tgridBoardView:" + this.getGridBoardView() +
                ", \n\tgameBoard:" + this.getGameBoard() +
                ", \n\tscene:" + this.getScene() +
                ", \n\tprimaryStage:" + this.getPrimaryStage() +
                "\n\t}";
    }

    public static void main(String[] args)
    {
        launch(GameBoardView.getUserAgentStylesheet());
    }
}


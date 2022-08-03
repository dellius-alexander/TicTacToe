package com.hyfi.tictactoe;


import com.hyfi.tictactoe.UI.FXMLBoardController;

import javafx.application.Application;
import javafx.event.EventDispatcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseEvent;

public class GameBoardView extends Application implements GameBoardViewInterface {
    private static final Logger log = LoggerFactory.getLogger(GameBoardView.class);
    @FXML
    private FXMLBoardController fxmlGameBoard;
    @FXML
    private FXMLLoader gridBoardView;

    private Parent gameBoard;

    private Scene scene;


    private Stage primaryStage;

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

    public static void main(String[] args)
    {
        launch(args);
    }

}


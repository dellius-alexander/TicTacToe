package com.hyfi.tictactoe;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GameBoardView extends Application implements GameBoardViewInterface {
    private static final Logger log = LoggerFactory.getLogger(GameBoardView.class);
    @FXML
    private FXMLDialogController fxmlDialog;
    @FXML
    private FXMLBoardController fxmlGameBoard;
    @FXML
    private FXMLLoader gridBoardView;
    @FXML
    private FXMLLoader dialogueBox;

    private Parent gameBoard;

    private Parent dialog;

    private Scene scene;
//    public GameBoardView(){super();}
    public void start(Stage stage) throws Exception {
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
            fxmlDialog = new FXMLDialogController();
            fxmlGameBoard = new FXMLBoardController();
            /////////////////////////////////////////////////////////
            dialogueBox = new FXMLLoader();
            dialogueBox.setLocation(
                    getClass().getResource("/Dialogue.fxml"));
            dialogueBox.setResources(fxmlDialog.getResources());
            /////////////////////////////////////////////////////////
            gridBoardView = new FXMLLoader();
            gridBoardView.setLocation(
                    getClass().getResource("/GridBoardView.fxml"));
            gridBoardView.setResources(fxmlGameBoard.getResources());
            /////////////////////////////////////////////////////////
            // Loads an object hierarchy from a FXML document to be the base of this scene
            gameBoard = gridBoardView.load();
            // Loads an object hierarchy from a FXML document to be the base of this scene
            dialog = dialogueBox.load();
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

//    @FXML // mouse click events
//    void onMouseClicked(MouseEvent event) throws Exception {
//        boolean clicked = fxmlDialog.onMouseClicked(event);
//        log.info("Event Source: {}",event.getSource().toString());
//        log.info("Event Source: {}",event.getSource());
//        log.info("Clicked: {}",clicked);
//        if (event.getSource() instanceof Button){
//            Button b = (Button) event.getSource();
//            if (b.getId().equalsIgnoreCase("yes")){
//
//            }
//        }
//    }
    public static void main(String[] args)
    {
        launch(args);
    }

}

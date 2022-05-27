package com.hyfi.tictactoe;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
/**
 * UI Controller
 */
public class FXMLBoardController {
    private static final Logger log = LoggerFactory.getLogger(FXMLBoardController.class);
    private Image xs;
    private Image os;
    private Board board;
    private Minimax minimax;
    private String playerTurn = "\u0000";

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="borderPane"
    private BorderPane borderPane; // Value injected by FXMLLoader

    @FXML // fx:id="gridPane"
    private GridPane gridPane; // Value injected by FXMLLoader

    @FXML // fx:id="imageView0"
    private ImageView imageView0; // Value injected by FXMLLoader

    @FXML // fx:id="imageView1"
    private ImageView imageView1; // Value injected by FXMLLoader

    @FXML // fx:id="imageView2"
    private ImageView imageView2; // Value injected by FXMLLoader

    @FXML // fx:id="imageView3"
    private ImageView imageView3; // Value injected by FXMLLoader

    @FXML // fx:id="imageView4"
    private ImageView imageView4; // Value injected by FXMLLoader

    @FXML // fx:id="imageView5"
    private ImageView imageView5; // Value injected by FXMLLoader

    @FXML // fx:id="imageView6"
    private ImageView imageView6; // Value injected by FXMLLoader

    @FXML // fx:id="imageView7"
    private ImageView imageView7; // Value injected by FXMLLoader

    @FXML // fx:id="imageView8"
    private ImageView imageView8; // Value injected by FXMLLoader

    private Map<String, Object> fxmlPL = new HashMap<>();

    /**
     * Initializes the Model and View for rendering
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        try{
            log.info("Initialization starting.........");
            assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert imageView0 != null : "fx:id=\"imageView0\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert imageView1 != null : "fx:id=\"imageView1\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert imageView2 != null : "fx:id=\"imageView2\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert imageView3 != null : "fx:id=\"imageView3\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert imageView4 != null : "fx:id=\"imageView4\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert imageView5 != null : "fx:id=\"imageView5\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert imageView6 != null : "fx:id=\"imageView6\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert imageView7 != null : "fx:id=\"imageView7\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert imageView8 != null : "fx:id=\"imageView8\" was not injected: check your FXML file 'GridBoardView.fxml'.";

            fxmlPL.put("borderPane",borderPane);
            fxmlPL.put("gridPane",gridPane);
            fxmlPL.put("location",location);
            fxmlPL.put("resources",resources);
            fxmlPL.put("image_views", new ImageView[]{
                    imageView0,imageView1,imageView2,
                    imageView3,imageView4,imageView5,
                    imageView6,imageView7,imageView8
            });
            playerTurn = "X";
            fxmlPL.put("playerTurn",playerTurn);
            board = new Board(new Object[3][3]);
            board.setHumanMark('X');
            board.setComputerMark('O');
            minimax = new Minimax(board);
            fxmlPL.put("board",board);
            fxmlPL.put("minimax",minimax);
            //Load the dog and cat images
            xs = new Image(new FileInputStream("/Users/dalexander/SynologyDrive/Repos/TicTacToe/target/classes/images/xs.png"));
            os = new Image(new FileInputStream("/Users/dalexander/SynologyDrive/Repos/TicTacToe/target/classes/images/os.png"));
            fxmlPL.put("os",os);
            fxmlPL.put("xs",xs);
            borderPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            borderPane.getCenter().setVisible(true);
            gridPane.setVisible(true);
            log.info("\nInitializing completed.\nX loaded: {} | O loaded: {}",xs.getProgress(),os.getProgress());

        } catch (FileNotFoundException e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * Randomly assigns an image loaded by the {@link Image} class to the ImageView Node.
     * @param playerMove {@linkplain ImageView}
     */
    private void makeAMove(ImageView playerMove, String player) {
        log.info("\nMaking-A-Move...!\nID: {}",playerMove.getId().charAt(9));

        int move = Integer.parseInt(String.valueOf(playerMove.getId().charAt(9)));
        char symbol = player.charAt(0);
        try {
            if ( player.matches("([Oo])" )) {
                log.info("{} player moved to {}",player, move);
                playerMove.setImage(os);
                playerMove.setSmooth(true);
                log.info("Move: {}",move);
                board.placeAMove(new Point(move,9),symbol);
                while ( os.getProgress() < 1.0 ){
                    wait(1000L);
                    log.info("Waiting for image to load.........");
                }
            } else if ( player.matches("([Xx])" )){
                log.info("{} player moved to {}",player, move);
                playerMove.setImage(xs);
                playerMove.setSmooth(true);
                log.info("Move: {}",move);
                board.placeAMove(new Point(move,9),symbol);
                while ( xs.getProgress() < 1.0 ){
                    wait(1000L);
                    log.info("Waiting for image to load.........");
                }
            }
        } catch (InterruptedException e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }

        log.info("Player {} move completed........", player);
        return;
    }
    /**
     * Handles Grid Pane mouse events for each cell.
     * @param event {@linkplain MouseEvent}
     */
    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {
        log.info("\nButton: {}",((ImageView)event.getSource()).getId().trim());
        int best_move = 0;
        playerTurn = "X";
        for (ImageView view : (ImageView[]) fxmlPL.get("image_views"))
        {
            log.info("ImageView: {}",view.toString());
            if ( view.getId().matches( // test for matching ID among cell selection
                    "(" + ((ImageView)event.getSource()).getId().trim() + ")*") ) {
                log.info("Match found: {}",view.getId());
                makeAMove(view,playerTurn); // make a move on the game board
                log.info("\nThread: {}",Thread.getAllStackTraces().entrySet().stream().collect(Collectors.toList()));
                playerTurn = "O"; // change player
                log.info("Player turn changed to {}",playerTurn);
                minimax.minimax(0,'O'); // test for computer best move
                int sub = board.getComputerMove().getSub();
                log.info("\nBest Move: {}",sub);
                ImageView player2 = (((ImageView[])fxmlPL.get("image_views"))[sub]);
                makeAMove(player2, playerTurn); // make a move for computer
                playerTurn = "X"; // change player back
            }
        }

    }



}

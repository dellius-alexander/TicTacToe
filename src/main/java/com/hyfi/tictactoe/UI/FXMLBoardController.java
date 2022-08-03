package com.hyfi.tictactoe.UI;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.*;

import com.hyfi.tictactoe.Board;
import com.hyfi.tictactoe.Minimax;
import com.hyfi.tictactoe.Point;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
/**
 * UI Controller
 */
public class FXMLBoardController implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(FXMLBoardController.class);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="borderPane"
    private BorderPane borderPane; // Value injected by FXMLLoader

    @FXML // fx:id="dialog"
    private Text dialog; // Value injected by FXMLLoader

    @FXML // fx:id="dialogBox"
    private AnchorPane dialogBox; // Value injected by FXMLLoader

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

    @FXML // fx:id="no"
    private Button no; // Value injected by FXMLLoader

    @FXML // fx:id="yes"
    private Button yes; // Value injected by FXMLLoader

    // Map container for UI objects
    private Map<String, Object> fxmlPL = new HashMap<>();
    private Random rand = new Random();
    private Image userImg;
    private Image oppImg;
    private Board board;
    private Minimax minimax;
    private String opponent = "\u0000";
    private String user = "\u0000";
    private boolean isGameOver;
    private boolean isGameStarted;
    private String greeting = String.format(
            "\n**** WELCOME TO THE TIC-TAC-TOE GAME ****\n" +
                    "\n------ Author: Dellius A. Alexander -----\n" +
                    "\nWould you like to play?\n");

    /**
     * Initializes the Model and View for rendering
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        try {
            log.info("Initialization starting.........");
            // setup game board
            board = new Board(new Object[3][3]);
            // setup Minimax algorithm
            minimax = new Minimax(board);
            // set game over token
            isGameOver = true;
            isGameStarted = false;
            // set no as cancel button
            no.setCancelButton(true);

            // check if any of out UI objects are null and throw message
            assert borderPane != null : "fx:id=\"borderPane\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert dialog != null : "fx:id=\"dialog\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert dialogBox != null : "fx:id=\"dialogBox\" was not injected: check your FXML file 'GridBoardView.fxml'.";
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
            assert no != null : "fx:id=\"no\" was not injected: check your FXML file 'GridBoardView.fxml'.";
            assert yes != null : "fx:id=\"yes\" was not injected: check your FXML file 'GridBoardView.fxml'.";


            // add all objects to our Map
            fxmlPL.put("borderPane", borderPane);
            fxmlPL.put("dialog", dialog);
            fxmlPL.put("dialogBox", dialogBox);
            fxmlPL.put("gridPane", gridPane);
            fxmlPL.put("location", location);
            fxmlPL.put("resources", resources);
            fxmlPL.put("no",no);
            fxmlPL.put("yes",yes);
            fxmlPL.put("board", board);
            fxmlPL.put("minimax", minimax);
            fxmlPL.put("borderPane",borderPane);
            fxmlPL.put("gridPane",gridPane);
            fxmlPL.put("opponent", opponent);
            fxmlPL.put("user", user);
            fxmlPL.put("oppImg", oppImg);
            fxmlPL.put("userImg", userImg);

            fxmlPL.put("imageViews", new ImageView[]{
                    imageView0, imageView1, imageView2,
                    imageView3, imageView4, imageView5,
                    imageView6, imageView7, imageView8
            });

            System.out.printf("\nSubscript: %s",imageView0.getId());

            fxmlPL.put( imageView0.getId(), imageView0 );
            fxmlPL.put( imageView1.getId(), imageView1 );
            fxmlPL.put( imageView2.getId(), imageView2 );
            fxmlPL.put( imageView3.getId(), imageView3 );
            fxmlPL.put( imageView4.getId(), imageView4 );
            fxmlPL.put( imageView5.getId(), imageView5 );
            fxmlPL.put( imageView6.getId(), imageView6 );
            fxmlPL.put( imageView7.getId(), imageView7 );
            fxmlPL.put( imageView8.getId(), imageView8 );

            System.out.printf("\nSubscript: %s",imageView8.getId());

            // set background color
            borderPane.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)
                    ));


            dialog.setText(greeting);
            dialog.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
            dialog.requestFocus();
            dialogBox.autosize();
            borderPane.autosize();
            borderPane.getTop().setVisible(true);
            borderPane.getCenter().setVisible(false);
            borderPane.getCenter().prefHeight(850);
            borderPane.getCenter().prefWidth(850);

        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles Grid Pane mouse events for each cell.
     * @param event {@linkplain MouseEvent}
     */
    @FXML
    private void handleButtonAction(MouseEvent event) throws InterruptedException, FileNotFoundException {

        // process terminal restart events
        if (!isGameStarted && isGameOver){

            if (event.getSource() instanceof AnchorPane){
                AnchorPane anchorPane = (AnchorPane) event.getSource();
                System.out.printf("\nAnchor Pane: ",anchorPane.toString());
            }

            if (event.getSource() instanceof Button){
                Button button = (Button) event.getSource();

                if (button.getId().equalsIgnoreCase("yes"))
                {
                    System.out.printf("\nEvent.getSource() Button: %s ", button.getId());
                    isGameOver = false;
                    isGameStarted = true;
                    dialog.setText("");
                    dialog.setVisible(false);
                    yes.setVisible(false);
                    no.setVisible(false);
                    dialogBox.setVisible(false);

                    dialog.resizeRelocate(-1,-1,-1,-1);
                    yes.resizeRelocate(-1,-1,-1,-1);
                    no.resizeRelocate(-1,-1,-1,-1);
                    dialogBox.resizeRelocate(-1,-1,-1,-1);

                    borderPane.getTop().setVisible(false);
                    borderPane.getTop().resizeRelocate(-1,-1,-1,-1);
                    borderPane.getCenter().setVisible(true);
                    borderPane.getCenter().resizeRelocate(0,0,Integer.MAX_VALUE,Integer.MAX_VALUE);
                    gridPane.resizeRelocate(0,0,Integer.MAX_VALUE,Integer.MAX_VALUE);
                    gridPane.setBackground(
                            new Background(
                                    new BackgroundFill(
                                            Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)
                            ));
                    // randomly assign player tokens
                    setupPlayerToken();
                }
                if (button.getId().equalsIgnoreCase("no")){
                    System.exit(0);
                }
            }
        }
        // process game play events
        if (isGameStarted && !isGameOver && event.getSource() instanceof ImageView)
        {
            ImageView imageView = (ImageView) event.getSource();
            String viewId = imageView.getId();
            String sub = viewId.substring(9,10);
            System.out.printf("\nButton: %s | Subscript: %s", viewId, sub);
            Point point = new Point(board.getRow(Integer.parseInt(sub)), board.getCol(Integer.parseInt(sub)), board.getBoardSize());
            System.out.printf("\nUser: %s | Point: %s", user, point);
            board.placeAMove(point,user);
            System.out.printf("\nGame Board: %s",board.displayBoard(board.getGameBoard()));
            imageView.setImage(userImg);
            while ( userImg.getProgress() < 1.0 )
            {
                Thread.sleep(1000L);
                System.out.printf("\nWaiting for image to load.........");
            }
            System.out.printf("\n%s load completed......", imageView.getId());
            // check for user win
            if(board.hasPlayerWon(user))
            {
                Thread.sleep(3000L);
                String winMsg = String.format("\nPlayer %s Won......" +
                        "\n\nWould you like to play again......\n\n%s",
                        user, board.displayBoard(board.getGameBoard()));
                System.out.printf("\n%s",winMsg);
                terminalMsg(winMsg);
                return;
            }
            else if (board.getAvailableCells().isEmpty()){
                Thread.sleep(3000L);
                String winMsg = String.format("\nGame is a Draw......" +
                                "\n\nWould you like to play again......\n\n%s",
                        board.displayBoard(board.getGameBoard()));
                System.out.printf("\n%s",winMsg);
                terminalMsg(winMsg);
                return;
            } else {
                // switch to opponent
                int move = minimax.minimax(
                        0,
                        opponent,
                        new Board(board),
                        Integer.MIN_VALUE,
                        0
                );

                System.out.printf("\nMinimax Score: %s | Computer Moves: %s", move, minimax.getBestmoves());
                point = minimax.getBestmoves().get(move);
                minimax.getBestmoves().clear();
                System.out.printf("\nOpponent: %s | Point: %s", opponent, point);
                board.placeAMove(point,opponent);
                System.out.printf("\nGame Board: %s",board.displayBoard(board.getGameBoard()));
                imageView =  (ImageView) fxmlPL.get("imageView" + point.getSub());
                imageView.setImage(oppImg);
                while ( oppImg.getProgress() < 1.0 )
                {
                    Thread.sleep(1000L);
                    System.out.printf("Waiting for image to load.........");
                }
                System.out.printf("\n%s load completed......", imageView.getId());
            }
            // check for opponent win
            if(board.hasPlayerWon(opponent))
            {
                Thread.sleep(3000L);
                String winMsg = String.format("\nPlayer %s Won......" +
                        "\n\nWould you like to play again......\n\n%s",
                        opponent, board.displayBoard(board.getGameBoard()));
                System.out.printf("\n%s",winMsg);
                terminalMsg(winMsg);
                return;
            }
            else if (board.getAvailableCells().isEmpty()){
                Thread.sleep(3000L);
                String winMsg = String.format("\nGame is a Draw......" +
                                "\n\nWould you like to play again......\n\n%s",
                        board.displayBoard(board.getGameBoard()));
                System.out.printf("\n%s",winMsg);
                terminalMsg(winMsg);
                return;
            }
        }
    }

    /**
     * Process terminal events.
     * @param msg
     * @return true upon completion
     */
    public boolean terminalMsg(String msg)
    {
        dialog.setText(msg);
        board.clearBoard();
        board = new Board(board);
        minimax = new Minimax(board);
        isGameOver = true;
        isGameStarted = false;

        dialog.setVisible(true);
        yes.setVisible(true);
        no.setVisible(true);
        dialogBox.setVisible(true);

        dialog.resizeRelocate(300,10, 300, 200);
        yes.resizeRelocate(275,400, 10, 10);
        no.resizeRelocate(640, 400, 10, 10);
        dialogBox.resizeRelocate(0, 0, Integer.MAX_VALUE,Integer.MAX_VALUE);
        gridPane.resizeRelocate(-1,-1,Integer.MIN_VALUE, Integer.MIN_VALUE);

        borderPane.getTop().setVisible(true);
        borderPane.getTop().resizeRelocate(0, 0, 850,850);
        borderPane.getCenter().setVisible(false);
        borderPane.getCenter().resizeRelocate(-1,-1,Integer.MIN_VALUE, Integer.MIN_VALUE);

        for ( ImageView iv : (ImageView[]) fxmlPL.get("imageViews")){
            iv.setImage(null);
        }
        return true;
    }

    public void setupPlayerToken() throws FileNotFoundException {
        if ( (rand.nextInt(3) % 2 == 0) ){
            opponent = "X";
            oppImg = new Image(new FileInputStream(
                    "/Users/dalexander/SynologyDrive/Repos/TicTacToe/target/classes/images/xs.png"));
            user = "O";
            userImg = new Image(new FileInputStream(
                    "/Users/dalexander/SynologyDrive/Repos/TicTacToe/target/classes/images/os.png"));
            board.setComputerMark(opponent);
            board.setHumanMark(user);
            System.out.printf("\nInitializing completed.\n%s loaded: %s | %s loaded: %s",
                    user, userImg.getProgress(), opponent, oppImg.getProgress());

            int sub = rand.nextInt( (int) Math.pow(board.getBoardSize(),2) + 1);
            if (sub % 2 == 0)
            { // place the first move
                sub = sub - 1;
                Point p = new Point(board.getRow(sub),board.getCol(sub), board.getBoardSize());
                board.placeAMove(p,opponent);
                ImageView iv = (ImageView) fxmlPL.get("imageView"+ p.getSub());
                iv.setImage(oppImg);
                System.out.printf("\nGame Board: %s",board.displayBoard(board.getGameBoard()));
            }
        } else {
            opponent = "O";
            oppImg = new Image(new FileInputStream(
                    "/Users/dalexander/SynologyDrive/Repos/TicTacToe/target/classes/images/os.png"));
            user = "X";
            userImg = new Image(new FileInputStream(
                    "/Users/dalexander/SynologyDrive/Repos/TicTacToe/target/classes/images/xs.png"));
            board.setComputerMark(opponent);
            board.setHumanMark(user);
            System.out.printf("\nInitializing completed.\n%s loaded: %s | %s loaded: %s",
                    user, userImg.getProgress(), opponent, oppImg.getProgress());
        }
    }

//    public Object setConfig(Object node, int X, int Y){
//
//        if (node instanceof Text)
//        {
//            Text n = (Text)node;
//            n.setX(X);
//            n.setY(Y);
//            n.setTextOrigin(VPos.CENTER);
//            n.setTextAlignment(TextAlignment.JUSTIFY);
//            n.setWrappingWidth(50);
//            n.setFill(Color.BLACK);
//            n.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
//            return n;
//        }
//        else if (node instanceof Label)
//        {
//            Label n = (Label) node;
//            n.setLayoutX(X);
//            n.setLayoutY(Y);
//            n.setAlignment(Pos.CENTER);
//            n.setWrapText(true);
//            n.setTextFill(Color.BLACK);
//            n.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
//            return n;
//        }
//        else if (node instanceof Button)
//        {
//            Button b = (Button) node;
////            b.setLayoutX(X);
////            b.setLayoutY(Y);
//            b.setAlignment(Pos.CENTER);
//            b.setWrapText(true);
//            b.setTextFill(Color.ROYALBLUE);
//            b.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
//            return b;
//
//        }
//        return null;
//    }
}

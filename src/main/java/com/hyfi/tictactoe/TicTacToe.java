package com.hyfi.tictactoe;
// Imports for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Class imports
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * This is a Tic Tac Toe game. It allows the user to play against the computer. Two players take turns to place their mark on a "3X3" grid of spaces with an X or 0. The player to move first will be randomly chosen. The first player will be assigned "X" and "0" will be assigned to the second player. The player who succeeds first in placing three marks in horizontal, vertical, or diagonal row wins the game. The game board is designed from a 1 dimensional array. 
 * @version 2.2
 * @since 2019-04-12
 */
public class TicTacToe {
    private static final Logger log = LoggerFactory.getLogger(TicTacToe.class);
    private static final Object NO_PLAYER="";
    // A new instance of the game board
    private Board board;
    // Instantiate Minimax class algorithm
    private Minimax mini;
    // A new instance of the random class.
    private Random rand = new Random();
    // A constant that represents user
    private static final int USERS_TURN = 1;
    // A constant that represents computer
    private static final int COMPUTERS_TURN = 0;
    // remember to get the size of the game board
    private int boardSize;
    // Holds the symbol/mark computer will be assigned at the start of game
    private Object computersMark;
    // Holds the symbol/mark user will be assigned at the start of game
    private Object humansMark;
    // Holds the value of player turn
    private  int turn;
    // Holds the value of current round
    private  int round;
    // Get user input
    private Scanner keyboard = new Scanner(System.in);
    // Holds the value from keyboard input
    private String input;
    // Input validator
    private Pattern inputValidator;
    /**
     * Constructor of main class
     */
    public TicTacToe() {
        // Log a simple INFO message.
        log.info("Constructor init...");
        // System.exit(0);
        board = this.askBoardSize() ;
        // assign the boardSize
        boardSize =  board.getGameBoard().length;
        // Instantiate Minimax class algorithm
        mini = new Minimax(board);
        // Instantiate the PointAndScore class
//        pointAndScore = new PointAndScore(board);
        //3

        this.inputValidator = Pattern.compile("^[1-"+ board.getBoardSize() + "]{"+(String.valueOf(board.getBoardSize()).length())+"}$");
        log.info("Input Validator: {} | Board Length: {}", inputValidator, board.getGameBoard().length);;
               
    }
    /**
     * Prompt user for game board size of N length
     * @return the contructed game board of N length
     */
    private Board askBoardSize(){
        boolean valid_response = false;
        Integer rsp;
        do {
            // Prints newline character
            System.out.println();
            System.out.print("What is the board size you would like to explore:  ");
            input = keyboard.next();            
            if (!input.matches("^[0-9]*$"))
                valid_response = false;
            else
            {
                valid_response = true;                
            }
            
            rsp = Integer.valueOf(input);
        } while (!valid_response);
        System.out.println();
        return new Board(new Object[rsp][rsp]);
    }

    /**
     * The getRow method accepts the value for the subscript
     * and returns the value of the row.
     * @param sub	The subscript value.
     * @return		The value of the row.
     */
    public int getRow(int sub) {
        return (int) (sub / Math.sqrt(this.boardSize));
    }
    /**
     * The getCol method accepts the value of the subscript
     * and returns the value of the column.
     * @param sub 	The subscript value.
     * @return		The value of the column.
     */
    public int getCol(int sub) {
        return (int) (sub % Math.sqrt(this.boardSize));
    }
    /**
     * The firstMove method will randomly decide who goes first.
     * This method will assign "X" to the first player and "0"
     * to the second player. The method initiates the first move
     * of the game.
     * @throws IOException  throws IOException
     * @throws SecurityException throws security exception
     */
    public void firstMove() throws SecurityException, IOException {

        // Holds the value of the player to move first
        int firstMover;
        // Holds the value of the player turn
        int whosTurn;
        // Random number between 1-10
        whosTurn = rand.nextInt(10)+1;
        // Returns a value of 0 or 1
        whosTurn = whosTurn % 2;
        // Holds the value of player to move first
        firstMover = whosTurn;
        // Assign characters "X" to first player and '0' to second player
        if (firstMover == USERS_TURN) {
            computersMark = "O";
            humansMark = "X";
            board.setHumanMark(humansMark);
            board.setComputerMark(computersMark);
            System.out.println("You move first!\n");
        }
        else {
            computersMark = "X";
            humansMark = "O";
            board.setHumanMark(humansMark);
            board.setComputerMark(computersMark);
            System.out.println("I will move first!\n");
        }
        log.info("Computer Mark: {}",computersMark);
        if (firstMover == USERS_TURN) {
            round += 1;
            humanMove();
            turn = COMPUTERS_TURN;
        }
        else {
            int sub;
            int test;
            round += 1;
            board.setRound(round);
            do{
                // generate a random subscript for the first computer move
                sub = rand.nextInt(board.getGameBoard().length-1);
                test = sub % 2;
            } while (test == 1 || sub == (board.getGameBoard().length));
            Point point = new Point(getRow(sub), boardSize);
//            Point point = board.getBestMove();
//            Point point = new Point(getRow(sub)-1,getCol(sub)-1,boardSize);
            point.setBoardSize(this.boardSize);
            // add the next move to list of last moves played
            board.placeAMove(point, computersMark);

            System.out.println();
            System.out.printf("\nI marked %s at row (1-%d): %d",computersMark, this.boardSize, point.getRow()+1);
            // Prints newline character
            System.out.println();
            System.out.printf("\n\t    column(1-%d): %d",this.boardSize, point.getCol()+1);
            System.out.println();
            board.displayBoard();
            turn = USERS_TURN;
        }
    }

    /**
     * The humanMove method is used during user turn to interact
     * with the user during game play.  It collects the user
     * input and assigns the user move to the game board.
     * @throws IOException  throws IOException
     * @throws SecurityException throws security exception
     */
    public void humanMove() throws SecurityException, IOException {
        Point point = null;
        boolean emptySubscript;
        int row;
        int col;
        do {
            do {
                System.out.printf("\nYOUR TURN, enter %s at row(1-%d):  ",humansMark,this.boardSize);
                input = keyboard.next().trim();
            } while ( !inputValidator.matcher(input).matches() );
            row = (Integer.parseInt(input) - 1);

            do {
                System.out.printf("\nNow enter column number(1-%d):  ",this.boardSize);
                input = keyboard.next().trim();
            } while ( !inputValidator.matcher(input).matches() );
            col = (Integer.parseInt(input) - 1);
            // Finds subscript value from row and column
            point = new Point(row, col, boardSize);
            log.info("Point: {}",point);
            emptySubscript = board.getGameBoard()[point.getRow()][point.getCol()] == NO_PLAYER;
            if (!emptySubscript) {
                System.out.print("\nInvalid selection, ROW " + (row+1) +
                        " \nCOLUMN " + (col+1) +
                        " is FILLED, \nmake another selection!");
                System.out.println();
                board.displayBoard();
            }
        }while (!emptySubscript);
        // add the next move to list of last moves played
        board.placeAMove(point, humansMark);
        board.displayBoard();
    }
    /**
     * The computerMove method initiates the minimax algorithm
     * and plays the best possible move for win or draw.
     * @throws IOException throws IOException
     */
    public void computerMove() {
        // find the best move for this round of game play
        int move = mini.minimax(
                0,
                computersMark,
                new Board(board),
                Integer.MIN_VALUE,
                0
        );
//        int move = mini.bestMove(
//                0,
//                (Integer) mini.getBestmoves().keySet().toArray()[0],
//                mini.getBestmoves(),
//                board,
//                board.getComputerMark()
//        );
        Point point = board.getComputerMoves().get(move);
        System.out.printf("\nBest Results: \"%s\", Computer Moves: %s\n",
                move, board.getComputerMoves());
//        System.out.printf("\nComputer Moves: %s\n", board.getComputerMoves());
        System.out.printf("\nMY TURN, \n\tI marked %s at Point: %s",
                computersMark, point);
        // add the next move to list of last moves played
        board.placeAMove(point, computersMark);
        board.displayBoard();
    }

    /**
     * The winnerExist determines a winner or draw,
     * and displays a statement to the user.
     */
    public void winnerExist() {

        if (board.hasPlayerWon(computersMark))
            System.out.println("I Won! You Lost!\n\n");
        else if (board.hasPlayerWon(humansMark))
            System.out.println("You Won! I Lost!\n\n");
        else
            System.out.println("It's a Draw!\n\n");
    }

    /**
     * The playAgain method prompts the user if he/she
     * wants to play again and return a boolean value
     * @return		The true or false value if player
     * 				to play again.
     */
    public boolean playAgain() {
        System.out.printf("\nWould you like to play again! (Y/N):  ");
        input = keyboard.next().toUpperCase().trim();
        // validate input and retry if incorrect
        if (Pattern.matches("^(Y|YES)$", input)){
            return true; }
        else if (Pattern.matches("^(N|NO)$", input)) {
            return false; }
        System.out.printf("\n\tInvalid response entered! \n\tPlease enter " +
                "(\"Y\" for \"Yes\" or \"N\" for \"No\")!\n");
        playAgain();
        return false;
    }

    /**
     * The play method initiates game play and implements
     * a game loop to iterate through the game until the game
     * reaches a terminal state.  At which point the game will
     * prompt the user to start a new game or quit.
     * @throws IOException throws IOException
     */
    public void play() throws IOException {
        do {
            this.round = 0;
            this.board.clearBoard();	// Resets game board.
            this.board.getComputerMoves().clear();
            this.board.getLastMoves().clear();
            System.out.printf("\n\n**** WELCOME TO THE TIC-TAC-TOE GAME ****");
            System.out.printf("\n------ Author: Dellius A. Alexander -----");
            this.board.displayBoard();
            // Prints newline character
            System.out.println("\n");
            // Initiates the first move of the game
            this.firstMove();
            // Loop to continue game play after first move
            // has been made until game is over.
            while (!board.isGameOver()) {
                if (turn == USERS_TURN) {
                    round += 1;
                    this.humanMove();
                    turn = COMPUTERS_TURN;
                }
                else if (turn == COMPUTERS_TURN) {
                    round += 1;
                    board.setRound(round);
                    this.computerMove();
                    turn = USERS_TURN;
                }
            }

            this.winnerExist();
        }while (this.playAgain());
    }
    ///////////////////////////////////////////////////////////////////////////
     public static void main(String[] args) throws IOException
     {
         ///////////////////////////////////////////////////////////////////////
         TicTacToe t = new TicTacToe();
	 	 t.play();
         ///////////////////////////////////////////////////////////////////////
             // TicTacToe t = new TicTacToe();
             // Object[][] gBoard = new Object[3][3];
//             Object[][] gBoard = { {'X',' ',' ','X'},
//                                 {'X','X','X',' '},
//                                 {' ',' ','X',' '},
//                                 {'X',' ',' ','X'}};
//             Board bd = new Board(gBoard);
//             // // System.out.println("bd.getSub: "+bd.getSub(3, 3));
//             // // System.out.println("bd.getSub: "+bd.getCol(8));
//             // // System.out.println("bd.getSub: "+bd.getRow(8));
//             bd.displayBoard(gBoard);
//             System.out.println(bd.hasPlayerWon('X'));
         log.info("End of Game TicTacToe............");
         // System.out.println("End of test class...");
     }
}
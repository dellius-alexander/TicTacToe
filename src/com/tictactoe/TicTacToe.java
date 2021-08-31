package com.tictactoe;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
/**
 * This is a Tic Tac Toe game. It allows the user to play
 * against the computer. Two players take turns to place their
 * mark on a "3X3" grid of spaces with an X or 0. The player
 * to move first will be randomly chosen. The first player
 * will be assigned "X" and "0" will be assigned to the second
 * player. The player who succeeds first in placing three
 * marks in horizontal, vertical, or diagonal row wins the game.
 * The game board is designed from a 1 dimensional array.

 * @version 2.2
 * @since 2019-04-12
 */
public class TicTacToe {
    // The TicTacToe game board
    private char[][] gameBoard = new char[3][3];
    // A new instance of the game board
    private Board board;
    // Instantiate Minimax class algorithm
    private Minimax mini = new Minimax(board);
    // Instantiate the PointAndScore class
    PointAndScore print = new PointAndScore(board);
    // A new instance of the random class.
    private Random rand = new Random();
    // A constant that represents user
    private static final int USERS_TURN = 1;
    // A constant that represents computer
    private static final int COMPUTERS_TURN = 0;
    // Holds the symbol/mark computer will be assigned at the start of game
    private char computersMark;
    // Holds the symbol/mark user will be assigned at the start of game
    private char humansMark;
    // Holds the value of player turn
    private  int turn;
    // Holds the value of current round
    private  int round;
    private Scanner keyboard = new Scanner(System.in);
    // Holds the value from keyboard input
    private String input;

    public TicTacToe() {
        board = new Board(gameBoard);
    }

    /**
     * The getRow method accepts the value for the subscript
     * and returns the value of the row.
     * @param subscript	The subscript value.
     * @return		The value of the row.
     */
    public int getRow(int subscript) {
        return subscript / 3 + 1;
    }
    /**
     * The getCol method accepts the value of the subscript
     * and returns the value of the column.
     * @param subscript 	The subscript value.
     * @return		The value of the column.
     */
    public int getCol(int subscript) {
        return subscript % 3 + 1;
    }
    /**
     * The firstMove method will randomly decide who goes first.
     * This method will assign "X" to the first player and "0"
     * to the second player. The method initiates the first move
     * of the game.
     * @throws IOException
     * @throws SecurityException
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
        // Assign characters "X" to first player and "0" to second player
        if (firstMover == USERS_TURN) {
            computersMark = 'O';
            humansMark = 'X';
            board.setHumanMark(humansMark);
            board.setComputerMark(computersMark);
            System.out.println("You move first!\n");
        }
        else if (firstMover == COMPUTERS_TURN) {
            computersMark = 'X';
            humansMark = 'O';
            board.setHumanMark(humansMark);
            board.setComputerMark(computersMark);
            System.out.println("I will move first!\n");
        }

        if (firstMover == USERS_TURN) {
            round += 1;
            humanMove();
            turn = COMPUTERS_TURN;
        }
        else if (firstMover == COMPUTERS_TURN) {
            int sub;
            int test;
            round += 1;
            board.setRound(round);
            do{
                sub = rand.nextInt(8);
                test = sub % 2;
            } while (test == 1 || sub == 4);
            Point point = new Point(getRow(sub), getCol(sub));
            board.placeAMove(point, computersMark);
            System.out.println();
            System.out.print("I marked " + computersMark +
                    " at row (1-3): " + point.getRow());
            // Prints newline character
            System.out.println();
            System.out.print("\t    column(1-3): " + point.getCol());
            System.out.println();
            board.displayBoard();
            turn = USERS_TURN;
        }
    }
    /**
     * The playAgain method prompts the user if he/she
     * wants to play again and return a boolean value
     * @return		The true or false value if player
     * 				to play again.
     */
    public boolean playAgain() {
        System.out.print("Would you like to play again! (Y/N):  ");
        input = keyboard.next().toUpperCase();
        // Prints newline character
        System.out.println();
        while(!input.matches("[YN]")){
            System.out.println("Invalid response entered! \nPlease enter " +
                    "(\"Y\" for \"Yes\" or \"N\" for \"No\")!");
            // Prints newline character
            System.out.println();
            System.out.print("Would you like to play again! (Y/N):  ");
            input = keyboard.next().toUpperCase();
            // Prints newline character
            System.out.println();
        }
        // Prints newline character
        System.out.println();
        // Prints newline character
        System.out.println();
        if (input.equalsIgnoreCase("Y"))
            return true;
        else if (input.equalsIgnoreCase("N"))
            return false;
        return false;
    }
    /**
     * The humanMove method is used during user turn to interact
     * with the user during game play.  It collects the user
     * input and assigns the user move to the game board.
     * @throws IOException
     * @throws SecurityException
     */
    public void humanMove() throws SecurityException, IOException {
        Point point = new Point();
        boolean emptySubscript;
        int row;
        int col;
        String inputValidator = "[123]";
        do {
            do {
                System.out.print("YOUR TURN, enter " + humansMark +
                        " at \n \t     row(1-3):  ");
                input = keyboard.next();
            } while (!input.matches(inputValidator));
            row = Integer.parseInt(input);

            do {
                System.out.print("Now enter column(1-3): ");
                input = keyboard.next();
            } while (!input.matches(inputValidator));
            col = Integer.parseInt(input);
            // Finds subscript value from row and column
            point.getSub(row, col);
            point = new Point(point.getRow(),point.getCol());
            emptySubscript = (board.placeAMove(point, humansMark));
            if (!emptySubscript) {
                System.out.print("\nInvalid selection, ROW " + row +
                        " \nCOLUMN " + col +
                        " is FILLED, \nmake another selection!");
                System.out.println();
                board.displayBoard();
                System.out.println();
                System.out.println();
            }
        }while (!emptySubscript);
        board.placeAMove(point, humansMark);
        board.displayBoard();
    }
    /**
     * The computerMove method initiates the minimax algorithm
     * and plays the best possible move for win or draw.
     * @throws IOException
     */
    public void computerMove() throws IOException {
        mini.minimax(0, computersMark);
        print.printResults();
        System.out.println();
        System.out.println();
        System.out.print("MY TURN, I marked " + computersMark +
                " at \n\t    row (1-3): " +
                board.getComputerMove().getRow());
        // Prints newline character
        System.out.println();
        System.out.print("\t  column(1-3): " + board.getComputerMove().getCol());
        board.placeAMove(board.getComputerMove(), computersMark);
        System.out.println();
        System.out.println();
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
     * The play method initiates game play and implements
     * a game loop to iterate through the game until the game
     * reaches a terminal state.  At which point the game will
     * prompt the user to start a new game or quit.
     * @throws IOException
     */
    public void play() throws IOException {
        do {

            round = 0;
            board.clearBoard();			// Resets game board.

            System.out.println("**** WELCOME TO THE TIC-TAC-TOE GAME ****");
            System.out.println("------ Author: Dellius A. Alexander -----");
            board.displayBoard();
            // Prints newline character
            System.out.println("\n");
            // Initiates the first move of the game
            firstMove();
            // Loop to continue game play after first move
            // has been made until game is over.
            while (!board.isGameOver()) {
                if (turn == USERS_TURN) {
                    round += 1;
                    humanMove();
                    turn = COMPUTERS_TURN;
                }
                else if (turn == COMPUTERS_TURN) {
                    round += 1;
                    board.setRound(round);
                    computerMove();
                    turn = USERS_TURN;
                }
                if (board.isGameOver())
                    break;
            }
            // Determines winner or draw if game is over
            winnerExist();
        }while (playAgain());
    }
    /**
     * The main method starts/initiates the Tic Tac Toe game.
     * @throws IOException
     * @main 		The main method.
     */
    public static void main(String[] arg) throws IOException {
        
        TicTacToe playGame = new TicTacToe();
        playGame.play();
    }
}

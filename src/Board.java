
import java.util.ArrayList;
import java.util.List;
/**
 * The Board class represents the game board in a Tic Tac Toe game.  
 * This class also contains a AI method using the MiniMax algorithm, 
 * so the computer can determine the best possible move to secure a 
 * winning state or draw during game play.

 * @version 2.2
 * @since 2019-04-12
 */
public class Board {
    // Constant to represent a blank cell on the game board.
    private static final char NO_PLAYER = ' ';
    // Holds the value of the current computer mark (X/0).
    private char computerMark;
    // Holds the value of the current human mark (X/0).
    private char humanMark;
    // Represents the game board of char type.
    private char[] gameBoard;
    // Holds the value of "row and column" reference to computer move.
    private Point computerMove;
    // Holds the value of the current round at play
    private int round;
    // Holds the value of the score returned from a simulated
    // iteration of game play at current depth
    private int computerScore;
    private int humanScore;
    // The value of current depth
    private int depth;

    /**
     * The Board method is the Default constructor.
     */
    public Board() {
        // The empty method is the default
        // constructor used to instantiate Board class.
    }
    /**
     * The Board method is the Default constructor.
     */
    public Board(char[] gameBoard) {
        this.gameBoard = gameBoard;
    }
    /**
     * The getRound method returns the value of the
     * current round of game play
     * @return	The current round of game play
     */
    public int getRound() {
        return round;
    }
    /**
     * The setRound method accepts the value of the
     * current round of game play
     * @param round	The current round of game play
     */
    public void setRound(int round) {
        this.round = round;
    }
    /**
     * The getScore method returns the value of the
     * score evaluated at the end of each iteration
     * simulation
     * @return	The score assigned to each iteration
     */
    public int getComputerScore() {
        return computerScore;
    }
    /**
     * The setScore method assigns the score evaluated
     * at the end of each iteration simulation
     * @param score
     */
    public void setComputerScore(int score) {
        this.computerScore = score;
    }
    /**
     * The getScore method returns the value of the
     * score evaluated at the end of each iteration
     * simulation
     * @return	The score assigned to each iteration
     */
    public int gethumanScore() {
        return humanScore;
    }
    /**
     * The setScore method assigns the score evaluated
     * at the end of each iteration simulation
     * @param score
     */
    public void sethumanScore(int score) {
        this.humanScore = score;
    }
    /**
     * The setDepth method assigns the value of current
     * depth of game play to field currentDepth
     * @param depth	The current depth at play
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    /**
     * The getDepth method returns the value of the
     * current depth being explored or at play
     * @return
     */
    public int getDepth() {
        return depth;
    }

    public char[] getGameBoard() {
        return gameBoard;
    }
    public void setGameBoard(char[] gameBoard) {
        this.gameBoard = gameBoard;
    }
    public char getNoPlayer() {
        return NO_PLAYER;
    }
    /**
     * The setComputerMark method initializes the computer mark.
     * @param playerMark	The player mark.
     */
    public void setComputerMark(char playerMark) {
        this.computerMark = playerMark;
    }
    /**
     * The setHumanMark method initializes the human mark.
     * @param playerMark	The player mark.
     */
    public void setHumanMark(char playerMark) {
        this.humanMark = playerMark;
    }
    /**
     * The getComputerMark method retrieves the computers mark.
     * @return	The computer mark on the board during game play.
     */
    public char getComputerMark() {
        return computerMark;
    }
    /**
     * The getHumanMark method retrieves the human mark.
     * @return	The human mark on the board during game play.
     */
    public char getHumanMark() {
        return humanMark;
    }
    /**
     * The getSub method accepts the value for the row
     * and column, and returns the subscript value of the cell.
     * @param r		The value of the row.
     * @param c		The value of the column.
     * @return		The index value of the cell on the board.
     */
    public int getSub(int r, int c)	{
        return (r - 1) * 3 + c - 1;
    }
    /**
     * The getRow method accepts the value for the subscript
     * and returns the value of the row.
     * @param sub	The subscript value.
     * @return		The value of the row.
     */
    public int getRow(int sub) {
        return sub / 3 + 1;
    }
    /**
     * The getCol method accepts the value of the subscript
     * and returns the value of the column.
     * @param sub 	The subscript value.
     * @return		The value of the column.
     */
    public int getCol(int sub) {
        return sub % 3 + 1;
    }
    /**
     * The getComputerMove returns the row and column
     * value of the subscript value for the computer
     * move when called.
     * @return	The object representing row and column
     */
    public Point getComputerMove() {
        return computerMove;
    }

    /**
     * The setComputerMove method accepts a point object
     * of row and column and assigns the value to
     * computerMove.
     * @param compMove	The object representing row and column
     */
    public void setComputerMove(Point compMove) {
        this.computerMove = compMove;
    }
    /**
     * The clearBoard method wipes the game board of all moves
     * and resets the game board to its original state.
     */
    public void clearBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            gameBoard[i] = NO_PLAYER;
        }
    }

    /**
     * The clearBoard method wipes the game board of all moves
     * and resets the game board to its original state.
     * @param gameBoard  The game board
     */
    public void clearBoard(char[] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            gameBoard[i] = NO_PLAYER;
        }
    }
    /**
     * The isGameOver method checks the game board for a winner
     * and returns true or false.
     * @return		The true or false if winner exist or not.
     */
    public boolean isGameOver() {
        return hasPlayerWon(computerMark) || hasPlayerWon(humanMark)
                || getAvailableCells().isEmpty();
    }
    /**
     * The hasPlayerWon method accepts a player mark and returns
     * a true or false value if winner exist or not.
     * @param player	The player mark.
     * @return			The true or false if winner exist or not.
     */
    public boolean hasPlayerWon(char player) {
        // Horizontal row check
        return ((gameBoard[0] == gameBoard[1] && gameBoard[1] == gameBoard[2] && gameBoard[2] == player) ||
                (gameBoard[3] == gameBoard[4] && gameBoard[4] == gameBoard[5] && gameBoard[5] == player) ||
                (gameBoard[6] == gameBoard[7] && gameBoard[7] == gameBoard[8] && gameBoard[8] == player) ||
                (gameBoard[0] == gameBoard[3] && gameBoard[3] == gameBoard[6] && gameBoard[6] == player) ||
                (gameBoard[1] == gameBoard[4] && gameBoard[4] == gameBoard[7] && gameBoard[7] == player) ||
                (gameBoard[2] == gameBoard[5] && gameBoard[5] == gameBoard[8] && gameBoard[8] == player) ||
                (gameBoard[0] == gameBoard[4] && gameBoard[4] == gameBoard[8] && gameBoard[8] == player) ||
                (gameBoard[2] == gameBoard[4] && gameBoard[4] == gameBoard[6] && gameBoard[6] == player));
    }
    /**
     * The getAvailableCells method retrieves and returns a
     * list available empty cells on the game board.
     * @return	The list of available cells
     */
    public List<Point> getAvailableCells() {
        List<Point> availableCells = new ArrayList<>();

        for (int r = 1; r <= 3; r++) {
            for (int c = 1; c <= 3; c++) {

                if (gameBoard[getSub(r,c)] == NO_PLAYER)
                    availableCells.add(new Point(r,c));
            }
        }
        return availableCells;
    }
    /**
     * The placeAMove method accepts two points as Point
     * and the player mark, to place a move in the cell referenced.
     * It places the move and returns true or if cell is occupied,
     * it returns false.
     * @param point		The two points representing row and column
     * @param player	The player mark
     * @return			The true or false value if cell is occupied.
     */
    public boolean placeAMove(Point point, char player) {
        if (gameBoard[getSub(point.getRow(), point.getCol())] != NO_PLAYER)
            return false;

        gameBoard[getSub(point.getRow(), point.getCol())] = player;
        return true;
    }
    /**
     * The displayBoard prints the game board to the display.
     */
    public void displayBoard() {
        System.out.println("\n");
        for (int i = 0; i<gameBoard.length; i++)
        {
            System.out.print(gameBoard[i]);
            if (((i+1) % 3) == 0)
            {
                System.out.println("");
                if (i < 6)
                    System.out.println("-+-+-");
            }
            else
                System.out.print("|");
        }
        System.out.println("\n");
    }
}
 
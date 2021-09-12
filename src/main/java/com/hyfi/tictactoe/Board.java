package com.hyfi.tictactoe;
// Imports
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;


/**
 * The Board class represents the game board in a Tic Tac Toe game. This class also contains a AI method using the MiniMax algorithm, so the computer can determine the best possible move to secure a winning state or draw during game play.
 * @version 2.2
 * @since 2019-04-12
 */
public class Board {
    private static final Logger logger = LoggerFactory.getLogger(Board.class);
    
    // Constant to represent a blank cell on the game board.
    private static final char NO_PLAYER = ' ';
    // Holds the value of the current computer mark (X/0).
    private char computerMark;
    // Holds the value of the current human mark (X/0).
    private char humanMark;
    // Represents the game board of char type.
    private char[][] gameBoard;
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
    // holds the size of the board
    private int boardSize;
    // holds the last move played
    private List<Point> lastMove = new ArrayList<Point>();

    /**
     * The Board method is the Default constructor.
     */
    public Board() {
        logger.debug("Default constructor...");
    }
    /**
     * Constructor accepts game board
     * @param gameBoard the game board passed to a new instance of Board
     */
    public Board(char[][] gameBoard){

        this.gameBoard = setupGameBrd(gameBoard);
        this.boardSize = this.gameBoard.length;
        logger.debug("Parameterized constructor...");
    }
    /**
     * Adds the last move played to a list of previous moves
     * @param point the point played last
     */
    public void setLastMove(Point point)
    {
        this.lastMove.add(point);
    }
    /**
     * Gets a list of privious played moves
     * @return the list of last moves
     */
    public List<Point> getLastMove()
    {
        return this.lastMove;
    }
    /**
     * Sets the board size
     */
    public void setBoardSize(){
        this.boardSize = this.gameBoard.length;
    }
    /**
     * Sets the board size
     * @param size the board size
     */
    public void setBoardSize(int size){
        this.boardSize = size;
    }
    /**
     * Sets the board size
     * @param gameBoard the game board
     */
    public void setBoardSize(char[][] gameBoard){
        this.boardSize = gameBoard.length;
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
     * @param score the value returnd from a win/lose/draw
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
     * @param score the value representing win/loose/draw of game play
     */
    public void sethumanScore(int score) {
        this.humanScore = score;
    }
    /**
     * The setDepth method assigns the value of current
     * lengthe of the available cells remaining to be played
     * @param depth	The current depth at play
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    /**
     * The getDepth method returns the value of the
     * current depth being explored or at play
     * @return the current length of the available cells to be played
     */
    public int getDepth() {
        return depth;
    }
    /**
     * Gets the game board
     * @return the game board
     */
    public char[][] getGameBoard() {
        return gameBoard;
    }
    /**
     * Sets the game board
     * @param gameBoard the game board
     */
    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
    /**
     * Gets the the NULL player token
     * @return the NULL player token
     */
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
        return (r - 1) * this.boardSize + (c - 1);
    }
    /**
     * The getRow method accepts the value for the subscript
     * and returns the value of the row.
     * @param sub	The subscript value.
     * @return		The value of the row.
     */
    public int getRow(int sub) {
        return (sub / this.boardSize) + 1;
    }
    /**
     * The getCol method accepts the value of the subscript
     * and returns the value of the column.
     * @param sub 	The subscript value.
     * @return		The value of the column.
     */
    public int getCol(int sub) {
        return (sub % this.boardSize) + 1;
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
     * @return false if compMove NULL, true if not NULL
     */
    public boolean setComputerMove(Point compMove) {
        if (compMove != null) {
            this.computerMove = compMove;
            return true;
        } else
			try {
				throw new Exception("Computer move object NULL...");
			} catch (Exception e) {
				logger.error("Unable to place a move...\n"+e.getMessage());
			}
            
        return false; 
    }
    /**
     * The clearBoard method wipes the game board of all moves
     * and resets the game board to its original state.
     */
    public void clearBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++){
                this.gameBoard[i][j] = NO_PLAYER;
            }  
        }
    }

    /**
     * The clearBoard method wipes the game board of all moves
     * and resets the game board to its original state.
     * @param gameBoard  The game board
     */
    public void clearBoard(char[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++){
                this.gameBoard[i][j] = NO_PLAYER;
            }  
        }
    }
    /**
     * The isGameOver method checks the game board for a winner
     * and returns true or false.
     * @return		The true or false if winner exist or not.
     */
    public boolean isGameOver() {
        
        boolean over = hasPlayerWon(computerMark) || hasPlayerWon(humanMark)
                || getAvailableCells().isEmpty();
                logger.debug("Game is Over: "+over);
                return over;
    }
    // /**
    //  * The hasPlayerWon method accepts a player mark and returns
    //  * a true or false value if winner exist or not.
    //  *    0     1     2
    //  * ___________________
    //  * |__o__|__x__|__o__|  <=  0  
    //  * |__x__|__x__|__x__|  <=  1 [   |ROWS|   ]
    //  * |__o__|__o__|__x__|  <=  2
    //  * ^___[ COLUMNS ]___^
    //  * 
    //  * Their is a pattern that opens up from the few sequences below. 
    //  * Can we find an equation to abstract each sequence. Yes...
    //  * 
    //  * Horizontal sequence = [0][0],[0][1],[0][2]
    //  * Vertical sequence = [0][0],[1][0],[2][0] 
    //  * Diagonal sequence A = [0][0],[1][1],[2][2]
    //  * Diagonal sequence B = [2][0],[1][1],[0][2]
    //  * Sequences:
    //  *  0,1,2 : a_n = n − 1
    //  *  1,2,3 : a_n = n
    //  *  2,3,4 : a_n = n + 1
    //  *  0,4,8 : a_n = 4n - 4
    //  *  6,4,2 : a_n = 2n + 8
    //  * 
    //  * @param player	The player mark.
    //  * @return			The true or false if winner exist or not.
    //  */
    // public boolean hasPlayerWon(char player) {
    //     // Horizontal/Vertical/Diagonal row check
    //     boolean hasWon = ((gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][1] == gameBoard[0][2] && gameBoard[0][2] == player) ||
    //                     (gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[1][2] && gameBoard[1][2] == player) ||
    //                     (gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[2][2] && gameBoard[2][2] == player) ||
    //                     (gameBoard[0][0] == gameBoard[1][0] && gameBoard[1][0] == gameBoard[2][0] && gameBoard[2][0] == player) ||
    //                     (gameBoard[0][1] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][1] && gameBoard[2][1] == player) ||
    //                     (gameBoard[0][2] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][2] && gameBoard[2][2] == player) ||
    //                     (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[2][2] == player) ||
    //                     (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0] && gameBoard[2][0] == player));
    //     // logger.debug("Has Player Won: "+hasWon);
    //     return hasWon;
    // }
    /**
     * The getAvailableCells method retrieves and returns a
     * list available empty cells on the game board.
     * @return	The list of available cells
     */
    public List<Point> getAvailableCells(){
        List<Point> availableCells = new ArrayList<>();

        for (int r = 1; r <= gameBoard.length; r++) {
            for (int c = 1; c <= gameBoard.length; c++) {
                // logger.debug("row: "+r+" | Column: "+c+ " | getSub: "+getSub(r, c));
                if (gameBoard[r-1][c-1] == NO_PLAYER)
                    availableCells.add(new Point(r,c));
            }
        }
        logger.debug("Available Cells: "+Arrays.deepToString(availableCells.toArray()));
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
        // System.out.println("Row: "+point.getRow());
        // System.out.println("Col: "+point.getCol());
        // logger.debug("Point: "+point.toString()+" | Row: "+point.getRow()+" | Column: "+point.getCol());
        if (gameBoard[point.getRow()][point.getCol()] != NO_PLAYER){
            logger.debug("Unable to Place A Move at => Row: "+point.getRow()+" | Column:"+point.getCol());
            return false;
        }
        gameBoard[point.getRow()][point.getCol()] = player;    
        logger.debug("Placed A Move at => Row: "+point.getRow()+" | Column:"+point.getCol());    
        return true;
    }
    /**
     * The displayBoard prints the game board to the display.
     * 
     */
    public void displayBoard() {
        System.out.println("\n");
        for (int i = 0; i<gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard.length; j++)
            {      
                System.out.print(gameBoard[i][j]);
                if (j == gameBoard.length-1) {
                    System.out.print("\t\t<= ROW "+(i+1));
                }
                // // logger.debug("Row "+ j +" I think: "+(j+1) % gameBoard.length);
                if (((j+1) % gameBoard.length) == 0)
                {
                    if (j < (gameBoard.length)){
                        System.out.println(" ");
                    }
                    if (i < gameBoard.length-1){
                        for (int j2 = 0; j2 < Math.ceil(gameBoard.length-1); j2++) {
                            System.out.print("-+");
                        }
                    }
                    if (i < (gameBoard.length-1)){
                        System.out.println("-");
                    }
                }
                else
                {
                    System.out.print("|");
                }
            }
        }    
        System.out.println("\n");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print(""+(i+1)+" ");
        }
        System.out.println("\n\n⇑-⇑-⇑-COLUMNS");
        System.out.println("\n\n\n");
        logger.debug("Printed Game Board...");
    }
    /**
     * The displayBoard prints the game board to the display.
     * @param gameBoard the game board
     */
    public void displayBoard(char[][] gameBoard) {
        System.out.println("\n");
        for (int i = 0; i<gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard.length; j++)
            {      
                System.out.print(gameBoard[i][j]);
                if (j == gameBoard.length-1) {
                    System.out.print("\t\t<= ROW "+(i+1));
                }
                // // logger.debug("Row "+ j +" I think: "+(j+1) % gameBoard.length);
                if (((j+1) % gameBoard.length) == 0)
                {
                    if (j < (gameBoard.length)){
                        System.out.println(" ");
                    }
                    if (i < gameBoard.length-1){
                        for (int j2 = 0; j2 < Math.ceil(gameBoard.length-1); j2++) {
                            System.out.print("-+");
                        }
                    }
                    if (i < (gameBoard.length-1)){
                        System.out.println("-");
                    }
                }
                else
                {
                    System.out.print("|");
                }
            }
        }    
        System.out.println("\n");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print(""+(i+1)+" ");
        }
        System.out.println("\n\n⇑-⇑-⇑-COLUMNS");
        System.out.println("\n\n\n");
        logger.debug("Printed Game Board...");
    }
    /**
     * Set up the game board with initial whitespace character.
     * @param gBoard the game board
     * @return the setup game board
     */
    public char[][] setupGameBrd(char[][] gBoard){
        // int label = 0;
        for (int i = 0; i < gBoard.length; i++) {
            for (int j = 0; j < gBoard.length; j++) {
                // System.out.println("Sub:" +s);
                gBoard[i][j] = ' ';
                // logger.debug("Sub: |" +gBoard[i][j]+ "|");
            }
        }
        logger.debug("Finished setting up game...");
        return gBoard;
    }
    // /**
    //  * Get the current line number of executing thread
    //  * @return the integer value line number of executing thread
    //  */
    // public static int getLineNumber() {
    //     return Thread.currentThread().getStackTrace()[2].getLineNumber();
    // }
    /**
     * Dynamically checks for a winning pattern based on the board size
     * @param player the player mark
     * @return true if player won, false if no win
     */
    public boolean hasPlayerWon(char player)
    {
        boolean won = false;

        char[][] gBoard = getGameBoard();
        int numberOfColumns = gBoard.length;
        // check the rows
        BooleanSupplier horizontalCheck = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            int validityCounter; 
            // now check each set of rows
            for (int i = 0; i < numberOfColumns; i++) 
            {   // reset counter after every row check
                validityCounter= 0; 
                // System.out.println("Valid cntr: "+validityCounter);
                // get the first value of each set
                // char firstValue = val.get(i)[0];
                for (int j = 0; j < numberOfColumns; j++)
                {
                    if (player == gBoard[i][j])
                    {
                        validityCounter++;
                        // System.out.println("Valid cntr: "+validityCounter);
                        if (validityCounter == numberOfColumns) 
                            return isWinner = true;
                    }
                    // else break;
                }
            }
            return isWinner;            
        };
        // check the columns
        BooleanSupplier verticalCheck = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            int validityCounter; 
            // get all the columns for checking
            for (int i = 0; i < numberOfColumns; i++)
            {   // reset counter after every row check
                validityCounter= 0; 
                // System.out.println("Valid cntr: "+validityCounter);
                for (int j = 0; j < numberOfColumns; j++) 
                {
                    if (player == gBoard[j][i])
                    {
                        validityCounter++;
                        // System.out.println("Valid cntr: "+validityCounter);
                        if (validityCounter == numberOfColumns) 
                            return isWinner = true;
                    }
                    // else break;
                }
            }
            return isWinner;
        };
        // check the diagonal from top left
        BooleanSupplier diagonalCheckFromTopLeft = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            // reset counter after every row check
            int validityCounter = 0; 
            /// check diagonal from top left
            for (int i = 0; i < numberOfColumns; i++)
            {   
                // System.out.println("Valid cntr: "+validityCounter);
                // System.out.println("Row calc: "+i+" | Column: "+i);
                if (player == gBoard[i][i])
                {
                    validityCounter++;
                    // System.out.println("Valid cntr: "+validityCounter);
                    if (validityCounter == numberOfColumns) 
                        return isWinner = true;
                }
                // else break;
            }
            return isWinner;
        };
        // check the diagonal from bottom left
        BooleanSupplier diagonalCheckFromBottomLeft = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            // reset counter after every row check
            int validityCounter = 0; 
            // check diagonal from bottom lift
            for (int i = 0; i < numberOfColumns; i++) 
            { // Count down function: a_n = -i + numberOfColumns
                // System.out.println("Valid cntr: "+validityCounter);
                // System.out.println("Row calc: "+(numberOfColumns - (i+1))+" | Column: "+i);
                if (player == gBoard[(numberOfColumns - (i+1))][i])
                {
                    validityCounter++;
                    // System.out.println("Valid cntr: "+validityCounter);
                    if (validityCounter == numberOfColumns) 
                        return isWinner = true;
                }
                // else break;
            }
            return isWinner;
        };
        

        // List<Point> lMove = new ArrayList<Point>(getLastMove());
        // Function<Character,Integer> lM = (playerMark) -> {
        //     int cnt = 0;
        //     for (Point point : lMove) {
        //         if (gameBoard[point.getRow()][point.getCol()] == (playerMark)){
        //             logger.debug("Player mark found: " + gameBoard[point.getRow()][point.getCol()] + " @ Point: "+ point.toString());
        //             cnt++;
        //         }
        //     }
        //     return cnt;
        // };
       
        // // exit if number of moves less than quantity to constitute a win
        // if(lMove.size() < gameBoard.length)
        // {
        //     return false;
        // } // exit if number of player mark is less than quantity to constitute a win
        // else if (lM.apply(player) < gameBoard.length)
        // {
        //     return false;
        // }
        // won = verticalCheck.getAsBoolean();
        // won = diagonalCheckFromTopLeft.getAsBoolean();
        // won = diagonalCheckFromBottomLeft.getAsBoolean();
        // won = horizontalCheck.getAsBoolean();
        won =   verticalCheck.getAsBoolean() == true ? true : 
                horizontalCheck.getAsBoolean() == true ? true :
                diagonalCheckFromTopLeft.getAsBoolean() == true ? true : 
                diagonalCheckFromBottomLeft.getAsBoolean() == true ? true : false;
                logger.debug("Player "+player+" has won: "+won);
        return won;
    }
    /**
     * Dynamically checks for a winning pattern based on the board size
     * @param player the player mark
     * @param gBoard the game board
     * @return true if player won, false if no win
     */
    public boolean hasPlayerWon(char player, char[][] gBoard)
    {
        boolean won = false;

        int numberOfColumns = gBoard.length;
        // lambda to check the rows
        BooleanSupplier horizontalCheck = () -> {
            boolean isWinner = false;
            List<char[]> val = new ArrayList<char[]>();
            // counts how many time a match is found
            int validityCounter; 
            // get all the rows for checking
            for (int i = 0; i < numberOfColumns; i++)
            val.add(gBoard[i]);
            // now check each set of rows
            for (int i = 0; i < numberOfColumns; i++) 
            {   // reset counter after every row check
                validityCounter= 0; 
                // System.out.println("Valid cntr: "+validityCounter);
                // get the first value of each set
                // char firstValue = val.get(i)[0];
                for (int j = 0; j < numberOfColumns; j++) 
                {
                    if (player == val.get(i)[j])
                    {
                        validityCounter++;
                        // System.out.println("Valid cntr: "+validityCounter);
                        if (validityCounter == numberOfColumns) 
                            return isWinner = true;
                    }
                    // else break;
                }
            }
            return isWinner;            
        };
        // lambda to check the columns
        BooleanSupplier verticalCheck = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            int validityCounter; 
            // get all the columns for checking
            for (int i = 0; i < numberOfColumns; i++)
            {   // reset counter after every row check
                validityCounter= 0; 
                // System.out.println("Valid cntr: "+validityCounter);
                for (int j = 0; j < numberOfColumns; j++) 
                {
                    if (player == gBoard[j][i])
                    {
                        validityCounter++;
                        // System.out.println("Valid cntr: "+validityCounter);
                        if (validityCounter == numberOfColumns) 
                            return isWinner = true;
                    }
                    // else break;
                }
            }
            return isWinner;
        };
        // lambda to check the diagonal from top left
        BooleanSupplier diagonalCheckFromTopLeft = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            // reset counter after every row check
            int validityCounter = 0; 
            /// check diagonal from top left
            for (int i = 0; i < numberOfColumns; i++)
            {   
                // System.out.println("Valid cntr: "+validityCounter);
                // System.out.println("Row calc: "+i+" | Column: "+i);
                if (player == gBoard[i][i])
                {
                    validityCounter++;
                    // System.out.println("Valid cntr: "+validityCounter);
                    if (validityCounter == numberOfColumns) 
                        return isWinner = true;
                }
                // else break;
            }
            return isWinner;
        };
        // lambda to check the diagonal from bottom left
        BooleanSupplier diagonalCheckFromBottomLeft = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            // reset counter after every row check
            int validityCounter = 0; 
            // check diagonal from bottom lift
            for (int i = 0; i < numberOfColumns; i++) 
            { // Count down function: a_n = -i + numberOfColumns
                // System.out.println("Valid cntr: "+validityCounter);
                // System.out.println("Row calc: "+(numberOfColumns - (i+1))+" | Column: "+i);
                if (player == gBoard[(numberOfColumns - (i+1))][i])
                {
                    validityCounter++;
                    // System.out.println("Valid cntr: "+validityCounter);
                    if (validityCounter == numberOfColumns) 
                        return isWinner = true;
                }
                // else break;
            }
            return isWinner;
        };

        // List<Point> lMove = new ArrayList<Point>(getLastMove());
        // Function<Character,Integer> lM = (playerMark) -> {
        //     int cnt = 0;
        //     for (Point point : lMove) {
        //         if (gameBoard[point.getRow()][point.getCol()] == (playerMark)){
        //             logger.debug("Player mark found: " + gameBoard[point.getRow()][point.getCol()] + " @ Point: "+ point.toString());
        //             cnt++;
        //         }
        //     }
        //     return cnt;
        // };
       
        // // exit if number of moves less than quantity to constitute a win
        // if(lMove.size() < gameBoard.length)
        // {
        //     return false;
        // } // exit if number of player mark is less than quantity to constitute a win
        // else if (lM.apply(player) < gameBoard.length)
        // {
        //     return false;
        // }
        // won = verticalCheck.getAsBoolean();
        // won = diagonalCheckFromTopLeft.getAsBoolean();
        // won = diagonalCheckFromBottomLeft.getAsBoolean();
        // won = horizontalCheck.getAsBoolean();
        won =   verticalCheck.getAsBoolean() == true ? true : 
                horizontalCheck.getAsBoolean() == true ? true :
                diagonalCheckFromTopLeft.getAsBoolean() == true ? true : 
                diagonalCheckFromBottomLeft.getAsBoolean() == true ? true : false;
                logger.debug("Player "+player+" has won: "+won);
        return won;
    }
    /**
     * Dynamically checks for a winning pattern based on the board size.
     * @param player the player mark
     * @param gBoard the game board
     * @param number_Of_Win_Pattern_To_Check_In_A_Row define the size of the win pattern, i.e. 4x4, 5x5
     * @return true if player won, false if no win
     */
    public boolean hasPlayerWon(char player, char[][] gBoard, int number_Of_Win_Pattern_To_Check_In_A_Row)
    {
        boolean won = false;
        
        int numberOfColumns = number_Of_Win_Pattern_To_Check_In_A_Row;
        // lambda to check the rows
        BooleanSupplier horizontalCheck = () -> {
            boolean isWinner = false;
            List<char[]> val = new ArrayList<char[]>();
            // counts how many time a match is found
            int validityCounter; 
            // get all the rows for checking
            for (int i = 0; i < numberOfColumns; i++)
            val.add(gBoard[i]);
            // now check each set of rows
            for (int i = 0; i < numberOfColumns; i++) 
            {   // reset counter after every row check
                validityCounter= 0; 
                // System.out.println("Valid cntr: "+validityCounter);
                // get the first value of each set
                // char firstValue = val.get(i)[0];
                for (int j = 0; j < numberOfColumns; j++) 
                {
                    if (player == val.get(i)[j])
                    {
                        validityCounter++;
                        // System.out.println("Valid cntr: "+validityCounter);
                        if (validityCounter == numberOfColumns) 
                            return isWinner = true;
                    }
                    // else break;
                }
            }
            return isWinner;            
        };
        // lambda to check the columns
        BooleanSupplier verticalCheck = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            int validityCounter; 
            // get all the columns for checking
            for (int i = 0; i < numberOfColumns; i++)
            {   // reset counter after every row check
                validityCounter= 0; 
                // System.out.println("Valid cntr: "+validityCounter);
                for (int j = 0; j < numberOfColumns; j++) 
                {
                    if (player == gBoard[j][i])
                    {
                        validityCounter++;
                        // System.out.println("Valid cntr: "+validityCounter);
                        if (validityCounter == numberOfColumns) 
                            return isWinner = true;
                    }
                    // else break;
                }
            }
            return isWinner;
        };
        // lambda to check the diagonal from top left
        BooleanSupplier diagonalCheckFromTopLeft = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            // reset counter after every row check
            int validityCounter = 0; 
            /// check diagonal from top left
            for (int i = 0; i < numberOfColumns; i++)
            {   
                // System.out.println("Valid cntr: "+validityCounter);
                // System.out.println("Row calc: "+i+" | Column: "+i);
                if (player == gBoard[i][i])
                {
                    validityCounter++;
                    // System.out.println("Valid cntr: "+validityCounter);
                    if (validityCounter == numberOfColumns) 
                        return isWinner = true;
                }
                // else break;
            }
            return isWinner;
        };
        // lambda to check the diagonal from bottom left
        BooleanSupplier diagonalCheckFromBottomLeft = () -> {
            boolean isWinner = false;
            // counts how many time a match is found
            // reset counter after every row check
            int validityCounter = 0; 
            // check diagonal from bottom lift
            for (int i = 0; i < numberOfColumns; i++) 
            { // Count down function: a_n = -i + numberOfColumns
                // System.out.println("Valid cntr: "+validityCounter);
                // System.out.println("Row calc: "+(numberOfColumns - (i+1))+" | Column: "+i);
                if (player == gBoard[(numberOfColumns - (i+1))][i])
                {
                    validityCounter++;
                    // System.out.println("Valid cntr: "+validityCounter);
                    if (validityCounter == numberOfColumns) 
                        return isWinner = true;
                }
                // else break;
            }
            return isWinner;
        };
        // won = verticalCheck.getAsBoolean();
        // won = diagonalCheckFromTopLeft.getAsBoolean();
        // won = diagonalCheckFromBottomLeft.getAsBoolean();
        // won = horizontalCheck.getAsBoolean();
        won =   verticalCheck.getAsBoolean() == true ? true : 
                horizontalCheck.getAsBoolean() == true ? true :
                diagonalCheckFromTopLeft.getAsBoolean() == true ? true : 
                diagonalCheckFromBottomLeft.getAsBoolean() == true ? true : false;
        logger.debug("Player "+player+" has won: "+won);
        return won;
    }
    // public static void main(String[] arg) 
    // {   
    //     ///////////////////////////////////////////////////////////////////////
    //     // TicTacToe t = new TicTacToe();
	// 	// t.play();
    //     ///////////////////////////////////////////////////////////////////////
    //         // TicTacToe t = new TicTacToe();            
    //         // char[][] gBoard = new char[3][3]; 
    //         char[][] gBoard = { {'X',' ',' ','X'},
    //                             {'X','X','X',' '},
    //                             {' ',' ','X',' '},
    //                             {'X',' ',' ',' '}};
    //         Board bd = new Board(gBoard);
    //         // // System.out.println("bd.getSub: "+bd.getSub(3, 3));
    //         // // System.out.println("bd.getSub: "+bd.getCol(8));
    //         // // System.out.println("bd.getSub: "+bd.getRow(8));
    //         bd.displayBoard(gBoard);
    //         System.out.println(bd.hasPlayerWon('X'));
    //     logger.debug("End of Game TicTacToe............");
    //     // System.out.println("End of test class...");
    // }

/////////////////////   END OF CLASS Board  /////////////////////////
}
 
package com.tictactoe;

// import java.util.logging.*;
// import java.io.IOException;
import java.util.ArrayList;
// import java.util.Arrays;
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
    // // Setting up logging to the console and file
    // private static Logger logger = Logger.getLogger("com.tictactoe.Board");
    // private static String logFile = "tictactoe-"+java.time.LocalDate.now()+".%u.%g.log";
    // //Creating consoleHandler and fileHandler
    // // private static ConsoleHandler cH;
    // private static Handler fH;    
    // private static Level logLevel = Level.WARNING;
    
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

    /**
     * The Board method is the Default constructor.
     */
    public Board() {        
        // try {
        //     // Add file handler
        //     // // fH = new FileHandler(logFile,true);
        //     // Add consule handler
        //     // // fH.setFormatter(new SimpleFormatter());
        //     // Add both handlers to the logger
        //      // Send logger output to our FileHandler.
        //     // logger.addHandler(fH);
        //      // Send logger output to our consuleHandler.
        //     // logger.addHandler(new ConsoleHandler());
        //     // Request that every detail gets logged.
        //     // logger.setLevel(logLevel);
            
        // } catch (SecurityException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }          
        // Log a simple INFO message.
        // logger.info("LINE: ["+getLineNumber()+"] | Constructor init...");
        // this.gameBoard = setupGameBrd(this.gameBoard);
    }
    public Board(char[][] gameBoard){
        // try{
        //     // Add file handler
        //     // fH = new FileHandler(logFile,true);
        //     // Add consule handler
        //     // fH.setFormatter(new SimpleFormatter());
        //     // Add both handlers to the logger
        //      // Send logger output to our FileHandler.
        //     // logger.addHandler(fH);
        //      // Send logger output to our consuleHandler.
        //     // logger.addHandler(new ConsoleHandler());
        //     // Request that every detail gets logged.
        //     // logger.setLevel(logLevel);
            
        // } catch (SecurityException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }    
        // Log a simple INFO message.
        // logger.info("LINE: ["+getLineNumber()+"] | Constructor init...");
        // assign game board
        this.gameBoard = setupGameBrd(gameBoard);
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

    public char[][] getGameBoard() {
        return gameBoard;
    }
    public void setGameBoard(char[][] gameBoard) {
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
        return (r - 1) * 3 + (c - 1);
    }
    /**
     * The getRow method accepts the value for the subscript
     * and returns the value of the row.
     * @param sub	The subscript value.
     * @return		The value of the row.
     */
    public int getRow(int sub) {
        return (sub / 3) + 1;
    }
    /**
     * The getCol method accepts the value of the subscript
     * and returns the value of the column.
     * @param sub 	The subscript value.
     * @return		The value of the column.
     */
    public int getCol(int sub) {
        return (sub % 3) + 1;
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
    public boolean setComputerMove(Point compMove) {
        if (compMove != null) {
            this.computerMove = compMove;
        } else
			try {
				throw new Exception(" ");
			} catch (Exception e) {
				// logger.warning("LINE: ["+getLineNumber()+"] | Unable to place a move...\n"+e.getMessage());
			}
            
        return true; 
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
                // logger.info("LINE: ["+getLineNumber()+"] | Game is Over: "+over);
                return over;
    }
    /**
     * The hasPlayerWon method accepts a player mark and returns
     * a true or false value if winner exist or not.
     *    0     1     2
     * ___________________
     * |__o__|__x__|__o__|  <=  0  
     * |__x__|__x__|__x__|  <=  1 [   |ROWS|   ]
     * |__o__|__o__|__x__|  <=  2
     * ^___[ COLUMNS ]___^
     * 
     * Their is a pattern that opens up from the few sequences below. 
     * Can we find an equation to abstract each sequence. Yes...
     * 
     * Horizontal sequence = [0][0],[0][1],[0][2]
     * Vertical sequence = [0][0],[1][0],[2][0] 
     * Diagonal sequence A = [0][0],[1][1],[2][2]
     * Diagonal sequence B = [2][0],[1][1],[0][2]
     * Sequences:
     *  0,1,2 : a_n = n − 1
     *  1,2,3 : a_n = n
     *  2,3,4 : a_n = n + 1
     *  0,4,8 : a_n = 4n - 4
     *  6,4,2 : a_n = 2n + 8
     * 
     * @param player	The player mark.
     * @return			The true or false if winner exist or not.
     */
    public boolean hasPlayerWon(char player) {
        // Horizontal/Vertical/Diagonal row check
        boolean hasWon = ((gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][1] == gameBoard[0][2] && gameBoard[0][2] == player) ||
                        (gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[1][2] && gameBoard[1][2] == player) ||
                        (gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][1] == gameBoard[2][2] && gameBoard[2][2] == player) ||
                        (gameBoard[0][0] == gameBoard[1][0] && gameBoard[1][0] == gameBoard[2][0] && gameBoard[2][0] == player) ||
                        (gameBoard[0][1] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][1] && gameBoard[2][1] == player) ||
                        (gameBoard[0][2] == gameBoard[1][2] && gameBoard[1][2] == gameBoard[2][2] && gameBoard[2][2] == player) ||
                        (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[2][2] == player) ||
                        (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0] && gameBoard[2][0] == player));
        // logger.info("LINE: ["+getLineNumber()+"] | Has Player Won: "+hasWon);
        return hasWon;
    }
    /**
     * The getAvailableCells method retrieves and returns a
     * list available empty cells on the game board.
     * @return	The list of available cells
     */
    public List<Point> getAvailableCells(){
        List<Point> availableCells = new ArrayList<>();

        for (int r = 1; r <= gameBoard.length; r++) {
            for (int c = 1; c <= gameBoard.length; c++) {
                // logger.info("LINE: ["+getLineNumber()+"] | row: "+r+" | Column: "+c+ " | getSub: "+getSub(r, c));
                if (gameBoard[r-1][c-1] == NO_PLAYER)
                    availableCells.add(new Point(r,c));
            }
        }
        // logger.info("LINE: ["+getLineNumber()+"] | Available Cells: "+Arrays.deepToString(availableCells.toArray()));
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
        // logger.info("LINE: ["+getLineNumber()+"] | Point: "+point.toString()+" | Row: "+point.getRow()+" | Column: "+point.getCol());
        if (gameBoard[point.getRow()][point.getCol()] != NO_PLAYER){
            // logger.info("LINE: ["+getLineNumber()+"] | Unable to Place A Move at => Row: "+point.getRow()+" | Column:"+point.getCol());
            return false;
        }
        gameBoard[point.getRow()][point.getCol()] = player;    
        // logger.info("LINE: ["+getLineNumber()+"] | Placed A Move at => Row: "+point.getRow()+" | Column:"+point.getCol());    
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
                    System.out.print("\t<= ROW "+(i+1));
                }
                // // logger.info("Row "+ j +" I think: "+(j+1) % gameBoard.length);
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
        System.out.println("\n\n---COLUMN");
        System.out.println("\n\n\n");
        // logger.info("LINE: ["+getLineNumber()+"] | Printed Game Board...");
    }
    /**
     * The displayBoard prints the game board to the display.
     * 
     */
    public void displayBoard(char[][] gameBoard) {
        System.out.println("\n");
        for (int i = 0; i<gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard.length; j++)
            {      
                System.out.print(gameBoard[i][j]);
                if (j == gameBoard.length-1) {
                    System.out.print("\t<= ROW "+(i+1));
                }
                // // logger.info("Row "+ j +" I think: "+(j+1) % gameBoard.length);
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
        System.out.println("\n\n---COLUMN");
        System.out.println("\n\n\n");
        // logger.info("LINE: ["+getLineNumber()+"] | Printed Game Board...");
    }
    /**
     * Set up the game board with initial whitespace character.
     * @param gb
     * @return
     */
    public char[][] setupGameBrd(char[][] gb){
        // int label = 0;
        for (int i = 0; i < gb.length; i++) {
            for (int j = 0; j < gb.length; j++) {
                // System.out.println("Sub:" +s);
                gb[i][j] = ' ';
                // logger.info("Sub: |" +gb[i][j]+ "|");
            }
        }
        // logger.info("LINE: ["+getLineNumber()+"] | Finished setting up game...");
        return gb;
    }
    /**
     * Get the current line number of executing thread
     * @return the integer value line number of executing thread
     */
    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

    // TODO: have to finish making the hasPlayerWon scalable
    // Horizontal function: (L*n - L) where L is the length of the array
    // public boolean hasPlayerWon(char player, char[][] gBoard)
    // {
    //     boolean isWinner;
    //     List<char[]> val = new ArrayList<char[]>();
    //     int valid_Counter= 0; // counts how many time a match is found
    //     int numberOfColumns = gBoard.length ;
    //     // get all the rows for checking
    //     for (int i = 0; i < numberOfColumns; i++)
    //         val.add(gBoard[i]);
    //     // now check each set of rows
    //     for (int i = 1; i < numberOfColumns; i++) 
    //     {   
    //         int setNumber = ((numberOfColumns * i) - numberOfColumns);
    //         System.out.pr
    //         // get the first value of each set
    //         char firstValue = val.get(setNumber)[i-1];
    //         for (int j = 0; j < numberOfColumns; j++)
    //             if (firstValue == val.get(setNumber)[j])
    //                 valid_Counter++;
    //     }
    //     if (valid_Counter == numberOfColumns)
    //         return true;
    //     else
    //         isWinner = false;

        


    //     return isWinner;
    // }
    // ///////////////////////////////////// [ MAIN ] //////////////////////////////////////
    // public static void main(String[] args) {  
    //     // char[][] gb = new char[3][3]; 
    //     char[][] gb = {{'X','X','X'},
    //                    {' ','X',' '},
    //                    {' ',' ','X'}};
        
    //     Board bd = new Board();
    //     // System.out.println("bd.getSub: "+bd.getSub(3, 3));
    //     // System.out.println("bd.getSub: "+bd.getCol(8));
    //     // System.out.println("bd.getSub: "+bd.getRow(8));
    //     // gb = bd.setupGameBrd(gb);
    //     bd.displayBoard(gb);
    //     System.out.println(bd.hasPlayerWon('X',gb));
        

    // }
/////////////////////   END OF CLASS Board  /////////////////////////
}
 
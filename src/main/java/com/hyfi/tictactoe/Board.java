package com.hyfi.tictactoe;
// Imports
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

@Data
/**
 * The Board class represents the game board in a Tic Tac Toe game. This class also contains a AI method using the MiniMax algorithm, so the computer can determine the best possible move to secure a winning state or draw during game play.
 * @version 2.2
 * @since 2019-04-12
 */
public class Board {
    private static final Logger log = LoggerFactory.getLogger(Board.class);
//    private GameBoardView view = new GameBoardView();
//    private FXMLBoardController controller = new FXMLBoardController();
    // Constant to represent a blank cell on the game board.
    private static final Object NO_PLAYER = ' ';
    // Holds the value of the current computer mark (X/0).
    private Object computerMark;
    // Holds the value of the current human mark (X/0).
    private Object humanMark;
    // Represents the game board of Object type.
    private Object[][] gameBoard;
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
     * The Board method is the Default constructor. Default board size is 9;
     */
    public Board() {
        log.info("Default constructor...");
//        Application.launch(GameBoardView.class);
//        try{
//            view.init();
//        } catch (Exception e){
//            log.error(e.getLocalizedMessage());
//            e.printStackTrace();
//        }
//        this.gameBoard = setupGameBrd(new Object[3][3]);
        this.gameBoard = new Object[3][3];
        this.boardSize = this.gameBoard.length;
    }
    /**
     * Constructor accepts game board
     * @param gameBoard the game board passed to a new instance of Board
     */
    public Board(Object[][] gameBoard) {
//        Application.launch(GameBoardView.class);
//        try{
//            view.init();
//        } catch (Exception e){
//            log.error(e.getLocalizedMessage());
//            e.printStackTrace();
//        }

        this.gameBoard = gameBoard;
        this.boardSize = this.gameBoard.length;
        log.info("Parameterized constructor...");
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
    public void setBoardSize(Object[][] gameBoard){
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
    public Object[][] getGameBoard() {
        return gameBoard;
    }
    /**
     * Sets the game board
     * @param gameBoard the game board
     */
    public void setGameBoard(Object[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
    /**
     * Gets the the NULL player token
     * @return the NULL player token
     */
    public Object getNoPlayer() {
        return NO_PLAYER;
    }
    /**
     * The setComputerMark method initializes the computer mark.
     * @param playerMark	The player mark.
     */
    public void setComputerMark(Object playerMark) {
        this.computerMark = playerMark;
    }
    /**
     * The setHumanMark method initializes the human mark.
     * @param playerMark	The player mark.
     */
    public void setHumanMark(Object playerMark) {
        this.humanMark = playerMark;
    }
    /**
     * The getComputerMark method retrieves the computers mark.
     * @return	The computer mark on the board during game play.
     */
    public Object getComputerMark() {
        return computerMark;
    }
    /**
     * The getHumanMark method retrieves the human mark.
     * @return	The human mark on the board during game play.
     */
    public Object getHumanMark() {
        return humanMark;
    }
     /**
     * The getSub method accepts the value for the row
     * and column, and returns the subscript value of the cell.
     * @param row		The value of the row.
     * @param col		The value of the column.
     * @return		The index value of the cell on the board.
     */
    public int getSub(int row, int col)	{
        return (int) ((row) * (boardSize/Math.sqrt(boardSize)) + (col));
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
				log.error("Unable to place a move...\n"+e.getMessage());
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
                this.gameBoard[i][j] = '\u0000';
            }  
        }
    }

    /**
     * The clearBoard method wipes the game board of all moves
     * and resets the game board to its original state.
     * @param gameBoard  The game board
     */
    public void clearBoard(Object[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++){
                this.gameBoard[i][j] = '\u0000';
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
                log.info("Game is Over: "+over);
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
    // public boolean hasPlayerWon(Object player) {
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

        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard.length; c++)
            {

                // logger.debug("row: "+r+" | Column: "+c+ " | getSub: "+getSub(r, c));
                if (String.valueOf(this.gameBoard[r][c]).isEmpty()) {
                    availableCells.add(new Point(r, c, boardSize));
                }
            }
        }
        log.info("Available Cells: "+Arrays.deepToString(availableCells.toArray()));
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
    public boolean placeAMove(Point point, Object player) {
        // System.out.println("Row: "+point.getRow());
        // System.out.println("Col: "+point.getCol());
        log.info("\nPoint: {} | Player: {}", point, player);
        // logger.debug("Point: "+point.toString()+" | Row: "+point.getRow()+" | Column: "+point.getCol());
        if (String.valueOf(this.gameBoard[point.getRow()][point.getCol()]).isEmpty()){
            log.info("Unable to Place A Move at => Row: "+point.getRow()+" | Column: "+point.getCol());
            return false;
        }
        gameBoard[point.getRow()][point.getCol()] = player;
        setLastMove(point);
        log.info("Placed A Move at => Row: {}  | Column: {} \n Last moves: {}",point.getRow(),point.getCol(), this.getLastMove().stream().collect(Collectors.toList()));
        return true;
    }

//    private void updateModelView(Point p, Object c){
//
//        if (String.valueOf( c ).equalsIgnoreCase("X")){
//            switch (p.getSub()){
//
//                case 0 : controller.getImageView1().setImage(controller.getXs());
//                    break;
//                case 1 : controller.getImageView2().setImage(controller.getXs());
//                    break;
//                case 2 : controller.getImageView3().setImage(controller.getXs());
//                    break;
//                case 3 : controller.getImageView4().setImage(controller.getXs());
//                    break;
//                case 4 : controller.getImageView5().setImage(controller.getXs());
//                    break;
//                case 5 : controller.getImageView6().setImage(controller.getXs());
//                    break;
//                case 6 : controller.getImageView7().setImage(controller.getXs());
//                    break;
//                case 7 : controller.getImageView8().setImage(controller.getXs());
//                    break;
//                case 8 : controller.getImageView0().setImage(controller.getXs());
//                    break;
//            }
//        } else {
//            switch (p.getSub()){
//
//                case 0 : controller.getImageView1().setImage(controller.getOs());
//                    break;
//                case 1 : controller.getImageView2().setImage(controller.getOs());
//                    break;
//                case 2 : controller.getImageView3().setImage(controller.getOs());
//                    break;
//                case 3 : controller.getImageView4().setImage(controller.getOs());
//                    break;
//                case 4 : controller.getImageView5().setImage(controller.getOs());
//                    break;
//                case 5 : controller.getImageView6().setImage(controller.getOs());
//                    break;
//                case 6 : controller.getImageView7().setImage(controller.getOs());
//                    break;
//                case 7 : controller.getImageView8().setImage(controller.getOs());
//                    break;
//                case 8 : controller.getImageView0().setImage(controller.getOs());
//                    break;
//            }
//
//        }
//    }
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
        log.info("Printed Game Board...");
    }
    /**
     * The displayBoard prints the game board to the display.
     * @param gameBoard the game board
     */
    public void displayBoard(Object[][] gameBoard) {
        System.out.println("\n");
        for (int i = 0; i < gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard.length; j++)
            {      
                System.out.printf(String.valueOf(gameBoard[i][j]));
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
        log.info("Printed Game Board...");
    }
    /**
     * Set up the game board with initial whitespace character.
     * @param gBoard the game board
     * @return the setup game board
     */
    public Object[][] setupGameBrd(Object[][] gBoard){
        // int label = 0;
        for (int i = 0; i < gBoard.length; i++) {
            for (int j = 0; j < gBoard.length; j++) {
                // System.out.println("Sub:" +s);
                if (String.valueOf(gBoard[i][j]).matches("([XOxo]?)"))
                {
                    log.info("Cell taken: I: {}, J:{}, {} ",i,j, String.valueOf(gBoard[i][j]));
                }else {
                    gBoard[i][j] = ' ';
                }
                // logger.debug("Sub: |" +gBoard[i][j]+ "|");
            }
        }
        log.info("Finished setting up game...");
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
    public boolean hasPlayerWon(Object player)
    {
        boolean hasPlayerWon=false;
        log.info("Last Move Size" +
                ": {}",lastMove.size());
//        log.info("Testing player: {}",player);
//        displayBoard(getGameBoard());

        Object[][] gBoard = getGameBoard();
        if (lastMove.size() == 0){
            throw new IllegalStateException("No last moves have been recorded.");
        }
        int numberOfColumns = gBoard.length;
        // check the rows
        BooleanSupplier horizontalCheck = () ->
        {
//            log.info("Performing horizontal check......");
            // counts how many time a match is found
            int validityCounter; 
            // now check each set of rows
            for (int i = 0; i < numberOfColumns; i++) 
            {   // reset counter after every row check
                validityCounter= 0; 
                // System.out.println("Valid cntr: "+validityCounter);
                // get the first value of each set
                // Object firstValue = val.get(i)[0];
                for (int j = 0; j < numberOfColumns; j++)
                {
//                    log.info("Testing[{}][{}]: {}",i,j, gBoard[i][j]);
                    if (player == gBoard[i][j])
                    {
                        validityCounter++;
//                         log.info("Valid cntr: "+validityCounter);
                        if (validityCounter == numberOfColumns) {
//                            log.info("\nPerforming horizontalCheck check......\nWinner found......");
                            return true;
                        }
                    }
                }
            }
            return false;
        };
        // check the columns
        BooleanSupplier verticalCheck = () -> {
//            log.info("Performing vertical check......");
            // counts how many time a match is found

            // get all the columns for checking
            for (int i = 0; i < numberOfColumns; i++)
            {   // reset counter after every row check
                int validityCounter= 0;
                // System.out.println("Valid cntr: "+validityCounter);
                for (int j = 0; j < numberOfColumns; j++)
                {
                    if (player == gBoard[j][i])
                    {
                        validityCounter++;
                        // System.out.println("Valid cntr: "+validityCounter);
                        if (validityCounter == numberOfColumns){
//                            log.info("\nPerforming verticalCheck check......\nWinner found......");
                            return true;
                        }
                    }
                }
            }
            return false;
        };
        // check the diagonal from top left
        BooleanSupplier diagonalCheckFromTopLeft = () -> {
//            log.info("Performing diagonalCheckFromTopLeft check......");
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
                    if (validityCounter == numberOfColumns){
//                        log.info("\nPerforming diagonalCheckFromTopLeft check......\nWinner found......");
                        return true;}
                }
                // else break;
            }
            return false;
        };
        // check the diagonal from bottom left
        BooleanSupplier diagonalCheckFromBottomLeft = () -> {
//            log.info("Performing diagonalCheckFromBottomLeft check......");
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
                    if (validityCounter == numberOfColumns){
//                        log.info("\nPerforming diagonalCheckFromBottomLeft check......\nWinner found......");
                        return true;}
                }
                // else break;
            }
            return false;
        };

        if (verticalCheck.getAsBoolean())
            hasPlayerWon = true;
        if (horizontalCheck.getAsBoolean())
            hasPlayerWon = true;
        if (diagonalCheckFromTopLeft.getAsBoolean())
            hasPlayerWon = true;
        if (diagonalCheckFromBottomLeft.getAsBoolean())
            hasPlayerWon = true;
        return hasPlayerWon;
    }

    public static class MyTask implements Runnable {
        BooleanSupplier target;
        Boolean bool;

        public MyTask(BooleanSupplier target) {
            this.target = target;
        }

        @Override
        public void run() {
            bool = target.getAsBoolean();
        }
    }
//    /**
//     * Dynamically checks for a winning pattern based on the board size
//     * @param player the player mark
//     * @param gBoard the game board
//     * @return true if player won, false if no win
//     */
//    public boolean hasPlayerWon(Object player, Object[][] gBoard)
//    {
//        boolean won = false;
//
//        int numberOfColumns = gBoard.length;
//        // lambda to check the rows
//        BooleanSupplier horizontalCheck = () -> {
//            boolean isWinner = false;
//            List<Object[]> val = new ArrayList<Object[]>();
//            // counts how many time a match is found
//            int validityCounter;
//            // get all the rows for checking
//            for (int i = 0; i < numberOfColumns; i++)
//            val.add(gBoard[i]);
//            // now check each set of rows
//            for (int i = 0; i < numberOfColumns; i++)
//            {   // reset counter after every row check
//                validityCounter= 0;
//                // System.out.println("Valid cntr: "+validityCounter);
//                // get the first value of each set
//                // Object firstValue = val.get(i)[0];
//                for (int j = 0; j < numberOfColumns; j++)
//                {
//                    if (player == val.get(i)[j])
//                    {
//                        validityCounter++;
//                        // System.out.println("Valid cntr: "+validityCounter);
//                        if (validityCounter == numberOfColumns)
//                            return isWinner = true;
//                    }
//                    // else break;
//                }
//            }
//            return isWinner;
//        };
//        // lambda to check the columns
//        BooleanSupplier verticalCheck = () -> {
//            boolean isWinner = false;
//            // counts how many time a match is found
//            int validityCounter;
//            // get all the columns for checking
//            for (int i = 0; i < numberOfColumns; i++)
//            {   // reset counter after every row check
//                validityCounter= 0;
//                // System.out.println("Valid cntr: "+validityCounter);
//                for (int j = 0; j < numberOfColumns; j++)
//                {
//                    if (player == gBoard[j][i])
//                    {
//                        validityCounter++;
//                        // System.out.println("Valid cntr: "+validityCounter);
//                        if (validityCounter == numberOfColumns)
//                            return isWinner = true;
//                    }
//                    // else break;
//                }
//            }
//            return isWinner;
//        };
//        // lambda to check the diagonal from top left
//        BooleanSupplier diagonalCheckFromTopLeft = () -> {
//            boolean isWinner = false;
//            // counts how many time a match is found
//            // reset counter after every row check
//            int validityCounter = 0;
//            /// check diagonal from top left
//            for (int i = 0; i < numberOfColumns; i++)
//            {
//                // System.out.println("Valid cntr: "+validityCounter);
//                // System.out.println("Row calc: "+i+" | Column: "+i);
//                if (player == gBoard[i][i])
//                {
//                    validityCounter++;
//                    // System.out.println("Valid cntr: "+validityCounter);
//                    if (validityCounter == numberOfColumns)
//                        return isWinner = true;
//                }
//                // else break;
//            }
//            return isWinner;
//        };
//        // lambda to check the diagonal from bottom left
//        BooleanSupplier diagonalCheckFromBottomLeft = () -> {
//            boolean isWinner = false;
//            // counts how many time a match is found
//            // reset counter after every row check
//            int validityCounter = 0;
//            // check diagonal from bottom lift
//            for (int i = 0; i < numberOfColumns; i++)
//            { // Count down function: a_n = -i + numberOfColumns
//                // System.out.println("Valid cntr: "+validityCounter);
//                // System.out.println("Row calc: "+(numberOfColumns - (i+1))+" | Column: "+i);
//                if (player == gBoard[(numberOfColumns - (i+1))][i])
//                {
//                    validityCounter++;
//                    // System.out.println("Valid cntr: "+validityCounter);
//                    if (validityCounter == numberOfColumns)
//                        return isWinner = true;
//                }
//                // else break;
//            }
//            return isWinner;
//        };
//
//        // List<Point> lMove = new ArrayList<Point>(getLastMove());
//        // Function<Character,Integer> lM = (playerMark) -> {
//        //     int cnt = 0;
//        //     for (Point point : lMove) {
//        //         if (gameBoard[point.getRow()][point.getCol()] == (playerMark)){
//        //             logger.debug("Player mark found: " + gameBoard[point.getRow()][point.getCol()] + " @ Point: "+ point.toString());
//        //             cnt++;
//        //         }
//        //     }
//        //     return cnt;
//        // };
//
//        // // exit if number of moves less than quantity to constitute a win
//        // if(lMove.size() < gameBoard.length)
//        // {
//        //     return false;
//        // } // exit if number of player mark is less than quantity to constitute a win
//        // else if (lM.apply(player) < gameBoard.length)
//        // {
//        //     return false;
//        // }
//        // won = verticalCheck.getAsBoolean();
//        // won = diagonalCheckFromTopLeft.getAsBoolean();
//        // won = diagonalCheckFromBottomLeft.getAsBoolean();
//        // won = horizontalCheck.getAsBoolean();
//        won =   verticalCheck.getAsBoolean() == true ? true :
//                horizontalCheck.getAsBoolean() == true ? true :
//                diagonalCheckFromTopLeft.getAsBoolean() == true ? true :
//                diagonalCheckFromBottomLeft.getAsBoolean() == true ? true : false;
//                log.info("Player "+player+" has won: "+won);
//        return won;
//    }
//    /**
//     * Dynamically checks for a winning pattern based on the board size.
//     * @param player the player mark
//     * @param gBoard the game board
//     * @param number_Of_Win_Pattern_To_Check_In_A_Row define the size of the win pattern, i.e. 4x4, 5x5
//     * @return true if player won, false if no win
//     */
//    public boolean hasPlayerWon(Object player, Object[][] gBoard, int number_Of_Win_Pattern_To_Check_In_A_Row)
//    {
//        boolean won = false;
//
//        int numberOfColumns = number_Of_Win_Pattern_To_Check_In_A_Row;
//        // lambda to check the rows
//        BooleanSupplier horizontalCheck = () -> {
//            boolean isWinner = false;
//            List<Object[]> val = new ArrayList<Object[]>();
//            // counts how many time a match is found
//            int validityCounter;
//            // get all the rows for checking
//            for (int i = 0; i < numberOfColumns; i++)
//            val.add(gBoard[i]);
//            // now check each set of rows
//            for (int i = 0; i < numberOfColumns; i++)
//            {   // reset counter after every row check
//                validityCounter= 0;
//                // System.out.println("Valid cntr: "+validityCounter);
//                // get the first value of each set
//                // Object firstValue = val.get(i)[0];
//                for (int j = 0; j < numberOfColumns; j++)
//                {
//                    if (player == val.get(i)[j])
//                    {
//                        validityCounter++;
//                        // System.out.println("Valid cntr: "+validityCounter);
//                        if (validityCounter == numberOfColumns)
//                            return isWinner = true;
//                    }
//                    // else break;
//                }
//            }
//            return isWinner;
//        };
//        // lambda to check the columns
//        BooleanSupplier verticalCheck = () -> {
//            boolean isWinner = false;
//            // counts how many time a match is found
//            int validityCounter;
//            // get all the columns for checking
//            for (int i = 0; i < numberOfColumns; i++)
//            {   // reset counter after every row check
//                validityCounter= 0;
//                // System.out.println("Valid cntr: "+validityCounter);
//                for (int j = 0; j < numberOfColumns; j++)
//                {
//                    if (player == gBoard[j][i])
//                    {
//                        validityCounter++;
//                        // System.out.println("Valid cntr: "+validityCounter);
//                        if (validityCounter == numberOfColumns)
//                            return isWinner = true;
//                    }
//                    // else break;
//                }
//            }
//            return isWinner;
//        };
//        // lambda to check the diagonal from top left
//        BooleanSupplier diagonalCheckFromTopLeft = () -> {
//            boolean isWinner = false;
//            // counts how many time a match is found
//            // reset counter after every row check
//            int validityCounter = 0;
//            /// check diagonal from top left
//            for (int i = 0; i < numberOfColumns; i++)
//            {
//                // System.out.println("Valid cntr: "+validityCounter);
//                // System.out.println("Row calc: "+i+" | Column: "+i);
//                if (player == gBoard[i][i])
//                {
//                    validityCounter++;
//                    // System.out.println("Valid cntr: "+validityCounter);
//                    if (validityCounter == numberOfColumns)
//                        return isWinner = true;
//                }
//                // else break;
//            }
//            return isWinner;
//        };
//        // lambda to check the diagonal from bottom left
//        BooleanSupplier diagonalCheckFromBottomLeft = () -> {
//            boolean isWinner = false;
//            // counts how many time a match is found
//            // reset counter after every row check
//            int validityCounter = 0;
//            // check diagonal from bottom lift
//            for (int i = 0; i < numberOfColumns; i++)
//            { // Count down function: a_n = -i + numberOfColumns
//                // System.out.println("Valid cntr: "+validityCounter);
//                // System.out.println("Row calc: "+(numberOfColumns - (i+1))+" | Column: "+i);
//                if (player == gBoard[(numberOfColumns - (i+1))][i])
//                {
//                    validityCounter++;
//                    // System.out.println("Valid cntr: "+validityCounter);
//                    if (validityCounter == numberOfColumns)
//                        return isWinner = true;
//                }
//                // else break;
//            }
//            return isWinner;
//        };
//        // won = verticalCheck.getAsBoolean();
//        // won = diagonalCheckFromTopLeft.getAsBoolean();
//        // won = diagonalCheckFromBottomLeft.getAsBoolean();
//        // won = horizontalCheck.getAsBoolean();
//        won =   verticalCheck.getAsBoolean() == true ? true :
//                horizontalCheck.getAsBoolean() == true ? true :
//                diagonalCheckFromTopLeft.getAsBoolean() == true ? true :
//                diagonalCheckFromBottomLeft.getAsBoolean() == true ? true : false;
//        log.info("Player "+player+" has won: "+won);
//        return won;
//    }


//     public static void main(String[] arg)
//     {
//         ///////////////////////////////////////////////////////////////////////
//         // TicTacToe t = new TicTacToe();
//	 	// t.play();
//         ///////////////////////////////////////////////////////////////////////
//             // TicTacToe t = new TicTacToe();
//             // Object[][] gBoard = new Object[3][3];
//             Object[][] gBoard = { {'X','O','O','O'},
//                                 {'X','X','X','O'},
//                                 {'O','O','X','O'},
//                                 {'O','O','X','O'} };
//             // // Convert elements to strings and concatenate them, separated by commas
//         // String joined = things.stream()
//         // .map(Object::toString)
//         // .collect(Collectors.joining(", "));
//             log.info("\nBoard: {}", Arrays.stream(gBoard).collect(Collectors.toList()));
//             Board bd = new Board(gBoard);
////             bd.setupGameBrd(gBoard);
//             bd.setComputerMark('X');
//             bd.setHumanMark('O');
//             bd.setLastMove(new Point(2,2,bd.boardSize));
//             // // System.out.println("bd.getSub: "+bd.getSub(3, 3));
//             // // System.out.println("bd.getSub: "+bd.getCol(8));
//             // // System.out.println("bd.getSub: "+bd.getRow(8));
//             bd.displayBoard(gBoard);
//             log.info("Is \"O\" Winner: {}",bd.hasPlayerWon('X'));
//             log.info("End of Game TicTacToe............");
//
//
//         // System.out.println("End of test class...");
//     }

/////////////////////   END OF CLASS Board  /////////////////////////
}
 
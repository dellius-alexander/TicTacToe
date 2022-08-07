package com.hyfi.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Board class represents the game board in a Tic Tac Toe game. This
 * class also contains AI method using the MiniMax algorithm, so the
 * computer can determine the best possible move to secure a winning state
 * or draw during game play.
 * @version 2.2
 * @since 2019-04-12
 */
public class Board implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Board.class);
    // Constant to represent a blank cell on the game board.
    private static final Object NO_PLAYER = "";
    // Holds the value of the current computer mark (X/0).
    private Object computerMark;
    // Holds the value of the current human mark (X/0).
    private Object humanMark;
    // Represents the game board of Object type.
    private Object[][] gameBoard;
    // Holds the value of "row and column" reference to computer move.
    private Map<Integer, Point> computerMoves;
    // Holds the value of the current round at play
    private int round = '\u0000';
    // Holds the value of the score returned from a simulated
    // iteration of game play at current depth
    private int computerScore = '\u0000';
    private int humanScore = '\u0000';
    // The value of current depth
    private int depth = '\u0000';;
    // holds the size of the board
    private int boardSize;
    // holds the last move played
//    private Map<Integer, Point> lastMoves;
    private Queue<Point> lastMoves;

    /**
     * The Board method is the Default constructor. Default board size is 9;
     */
    public Board() {
        log.info("\nDefault constructor...");
        this.lastMoves = new ArrayDeque<>();
        this.gameBoard = setupGameBrd(new Object[3][3]);
        this.boardSize = 0;
        this.computerMoves = new HashMap<>();
        this.humanMark = null;
        this.computerMark = null;
        this.computerScore = '\u0000';
        this.humanScore ='\u0000';
        this.depth = '\u0000';
        this.round = '\u0000';
    }

    /**
     * Constructor accepts game board
     * @param gameBoard the game board passed to a new instance of Board
     */
    public Board(Object[][] gameBoard) {
        try{
            this.lastMoves = new ArrayDeque<>();
            this.gameBoard = setupGameBrd(gameBoard);
            this.boardSize = this.gameBoard.length;
            this.computerMoves = new HashMap<>();
            this.humanMark = null;
            this.computerMark = null;
            this.computerScore = '\u0000';
            this.humanScore ='\u0000';
            this.depth = '\u0000';
            this.round = '\u0000';
            log.info("\nParameterized constructor...");
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    public Board(String[][] gameBoard){
        try {
            this.lastMoves = new ArrayDeque<>();
            this.gameBoard = setupGameBrd(gameBoard);
            this.boardSize = gameBoard.length;
            this.computerMoves = new HashMap<>();
            this.humanMark = null;
            this.computerMark = null;
            this.computerScore = '\u0000';
            this.humanScore ='\u0000';
            this.depth = '\u0000';
            this.round = '\u0000';
            log.info("\nParameterized constructor...");
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
    public Board(Board b){
        try {
            this.lastMoves = new ArrayDeque<>();
            this.gameBoard = b.clone();
            this.boardSize = b.getBoardSize();
            this.computerMoves = new HashMap<>();
            this.computerMark = b.getComputerMark() != null ? b.getComputerMark() : "";
            this.humanMark = b.getHumanMark() != null ? b.getHumanMark() : "";
            this.computerScore = '\u0000';
            this.humanScore = '\u0000';
            this.depth = '\u0000';
            this.round = '\u0000';
            log.info("\nNew board initialized...");
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    /**
     * Clone the game board.
     * @return {@linkplain  Object[][]}
     */
    @Override
    public synchronized Object[][] clone() throws CloneNotSupportedException {

        Object[][] b = new Object[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                b[i][j] = gameBoard[i][j];
            }
        }
        return b;
    }



//    public synchronized Board setNext(Board next){
//        this.listLength++;
//        return this.next = next; }
//    public synchronized Board getNext(){
//        Board temp = this.next;
//        return temp;}
//    public synchronized Board setPrevious(Board previous){
//        return this.previous = previous; }
//    public synchronized Board getPrevious(){
//        Board temp = this.previous;
//        return temp;}
    public Board getBoard(){return this;}
    public int getHumanScore() { return humanScore; }
    public Queue<Point>  getLastMoves(){return this.lastMoves;}
    public void setLastMoves(Queue<Point> lastMoves) { this.lastMoves = lastMoves; }
//    public int getLength(){
//        Board temp = this.getPrevious() == null ? null : this.getPrevious();
//        while (temp != null){
//            temp = temp.getPrevious();
//            this.listLength++;
//        }
//        temp = this.getNext() == null ? null : this.getNext();
//        while (temp != null){
//            this.listLength++;
//            temp = temp.getNext();
//        }
//
//        return this.listLength;
//    }
    /**
     * Adds the last move played to a list of previous moves
     * @param point the point played last
     */
    public void setLastMove(Point point)
    {
        this.lastMoves.add( point);
    }


    /**
     * Gets a list of privious played moves
     * @return the list of last moves
     */
    public Point getLastMove()
    {
        return this.lastMoves.poll();
    }

    /**
     * Removes the last move
     */
    public void removeLastMove(){
        this.lastMoves.remove(this.lastMoves.size() - 1);
    }

//    /**
//     * Sets the board size
//     */
//    public void setBoardSize(){
//        this.boardSize = this.gameBoard.length;
//    }

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
     * @param score the value returned from a win(1)/lose(-1)/draw(0)
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
        return (int) ((row) * (boardSize) + (col));
    }
    /**
     * The getRow method accepts the value for the subscript
     * and returns the value of the row.
     * @param sub	The subscript value.
     * @return		The value of the row.
     */
    public int getRow(int sub) {
        return (sub / this.boardSize);
    }
    /**
     * The getCol method accepts the value of the subscript
     * and returns the value of the column.
     * @param sub 	The subscript value.
     * @return		The value of the column.
     */
    public int getCol(int sub) {

        return  (sub % this.boardSize);
    }
    /**
     * Get the best entry.
     * @param  key element key value
     * @return	{@linkplain  Map.Entry}
     */
    public Map.Entry<Integer,Point> getBestEntry(int key) {
        log.info("\nContainer size: {}", this.computerMoves.size());
        Point point = null; // container for the best move
        try {
            int ittr = 0;
            // iterate through each entry saved to computer moves to find the best move
            for (Map.Entry<Integer, Point> entry : this.computerMoves.entrySet()) {
                if (placeAMove(entry.getValue(), computerMark)) {
                    if (hasPlayerWon(computerMark)) {
                        gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                        return entry;
                    }
                    gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                }
            }
            for (Map.Entry<Integer, Point> entry : this.computerMoves.entrySet()) {
                if (placeAMove(entry.getValue(), humanMark)) {
                    if (hasPlayerWon(humanMark)) {
                        gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                        return entry;
                    }
                    gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                }
            }

            for (Map.Entry<Integer, Point> entry : this.computerMoves.entrySet()) {
                if (entry.getKey() != null && entry.getKey() == 0) {
                    gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                    return entry;
                }
            }

        } catch (NullPointerException e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        final Point[] p = {this.computerMoves.get(key)};
        return new Map.Entry<Integer, Point>() {
            @Override
            public Integer getKey() {
                return key;
            }

            @Override
            public Point getValue() {
                return p[0];
            }

            @Override
            public Point setValue(Point value) {
                return p[0] = value;
            }
        };
    }

    /**
     * The getComputerMove returns the row and column
     * value of the subscript value for the computer
     * move when called.
     * @return	The object representing row and column
     */
    public Map.Entry<Integer, Point> getBestMove() {
        log.info("\nContainer size: {}", this.computerMoves.size());
        try {
            // iterate through each entry saved to computer moves to find the best move
            for (Map.Entry<Integer, Point> entry : this.computerMoves.entrySet()) {
                if (placeAMove(entry.getValue(), computerMark)) {
                    if (hasPlayerWon(computerMark)) {
                        gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                        return entry;
                    }
                    gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                }
            }
            // iterate through each entry saved to computer moves to find the best move
            for (Map.Entry<Integer, Point> entry : this.computerMoves.entrySet()) {
                if (placeAMove(entry.getValue(), humanMark)) {
                    if (hasPlayerWon(humanMark)) {
                        gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                        return entry;
                    }
                    gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                }
            }
            // iterate through each entry saved to computer moves to find the best move
            for (Map.Entry<Integer, Point> entry : this.computerMoves.entrySet()) {
                if (entry.getKey() != null && entry.getKey() == 0) {
                    gameBoard[entry.getValue().getRow()][entry.getValue().getCol()] = NO_PLAYER;
                    return entry;
                }
                return entry;
            }

        } catch (NullPointerException e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the map of computer moves.
     * @return {@linkplain Map} Computer moves
     */
    public Map<Integer, Point> getComputerMoves(){
        return this.computerMoves;
    }
    public void setComputerMoves(Map<Integer, Point> computerMoves) { this.computerMoves = computerMoves; }
    /**
     * Gets the game board size
     * @return the game board size
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * The setComputerMove method accepts a point object
     * of row and column and assigns the value to
     * computerMove.
     * @param key key
     * @param point {@link Point}
     * @return true if computer move set successfully
     */
    public boolean setComputerMove(Integer key, Point point) {
        try {
            log.info("\nComputer Move: {}",
                    this.computerMoves.toString());
            if (point != null && key != null) {
                this.computerMoves.put(key,point);
                return true;
            }
        } catch (Exception e){
            log.error("Unable to place a move...\n{}",e.getLocalizedMessage());
            e.printStackTrace();
        }
        return false; 
    }

    /**
     * The clearBoard method wipes the game board of all moves
     * and resets the game board to its original state.
     */
    public void clearBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = NO_PLAYER;
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
            for (int j = 0; j < gameBoard.length; j++) {
                gameBoard[i][j] = NO_PLAYER;
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
                log.info("\nIs game over: {}",over);
                return over;
    }
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
                if ( this.gameBoard[r][c] == NO_PLAYER )
                {
                    log.info("\nEmpty Cell found: Row => {}, Column => {}, Content => {}",
                            r, c, gameBoard[r][c]);
                    availableCells.add(new Point(r, c, boardSize));
                }
            }
        }
        log.info("\nAvailable Cells: {}",
                Arrays.stream(availableCells.toArray()).collect(Collectors.toList()));
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
//        if (this.gameBoard[point.getRow()][point.getCol()] != NO_PLAYER){
//            log.info("\nUnable to Place A Move at => Row: "+point.getRow()+" | Column: "+point.getCol());
//            return false;
//        }
        if ( gameBoard[point.getRow()][point.getCol()] == NO_PLAYER
        || gameBoard[point.getRow()][point.getCol()] == null){
            gameBoard[point.getRow()][point.getCol()] = player;
            setLastMove(point);
            log.info("\nPlaced A Move at => Row: {}  | Column: {} \n Last moves: {}",
                    point.getRow(),point.getCol(),
                    this.lastMoves.stream().collect(Collectors.toList()));
            return true;
        }
        else {
            log.info("\nUnable to Place A Move at => Row: " +
                    point.getRow() + " | Column: " + point.getCol());
            return false;
        }
    }


    /**
     * The displayBoard prints the game board to the display.
     * 
     */
    public void displayBoard() {
        System.out.printf("%s",displayBoard(this.gameBoard));
    }
    /**
     * The displayBoard prints the game board to the display.
     * @param gameBoard the game board
     * @return the game board in string format.
     */
    public String displayBoard(Object[][] gameBoard) {
        String board = String.format("\n\n");

        for (int i = 0; i<gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard.length; j++)
            {
                board += String.format("%s",(gameBoard[i][j].toString().isEmpty() ? " ": gameBoard[i][j]));
                if (j == gameBoard.length-1)
                {
                    board += String.format("\t\t<= ROW %s", (i+1));
                }
                if (((j+1) % gameBoard.length) == 0)
                {
                    if (j < (gameBoard.length))
                    {
                        board += String.format(" \n");
                    }
                    if (i < gameBoard.length-1)
                    {
                        for (int j2 = 0; j2 < gameBoard.length-1; j2++)
                        {
                            board += String.format("-+");
                        }
                    }
                    if (i < (gameBoard.length-1))
                    {
                        board += String.format("-\n");
                    }
                }
                else
                {
                    board += String.format("|");
                }
            }
        }
        board += String.format("\n");
        for (int i = 0; i < gameBoard.length; i++)
        {
            board += String.format("%s ",(i+1));
        }
        board += String.format("\n\n⇑-⇑-⇑-COLUMNS\n");
//        System.out.printf("%s",board);
        log.info("\n{} \n\nPrinted Game Board...", board);
        return board;
    }
    /**
     * Set up the game board with initial whitespace character.
     * @param gBoard the game board
     * @return the setup game board
     */
    public Object[][] setupGameBrd(Object[][] gBoard){
        // int label = 0;
        log.info("\nGame board Length: {}",gBoard.length);
        for (int i = 0; i < gBoard.length; i++) {
            for (int j = 0; j < gBoard.length; j++) {
                // System.out.println("Sub:" +s);
                if (gBoard[i][j] != null)
                {
                    log.info("\nCell taken: I: {}, J:{}, contents: {} ",i,j,gBoard[i][j].toString());
                }else {
                    gBoard[i][j] = NO_PLAYER;
                    log.info("\nSetting => Row: {} | Column: {}, contents: {}",i,j,gBoard[i][j].toString());
                }
            }
        }
        log.info("\nFinished setting up game...");
        return gBoard;
    }

    public boolean diagonalCheckFromBottomLeft(Object player){
        log.info("\nPerforming diagonalCheckFromBottomLeft(for Player : {})",player);
        Object[][] gBoard = getGameBoard();
        int numberOfColumns = gBoard.length;
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
                log.info("\ndiagonalCheckFromBottomLeft Valid Counter: {} | Point => row: {} , Column: {}",
                        validityCounter, (numberOfColumns - (i+1)), i);
            }
            else if (validityCounter < i){
                isWinner = false;
                break;
            }
            if (validityCounter == numberOfColumns){
                log.info("\nWinner Found => diagonalCheckFromBottomLeft(for Player : {})",player);
                isWinner = true;
                break;
            }
        }
        return isWinner;
    }

    public boolean diagonalCheckFromTopLeft(Object player){
        log.info("\nPerforming diagonalCheckFromTopLeft(for Player : {})",player);
        Object[][] gBoard = getGameBoard();
        int numberOfColumns = gBoard.length;
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
                log.info("\ndiagonalCheckFromTopLeft Valid Counter: {} | Point => row: {} , Column: {}",
                        validityCounter, i, i);
            }
            else if (validityCounter < i){
                isWinner = false;
                break;
            }
            if (validityCounter == numberOfColumns){
                log.info("\nWinner Found => diagonalCheckFromTopLeft(for Player : {})",player);
                isWinner = true;
                break;
            }
        }
        return isWinner;
    }

    public boolean horizontalCheck(Object player){
        log.info("\nPerforming horizontalCheck(for Player : {})",player);
        Object[][] gBoard = getGameBoard();
        int numberOfColumns = gBoard.length;
        // counts how many time a match is found
        boolean isWinner = false;
        int validityCounter;
        // now check each set of rows
        for (int i = 0; i < numberOfColumns; i++)
        {   // reset counter after every row check
            validityCounter= 0;
            for (int j = 0; j < numberOfColumns; j++)
            {
//                    log.info("\nTesting[{}][{}]: {}",i,j, gBoard[i][j]);
                if (player == gBoard[i][j])
                {
                    validityCounter += 1;
                    log.info("\nhorizontalCheck Valid Counter: {} | Point => row: {} , Column: {}",
                            validityCounter, i, j);
                }
                else if (validityCounter < j){
                    isWinner = false;
                    break;
                }
            }
            if (validityCounter == numberOfColumns){
                log.info("\nWinner Found => horizontalCheck(for Player : {})",player);
                isWinner = true;
                break;
            }
        }
        return isWinner;
    }

    public boolean verticalCheck(Object player){
        log.info("\nPerforming verticalCheck(for Player : {})",player);
        Object[][] gBoard = getGameBoard();
        int numberOfColumns = gBoard.length;
        // counts how many time a match is found
        boolean isWinner = false;
        int validityCounter;
        // get all the columns for checking
        for (int i = 0; i < numberOfColumns; i++)
        {   // reset counter after every row check
            validityCounter = 0;
            // System.out.println("Valid cntr: "+validityCounter);
            for (int j = 0; j < numberOfColumns; j++)
            {
                if (player == gBoard[j][i])
                {
                    validityCounter += 1;
                    log.info("\nverticalCheck Valid Counter: {} | Point => row: {} , Column: {}",
                            validityCounter, j, i);
                }
                else if (validityCounter < j){
                    isWinner = false;
                    break;
                }
            }
            if (validityCounter == numberOfColumns){
                log.info("\nWinner Found => verticalCheck(for Player : {})",player);
                isWinner = true;
                break;
            }
        }
        return isWinner;
    }
    /**
     * The hasPlayerWon method accepts a player mark and returns
     * a true or false value if winner exist or not.
     * <br>
     * _____ COLUMNS ]____ <br>
     *    0     1     2 <br>
     * ___________________ <br>
     * |__o__|__x__|__o__|   =  0 <br>
     * |__x__|__x__|__x__|   =  1 [   |ROWS|   ] <br>
     * |__o__|__o__|__x__|   =  2 <br>
     * <br>
     * There is a pattern that opens up from the few sequences below.
     * Can we find an equation to abstract each sequence. Yes...
     * <br>
     * Horizontal sequence = [0][0],[0][1],[0][2] <br>
     * Vertical sequence = [0][0],[1][0],[2][0] <br>
     * Diagonal sequence A = [0][0],[1][1],[2][2] <br>
     * Diagonal sequence B = [2][0],[1][1],[0][2] <br>
     * <br>
     * Sequences: <br>
     *  0,1,2 : a_n = n − 1 <br>
     *  1,2,3 : a_n = n <br>
     *  2,3,4 : a_n = n + 1 <br>
     *  0,4,8 : a_n = 4n - 4 <br>
     *  6,4,2 : a_n = 2n + 8 <br>
     * <br>
     * Dynamically checks for a winning pattern based on the board size
     * @param player the player mark
     * @return {@linkplain boolean} true if player won, false if no win
     */
    public boolean hasPlayerWon(Object player) {
        log.info("\n\nTesting => Player: {}\n",player);
        // parallel execution implementation for game board checks
        Stream<Boolean> s = null;
        Collection<CompletableFuture<Boolean>> futures;
        boolean results = false;
        try {
            if (getAvailableCells().size() >= (Math.pow(getBoardSize(), 2) - getBoardSize())) {
                return false;
            } else {
                futures = Set.of(
                    CompletableFuture.<Boolean>supplyAsync(
                        new Supplier<Boolean>() {
                            /**
                             * Gets a result.
                             *
                             * @return a result
                             */
                            @Override
                            public Boolean get() {
                                return verticalCheck(player);
                            }
                        }),
                        CompletableFuture.<Boolean>supplyAsync(
                                new Supplier<Boolean>() {
                                    /**
                                     * Gets a result.
                                     *
                                     * @return a result
                                     */
                                    @Override
                                    public Boolean get() {
                                        return horizontalCheck(player);
                                    }
                                }),
                    CompletableFuture.<Boolean>supplyAsync(
                        new Supplier<Boolean>() {
                            /**
                             * Gets a result.
                             *
                             * @return a result
                             */
                            @Override
                            public Boolean get() {
                                return diagonalCheckFromTopLeft(player);
                            }
                        }),
                    CompletableFuture.<Boolean>supplyAsync(
                        new Supplier<Boolean>() {
                            /**
                             * Gets a result.
                             *
                             * @return a result
                             */
                            @Override
                            public Boolean get() {
                                return diagonalCheckFromBottomLeft(player);
                            }
                        })
                );
            }

            log.info("\nLast Moves: {}\n", getLastMoves());

            results = futures
                    .parallelStream()
                    .parallel()
                    .anyMatch(
                        bool -> {
                            try {
                                if (bool.get()){
                                    return Boolean.TRUE;
                                }
                            } catch (InterruptedException | ExecutionException e) {
                                log.error(e.getLocalizedMessage());
                                e.printStackTrace();
                            }
                            return Boolean.FALSE;
                        }
                    );

        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }

        return results;

//            ExecutorService myWorkers = Executors.newFixedThreadPool(4);
//
//            CompletableFuture<Boolean> future
//                    = CompletableFuture.<Boolean>supplyAsync(() -> horizontalCheck(player), myWorkers)
//                    .thenApplyAsync(v -> verticalCheck(player), myWorkers)
//                    .thenApplyAsync(v -> diagonalCheckFromTopLeft(player), myWorkers)
//                    .thenApplyAsync(v -> diagonalCheckFromBottomLeft(player), myWorkers);
//            future.whenComplete((x, y) -> myWorkers.shutdownNow());
//            results = future.get();
/////////////////// Alternate implementation //////////////////
//                .filter(
//                        (bool -> {
//                            try {
//                                if (bool.get()) return Boolean.TRUE;
//                                else {
//                                    bool.cancel(true);
//                                }
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            } catch (ExecutionException e) {
//                                e.printStackTrace();
//                            }
//                            return true;
//                        })
//                )
//                .anyMatch((bool) -> {
//                    try {
//                        if (bool.get())
//                            return Boolean.TRUE;
//                    } catch (InterruptedException | ExecutionException e) {
//                        log.error(e.getLocalizedMessage());
//                        e.printStackTrace();
//                    }
//                    return Boolean.FALSE;
//                });

/////////////////// Alternate implementation //////////////////
//        return cf.parallelStream()
//                .filter((future) -> {
//                    try {
//                        if (future.get()) return true;
//                    } catch (InterruptedException | ExecutionException e) {
//                        log.error(e.getLocalizedMessage());
//                        e.printStackTrace();
//                    }
//                    return false;
//                } ).findFirst().isPresent();

    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $computerMark = this.getComputerMark();
        result = result * PRIME + ($computerMark == null ? 43 : $computerMark.hashCode());
        final Object $humanMark = this.getHumanMark();
        result = result * PRIME + ($humanMark == null ? 43 : $humanMark.hashCode());
        result = result * PRIME + Arrays.deepHashCode(this.getGameBoard());
        final Object $computerMoves = this.getComputerMoves();
        result = result * PRIME + ($computerMoves == null ? 43 : $computerMoves.hashCode());
        result = result * PRIME + this.getRound();
        result = result * PRIME + this.getComputerScore();
        result = result * PRIME + this.getHumanScore();
        result = result * PRIME + this.getDepth();
        result = result * PRIME + this.getBoardSize();
        final Object $lastMoves = this.getLastMoves();
        result = result * PRIME + ($lastMoves == null ? 43 : $lastMoves.hashCode());
        return result;
    }

    public String toString() {
        return "Board(" +
                "computerMark=" + this.getComputerMark() + ", " +
                "humanMark=" + this.getHumanMark() + ", " +
                "gameBoard=" + Arrays.deepToString(this.getGameBoard()) + ", " +
                "computerMoves=" + this.getComputerMoves() + ", " +
                "round=" + this.getRound() + ", " +
                "computerScore=" + this.getComputerScore() + ", " +
                "humanScore=" + this.getHumanScore() + ", " +
                "depth=" + this.getDepth() + ", " +
                "boardSize=" + this.getBoardSize() + ", " +
                "lastMoves=" + this.getLastMoves() + ")";
    }

//    public int checkGameBoard(Object player){
//        List<Object[]> array = Arrays.stream(getGameBoard())
//                .collect(Collectors.toList());
//        List<Object> arr = array.collect(Collectors.toList());
//        System.out.printf("\nPlayers '%s' Found: %s",
//                player,
//                arr.toString());
//        return arr.size();
//    }


    ///////////////////////////////////////////////////////////////////////////
    //  MAIN
    ///////////////////////////////////////////////////////////////////////////
     public static void main(String[] arg)
     {
         //////////////////////////////////////////////////////////////////////
         // TicTacToe t = new TicTacToe();
         // t.play();
         ///////////////////////////////////////////////////////////////////////
         Object[][] gBoard = {
                             {"O","O","","X"},
                             {"","","X","X"},
                             {"O","","","O"},
                             {"X","","X","O"}
                             };
         // Convert elements to strings and concatenate them, separated by commas
         log.info("\nBoard: {}", Arrays.stream(gBoard).collect(Collectors.toList()));

         Board bd1 = new Board(gBoard);
         bd1.setComputerMark("X");
         bd1.setHumanMark("O");
         bd1.setLastMove(new Point(0,0,bd1.boardSize));
         bd1.setGameBoard(bd1.setupGameBrd(bd1.getGameBoard()));
         System.out.printf("\nBoard 1");
         bd1.placeAMove(new Point(1,0,bd1.boardSize),"O");

         Board bd2 = new Board(bd1);
         System.out.printf("\nBoard 2");
//         bd1.setNext(bd2);
//         bd2.setPrevious(bd1);
         bd2.placeAMove(new Point(2,2,bd2.boardSize),"O");

         Board bd3 = new Board(bd2);
         System.out.printf("\nBoard 3");
//         bd2.setNext(bd3);
//         bd3.setPrevious(bd2);
         bd3.placeAMove(new Point(1,1,bd3.boardSize),"O");

         bd1.displayBoard();
         System.out.printf("\nIs \"O\" Winner: %s \n",
                 bd1.hasPlayerWon("O"));
         System.out.printf("\nIs \"X\" Winner: %s \n",
                 bd1.hasPlayerWon("X"));
         bd2.displayBoard();
         System.out.printf("\nIs \"O\" Winner: %s \n",
                 bd2.hasPlayerWon("O"));
         System.out.printf("\nIs \"X\" Winner: %s \n",
                 bd2.hasPlayerWon("X"));
         bd3.displayBoard();
         System.out.printf("\nIs \"O\" Winner: %s \n",
                 bd3.hasPlayerWon("O"));
         System.out.printf("\nIs \"X\" Winner: %s \n",
                 bd3.hasPlayerWon("X"));
//         System.out.printf("\nLength => bd1: %s, bd2: %s, bd3: %s \n",
//                 bd1.getLength(), bd2.getLength(), bd3.getLength());
         System.out.printf("\nEnd of Game TicTacToe......\n");
     }

/////////////////////   END OF CLASS Board  /////////////////////////
}
 
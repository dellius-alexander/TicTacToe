package com.tictactoe;

// import java.io.IOException;
// import java.util.logging.*;

/**
 * The Point class stores and represents the points (row and column)
 * of a single cell to be played on the game board during game play.
 * @version 2.2
 * @since 2019-04-12
 */
public class Point {
    // private static Logger logger = Logger.getLogger("com.tictactoe.Point");
    // private static String logFile = "tictactoe-"+java.time.LocalDate.now()+".%u.%g.log";
    // //Creating consoleHandler and fileHandler
    // private static Handler fH;    
    // private static Level logLevel = Level.WARNING;
    // The row, column and subscript of each cell.
    private int row;
    private int col;
    private int sub;
    private Point computerMove;
    private int boardSize;

    /**
     * The class default constructor.
     */
    public Point() {
        // try {
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
        // logger.info("Constructor init...");
    }
    public Point(int s) {
        // try {
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
        s -= 1;
        this.row = (s / this.boardSize) + 1;
        this.col = (s % this.boardSize) + 1;
        this.sub = s;
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+s+" | Row: "+this.row+" | Column: "+this.col);
        computerMove = new Point(row,col);
    }
    /**
     * The class constructor that accepts the row and column
     * integer value.
     * @param r
     * @param c
     */
    public Point(int r, int c) {
        // try {
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
        this.row = r-1;
        this.col = c-1;
        this.sub = (r - 1) * this.boardSize + (c - 1);
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
    }
    /**
     * Sets the board size
     */
    public void setBoardSize(int size){
        this.boardSize = size;
    }
    /**
     * Sets the board size
     */
    public void setBoardSize(char[][] gameBoard){
        this.boardSize = gameBoard.length;
    }
    /**
     * Sets the row and column values from the subscript
     * @param sub
     */
    public void setPoints(int sub) {
        this.row = (sub / this.boardSize) + 1;
        this.col = (sub % this.boardSize) + 1;
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+sub+" | Row: "+this.row+" | Column: "+this.col);
    }
    /**
     * The toString method returns a string representation of the object
     * row and column.
     * @return	A string representation of the object row and column.
     */
    @Override
    public String toString() {
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return "['" + this.row + "', '" + this.col + "']";
    }
    /**
     * The getRow method returns the value of the row.
     * @return	The value of the row.
     */
    public int getRow() {
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return this.row;
    }
    /**
     * The getRow method accepts the subscript value and returns
     * the row reference to a cell.
     * @param s		The subscript value
     * @return		The row value
     */
    public int getRow(int s) {
        this.sub = s;
        this.row = (sub / this.boardSize) + 1;
        this.col = (sub % this.boardSize) + 1;
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return this.row;
    }
    /**
     * The getCol method returns the value of the column.
     * @return	The value of the column.
     */
    public int getCol() {
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return col;
    }
    /**
     * The getCol method accepts a subscript value and
     * returns the column reference to a cell.
     * @param s		The subscript value
     * @return		The column reference to a cell
     */
    public int getCol(int s) {
        this.sub = s;
        this.row = (s / this.boardSize) + 1;
        this.col = (s % this.boardSize) + 1;
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return this.col;
    }
    /**
     * The getSub method returns the subscript reference to a cell.
     * @return		The subscript reference.
     */
    public int getSub() {
        // this.sub = (this.row-1) * this.boardSize + (this.col-1);
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return this.sub;
    }
    /**
     * The getSub method accepts the row and column and
     * returns the subscript reference to a cell.
     * @param r		The row value
     * @param c		The column value
     * @return		The subscript value
     */
    public int getSub(int row, int col) {
        this.row = row-1;
        this.col = col-1;
        this.sub = (row - 1) * this.boardSize + (col - 1);
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return this.sub;
    }
    /**
     * The getComputerMove returns the row and column
     * value of the subscript value for the computer
     * move when called.
     * @return	The object representing row and column
     */
    public Point getComputerMove() {
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return computerMove;
    }
    /**
     * The setComputerMove method accepts a point object
     * of row and column and assigns the value to
     * computerMove.
     * @param computerPoint	The object representing row and column
     */
    public void setComputerMove(Point computerPoint) {
        this.computerMove = computerPoint;
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
    }
        /**
     * The setComputerMove method accepts a point object
     * of row and column and assigns the value to
     * computerMove.
     * @param row	The row value
     * @param col	The column value
     */
    public void setComputerMove(int row, int col) {
        this.row = row-1;
        this.col = col-1;
        this.sub = (row - 1) * this.boardSize + (col - 1);
        this.computerMove = new Point(row, col);
        // logger.info("LINE: ["+getLineNumber()+"] | Subscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
    }
    /**
     * Get the current line number of executing thread
     * @return the integer value line number of executing thread
     */
    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }
    // ///////////////////////////////////// [ MAIN ] //////////////////////////////////////
    // public static void main(String[] args) { 
    //     Point p = new Point();
    //     p.getSub(5, 5);
    //     p.getCol(0);
    //     p.getCol();
    //     p.getRow(0);
    //     p.getRow();
    //     p.getSub(0, 0);
    // }
}

package com.hyfi.tictactoe;
// Imports
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

@Data
/**
 * The Point class stores and represents the points (row and column) of a single cell to be played on the game board during game play.
 * @version 2.2
 * @since 2019-04-12
 */
public class Point implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Point.class);
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
        // Log a simple INFO message.
        log.info("\nConstructor init...");
    }

    /**
     * Create a new point from an existing point and board size.
     * @param point {@linkplain Point}
     * @param boardSize the board size
     */
    public Point(Point point, int boardSize) {
        this.setRow(point.getRow());
        this.setCol(point.getCol());
        this.setSub(point.getSub());
        this.setBoardSize(boardSize);
        this.setComputerMove(point);

        log.info("\nConstructor init...");
    }
     /**
     * The subscript of the current Point
     * @param sub the subscript of the Point
     * @param boardSize the game board size
     */
    public Point(int sub,int boardSize) {
        // Log a simple INFO message.
        log.info("\nConstructor init...\nSub: {} | boardSize: {}",sub,boardSize);
        this.setBoardSize(boardSize);
        this.setRow((sub / this.boardSize ));
        this.setCol((sub % this.boardSize));
        this.setSub(sub);

        log.info("\nSubscript: {} | Row: {} | Column: {} ",sub,getRow(),getCol());
        computerMove = new Point(row,col,boardSize);
    }
     /**
     * The row and column of the current Point
     * integer value. Default board size is 9.
     * @param r the row of the Point
     * @param c the column of the point
     * @param boardSize  the game board size
     */
    public Point(int r, int c, int boardSize) {
        this.setRow(r);
        this.setCol(c);
        this.setSub((this.row * this.boardSize + (this.col)));
        this.setBoardSize(boardSize);
        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
    }



    /**
     * Sets the board size
     * @param size the size of the game board
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
     * Get the board size
     * @return the game board size
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * Sets the row and column values from the subscript
     * @param sub the subscript of the Point
     */
    public void setPoints(int sub) {
        this.row = (sub / this.boardSize);
        this.col = (sub % this.boardSize);
        log.info("\nSubscript: "+sub+" | Row: "+this.row+" | Column: "+this.col);
    }


    /**
     * The toString method returns a string representation of the object
     * row and column.
     * @return	A string representation of the object row and column.
     */
    @Override
    public String toString() {
        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return "['" + this.row + "', '" + this.col + "']";
    }

    /**
     * Set the row
     * @param row the row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * The getRow method returns the value of the row.
     * @return	The value of the row.
     */
    public int getRow() {
        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
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
        this.row = (s / this.boardSize);
        this.col = (s % this.boardSize);
        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return this.row;
    }

    /**
     * Set the column
     * @param col the column
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * The getCol method returns the value of the column.
     * @return	The value of the column.
     */
    public int getCol() {
        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
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
        this.row = (s / this.boardSize);
        this.col = (s % this.boardSize);

        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return this.col;
    }

    /**
     * Set the subscript
     * @param sub the subscript
     */
    public void setSub(int sub) {
        this.sub = sub;
    }

    /**
     * The getSub method returns the subscript reference to a cell.
     * @return		The subscript reference.
     */
    public int getSub() {
        // this.sub = (int) ((this.row) * (Math.sqrt(boardSize)) + (this.col));
        log.info("\nSubscript: {} | BoardSize: {} | Row: {} | Column: {}",this.sub,this.boardSize,this.row,this.col);
        return (sub == (((this.row) * (this.boardSize) + (this.col))) ? sub : ((this.row) * (this.boardSize) + (this.col)) );

    }
    /**
     * The getSub method accepts the row and column and
     * returns the subscript reference to a cell.
     * @param row		The row value
     * @param col		The column value
     * @return		The subscript value
     */
    public int getSub(int row, int col) {
        this.row = row;
        this.col = col;
        this.sub =  ((this.row) * (this.boardSize) + (this.col));
        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
        return this.sub;
    }
    /**
     * The getComputerMove returns the row and column
     * value of the subscript value for the computer
     * move when called.
     * @return	a {@linkplain Point}
     */
    public Point getComputerMove() {
        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
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
        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
    }
        /**
     * The setComputerMove method accepts a point object
     * of row and column and assigns the value to
     * computerMove.
     * @param row	The row value
     * @param col	The column value
     */
    public void setComputerMove(int row, int col) {
        this.row = row;
        this.col = col;
        this.sub = ((this.row) * (this.boardSize) + (this.col));
        this.computerMove = new Point(row, col,boardSize);
        log.info("\nSubscript: "+this.sub+" | Row: "+this.row+" | Column: "+this.col);
    }

    // /**
    //  * Get the current line number of executing thread
    //  * @return the integer value line number of executing thread
    //  */
    // public static int getLineNumber() {
    //     return Thread.currentThread().getStackTrace()[2].getLineNumber();
    // }
     ///////////////////////////////////// [ MAIN ] \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//     public static void main(String[] args) {
//         Point p = new Point();
//         p.setBoardSize(16);
//         logger.info("{}",p.getSub(1, 0));
//         logger.info("{}",p.getSub(1, 1));
//         logger.info("{}",p.getSub(1, 2));
//         logger.info("{}",p.getCol(15));
//         logger.info("{}",p.getCol(4));
//         logger.info("{}",p.getCol(5));
//         logger.info("{}",p.getRow(3));
//         logger.info("{}",p.getRow(4));
//         logger.info("{}",p.getRow(5));
//
//     }
}

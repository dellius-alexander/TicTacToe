

/**
 * The Point class stores and represents the points (row and column)
 * of a single cell to be played on the game board during game play.

 * @version 2.2
 * @since 2019-04-12
 */
public class Point {
    // The row, column and subscript of each cell.
    private int row;
    private int col;
    private int sub;
    private Point computerMove;

    /**
     * The class default constructor.
     */
    public Point() {
    }
    public Point(int s) {
        this.row = s / 3 + 1;
        this.col = s % 3 + 1;
        this.sub = s;
    }
    /**
     * The class constructor that accepts the row and column
     * integer value.
     * @param r
     * @param c
     */
    public Point(int r, int c) {
        this.row = r;
        this.col = c;
        this.sub = (row - 1) * 3 + col - 1;
    }

    public void setPoints(int sub) {
        this.row = sub / 3 + 1;
        this.col = sub % 3 + 1;
    }
    /**
     * The toString method returns a string representation of the object
     * row and column.
     * @return	A string representation of the object row and column.
     */
    @Override
    public String toString() {
        return "[" + row + ", " + col + "]";
    }
    /**
     * The getRow method returns the value of the row.
     * @return	The value of the row.
     */
    public int getRow() {
        return row;
    }
    /**
     * The getRow method accepts the subscript value and returns
     * the row reference to a cell.
     * @param s		The subscript value
     * @return		The row value
     */
    public int getRow(int s) {
        this.sub = s;
        return sub / 3 + 1;
    }
    /**
     * The getCol method returns the value of the column.
     * @return	The value of the column.
     */
    public int getCol() {
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
        return sub % 3 + 1;
    }
    /**
     * The getSub method returns the subscript reference to a cell.
     * @return		The subscript reference.
     */
    public int getSub() {
        return (row - 1) * 3 + col - 1;
    }
    /**
     * The getSub method accepts the row and column and
     * returns the subscript reference to a cell.
     * @param r		The row value
     * @param c		The column value
     * @return		The subscript value
     */
    public int getSub(int r, int c) {
        this.row = r;
        this.col = c;
        return (row - 1) * 3 + col - 1;
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
     * @param computerPoint	The object representing row and column
     */
    public void setComputerMove(Point computerPoint) {
        this.computerMove = computerPoint;
    }

}

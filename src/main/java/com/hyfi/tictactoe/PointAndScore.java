package com.hyfi.tictactoe;
// Logging depencencies
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Class dependencies
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
// import java.util.logging.*;
/**
 * The PointAndScore class stores and represents the points (row and column) of a single cell and score to be played on the game board during game play. This class writes the game statistical data to a text file for analysis.
 * @version 2.2
 * @since 2019-04-12
 */

public class PointAndScore {
    private static final Logger logger = LoggerFactory.getLogger(PointAndScore.class);
    // The score represents the value assigned to player move
    // after a winner is determined during AI simulation
    private int score;
    private int roundToPlay;
    private int depth;
    private Point point;
    private int iteration;
    private int max;
    private Board board;
    /**
     * The GameData.txt file will be created the root folder
     * and game data will be written to this file.
     */
    public static final String DATA_FILE = "data/GameData.txt";


    /**
     * The default constructor
     */
    public PointAndScore() {
       logger.debug("Constructor init...");
    }
    /**
     * This constructor accepts the game board
     * @param board the game board
     */
    public PointAndScore(Board board) {

        this.board = board;         
    }
    /**
     * The setMax method tracks the max values returned during iteration
     * of minimax algorithm.
     * @param max the max value
     */
    public void setMax(int max) {
        this.max = max;
    }
    /**
     * The getMax method reutrns the max values returned during iteration
     * of minimax algorithm.
     * @return max	The max value
     */
    public int getMax() {
        return max;
    }
    /**
     * The setPoint method assigns the point value to
     * the field point
     * @param point The field point value
     */
    public void setPoint(Point point) {
        this.point = point;
    }
    /**
     * The getPoint method assigns the point value to
     * the field point
     * @return 	The field point value
     */
    public Point getPoint() {
        return point;
    }
    /**
     * The setRound method assign the value of the current round
     * being played to the round field
     * @param 	t	The value to assign to round field
     */
    public void setRound(int t) {
        this.roundToPlay = t;
    }
    /**
     * The getRound method returns the value of the current turn
     * in game play
     * @return		The value of current round at play during the game
     */
    public int getRound() {
        return roundToPlay;
    }
    /**
     * The setScore method assigns the value to score field.
     * @param score		The value to assign to score field.
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     * The getScore method returns the value to the evaluated score
     * of player simulated moves
     * @return		The value of the score of player simulated move
     */
    public int getScore() {
        return score;
    }
    /**
     * Sets the value of available cells left in the list to be played or tested
     * @param depth the available cells left to be played
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    /**
     * Gets the value of available cells left in the list to be played or tested
     * @return the available cells left to be played
     */
    public int getDepth() {
        return depth;
    }
    /**
     * Tbe last move played that resulted in a win
     * @param round  the current round of game play
     * @param point the Point played
     * @param score the value representing a win/loose/draw
     * @param aCells the available cells left to be played
     * @param value the maximum value returned compared to other wins
     * @param depth the available cells left to be played
     * @param i the current iteration of game play
     * @throws IOException throw IOException on file access
     */
    public void terminalWinner(int round, Point point, int score, int aCells, int value, int depth, int i) throws IOException  {
        if (score == 1) {
            FileWriter fwriter = new FileWriter(DATA_FILE, true);
            PrintWriter outputFile = new PrintWriter(fwriter);
            outputFile.println("AI Computer Win Returned: On Round: " + round + " | Cells Remaining: " +
                    aCells + " | Depth: " + depth + " | Score: " + score + " | Max value: " +
                    value + " | Iteration: " + i + " |  Point: " + point);
            outputFile.close();
        }
        else if (score == -1) {
            FileWriter fwriter = new FileWriter(DATA_FILE, true);
            PrintWriter outputFile = new PrintWriter(fwriter);
            outputFile.println("AI Human Win  Returned: On Round: " + round + " | Cells Remaining: " +
                    aCells + " | Depth: " + depth + " | Score: " + score + " | Min value: " +
                    value +  " | Iteration: " + i + " |  Point: " + point);
            outputFile.close();
        }
        else if (score == 0){
            FileWriter fwriter = new FileWriter(DATA_FILE, true);
            PrintWriter outputFile = new PrintWriter(fwriter);
            outputFile.println("Game is a DRAW Returned: On Round: " + round + " | Cells Remaining: " +
                    aCells + " | Depth: " + depth + " | Score: " + score + " | Max/Min value: " +
                    value  + " | Iteration: " + i + " |  Point: " + point);
            outputFile.close();
        }

    }
    /**
     * The best moves played at game play that resulted in a win
     * @param point the Point played
     * @param score the value representing a win/loose/draw
     * @param aCells the available cells left to be played
     * @param i the current iteration of game play
     * @param max the best value compared to other game outcomes
     * @param depth the available cells left to be played
     * @param round  the current round of game play
     * @throws IOException IOException throw IOException on file access
     */
    public void printBestScore(Point point, int score, int aCells, int i, int max, int depth, int round) throws IOException {
        this.point = point;
        this.score = score;
        this.depth = depth;
        this.roundToPlay = round;
        this.max = max;
        this.iteration = i;

        FileWriter fwriter = new FileWriter(DATA_FILE, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.println("Best Moves Returned: On Round: " + round + " | Cells Remaining: " + aCells +
                " | Depth: " + depth + " | Score: " + score + " | Max value: " + max + " | Iteration: " + iteration +
                " |  Point: " + point);
        outputFile.close();
    }
    /**
     * The printComputerMove method prints statistical data for simulated move
     * played during game iteration depth
     * @param round  the current round of game play
     * @param score the value representing a win/loose/draw
     * @param point the Point played
     * @param depth the available cells left to be played
     * @param iteration the current iteration of game play
     * @param max the best value compared to other game outcomes
     * @throws IOException IOException throw IOException on file access
     */
    public void captureComputerMove(int round, int score, Point point, int depth, int iteration, int max ) throws IOException {
        FileWriter fwriter = new FileWriter(DATA_FILE, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.println("Computer Turn: On Round: " + round + " | Depth: " +
                depth + " | Score: " + score + " | Max value: " + max + " | Iteration: " + iteration + " | Point: " + point);
        outputFile.close();
    }
    /**
     * The printHumanMove method prints statistical data for simulated move
     * played during game iteration depth
     * @param round  the current round of game play
     * @param score the value representing a win/loose/draw
     * @param point the Point played
     * @param depth the available cells left to be played
     * @param iteration the current iteration of game play
     * @param min the worst value compared to other game outcomes
     * @throws IOException IOException throw IOException on file access
     */
    public void captureHumanMove(int round, int score, Point point, int depth, int iteration, int min) throws IOException {
        FileWriter fwriter = new FileWriter(DATA_FILE, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.println("***Human Turn: On Round: " + round + " | Depth: " +
                depth + " | Score: " + score + " | Max value: " + min + " | Iteration: " + iteration + " | Point: " + point);
        outputFile.close();
    }
    /**
     * The printResults method tracks and prints statistical value
     * of minimax decision after max depth has been evaluated for
     * the computer move.  The method captures and prints the final
     * decision returned best move for the computer to play.  This
     * method paired with PointAndScore class helps to evaluate
     * game performance.
     * @see PointAndScore
     * @throws IOException IOException throw IOException on file access
     */
    public void printResults() throws IOException {

        FileWriter fwriter = new FileWriter(DATA_FILE, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.println("********************  Computer Move: On Round: " + 	board.getRound() +  "  | Depth: " + board.getDepth() +
                "  |  Score: " + board.getComputerScore() + " | Max value: " + getMax() + "  |  Point: " +
                board.getComputerMove() + "  **************");
        outputFile.close();
    }
    // /**
    //  * Get the current line number of executing thread
    //  * @return the integer value line number of executing thread
    //  */
    // public static int getLineNumber() {
    //     return Thread.currentThread().getStackTrace()[2].getLineNumber();
    // }

}

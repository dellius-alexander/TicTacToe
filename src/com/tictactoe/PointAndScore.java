package com.tictactoe;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
// import java.util.logging.*;
/**
 * The PointAndScore class stores and represents the points (row and column)
 * of a single cell and score to be played on the game board during game play.
 * This class writes the game statistical data to a text file for analysis.

 * @version 2.2
 * @since 2019-04-12
 */

public class PointAndScore {
    // // Setting up logging to the console and file
    // private static Logger logger = Logger.getLogger("com.tictactoe.PointAndScore");
    // private static String logFile = "tictactoe-"+java.time.LocalDate.now()+".%u.%g.log";
    // //Creating consoleHandler and fileHandler
    // private static Handler fH;    
    // private static Level logLevel = Level.WARNING;
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
    public static final String DATA_FILE = "GameData.txt";


    /**
     * The default constructor
     */
    public PointAndScore() {
        // // Default constructor
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
    /**
     * This constructor accepts the game board
     */
    public PointAndScore(Board board) {
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
        this.board = board;         
    }
    /**
     * The setMax method tracks the max values returned during iteration
     * of minimax algorithm.
     * @param max
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
     * @param point		The field point value
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

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }
    /**
     * The terminalWinner method prints the winner of the current iteration.
     * @throws IOException
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
     * @throws IOException
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
     * @throws IOException
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
     * @throws IOException
     */
    public void printResults() throws IOException {

        FileWriter fwriter = new FileWriter(DATA_FILE, true);
        PrintWriter outputFile = new PrintWriter(fwriter);
        outputFile.println("********************  Computer Move: On Round: " + 	board.getRound() +  "  | Depth: " + board.getDepth() +
                "  |  Score: " + board.getComputerScore() + " | Max value: " + getMax() + "  |  Point: " +
                board.getComputerMove() + "  **************");
        outputFile.close();
    }
    /**
     * Get the current line number of executing thread
     * @return the integer value line number of executing thread
     */
    public static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

}

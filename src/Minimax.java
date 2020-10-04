
/**
 * The Minimax class implements the Minimax heuristic algorithm to look
 * ahead and determine the best move for computer to make during game
 * play.  It returns the best move to the game board on the computers turn.

 * @version 2.2
 * @since 2019-04-12
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Minimax {

    private Board board;
    private PointAndScore printScore = new PointAndScore();

    public Minimax(Board board) {
        this.board = board;
    }
    /**
     * The setBestMove method returns the all moves that end
     * in a win for the computer or a draw
     * @param point		The point returned from a win
     * @param score		The win or draw score returned
     * @param avilCells	Available cells of current depth
     * @param i			The current index to be captured
     * @param max		The best play returned
     * @param depth		The current iteration of game play
     * @throws IOException
     */
    public void setBestMove(Point point, int score, int avilCells, int i, int max, int depth) throws IOException {
        // If current iteration depth ends in a draw or win for computer return
        // return point at "0" depth.
        if (depth == 0)
            if (score > 0 && max > 0) {
                board.setComputerMove(point);
                printScore.printBestScore(point, score, avilCells, i, max, depth, board.getRound());
                return;
            }
            else if (score == 0 && max == 0) {
                board.setComputerMove(point);
                printScore.printBestScore(point, score, avilCells, i, max, depth, board.getRound());
                return;
            }
        return;
    }
    /**
     * The aiComputerMove method simulates the AI computer player
     * during minimax iteration
     * @param point		The point to be played
     * @param depth		The current iteration of game play
     * @return			The winning score of current depth
     * @throws IOException
     */
    public int aiComputerMove(Point point, int depth, int i, List<Point> availableCells) throws IOException {

        board.placeAMove(point, board.getComputerMark());
        if (board.hasPlayerWon(board.getComputerMark())) {
            board.setComputerScore(1);		// Returns value to "currentScore"
            // Captures statistical data of possible decision tree return best moves
            printScore.captureComputerMove(board.getRound(), board.getComputerScore(), point, depth, i, 1);
        }
        else if (availableCells.isEmpty()) {
            board.setComputerScore(0);		// Returns value to "currentScore"
            // Captures statistical data of AI Computer draw after play is made
            printScore.captureComputerMove(board.getRound(), board.getComputerScore(), point, depth, i, 0);
        }
        // Evaluates game state after a move is placed on the game board and returns
        // 0 for draw or 1 for AI computer win or -1 for AI human win
        return (minimax(depth, board.getHumanMark()));
    }
    /**
     * The aiHHumanMove method simulates the AI human player
     * during minimax iteration
     * @param point		The point to be played
     * @param depth		The current iteration of game play
     * @return			The winning score of current depth
     * @throws IOException
     */
    public int aiHumanMove(Point point, int  depth, int i, List<Point> availableCells) throws IOException {

        board.placeAMove(point, board.getHumanMark());
        if (board.hasPlayerWon(board.getHumanMark())) {
            board.sethumanScore(-1);	// Returns value to "currentScore"
            // Captures statistical data of decision upon AI human win
            printScore.captureHumanMove(board.getRound(), board.getComputerScore(), point, depth, i, -1);
        }
        else if (availableCells.isEmpty()) {
            board.sethumanScore(0);		// Returns value to "currentScore"
            // Captures statistical data of AI human draw after play is made
            printScore.captureHumanMove(board.getRound(), board.getComputerScore(), point, depth, i, 0);
        }
        // Evaluates game state after a move is placed on the game board and returns
        // 0 for draw or 1 for AI computer win or -1 for AI human win
        return (minimax(depth, board.getComputerMark()));
    }
    /**
     * The minimax method is an AI algorithm that simulated the possible moves
     * that can be made at the current game state and returns the best move
     * for the computer to play. It tests all possible moves remaining
     * on the board for each iteration and returns the best possible
     * move for the computer for play towards a win or draw.
     * @param depth		How many moves ahead to loop through.
     * @param player	THe mark of the player during each iteration.
     * @return			The best possible move for computer to take.
     * @throws IOException
     */
    public int minimax (int depth, char player) throws IOException  {
        // Captures all available cells into an array list.
        List<Point> availableCells = new ArrayList<>(board.getAvailableCells());
        if (board.hasPlayerWon(board.getComputerMark()))
            return 1;  		// Returns value to "currentScore"
        if (board.hasPlayerWon(board.getHumanMark()))
            return -1;		// Returns value to "currentScore"
        if (availableCells.isEmpty())
            return 0;		// Returns value to "currentScore"
        // Set min value to +INFINITY
        int min = Integer.MAX_VALUE;
        // Set max value to -INFINITY
        int max = Integer.MIN_VALUE;
        // Iterates through remaining moves of game board to maximum
        // depth of moves remaining on the board.
        for (int i = 0; i < availableCells.size(); i++) {
            board.setDepth(availableCells.size());
            // The cell/point value to be tested for all possible outcomes
            Point point = availableCells.get(i);
            // Plays a move for computer AI
            if (player == board.getComputerMark()) {
                int currentScore = aiComputerMove(point, availableCells.size(), i, availableCells);
                // Returns the maximum value for the best move of all iterations
                max = Math.max(currentScore, max);
                printScore.setMax(max);
                setBestMove(point, currentScore, availableCells.size(), i, max, depth);
                printScore.terminalWinner(board.getRound(),point, currentScore, availableCells.size(), max, depth, i);
                // If AI computer wins playing index "i" as the next move in the given iteration. The index
                // is reset and added to the pool of available cells as the next index "i" is tested in the loop.
                if (currentScore == 1 || currentScore == 0 || currentScore == -1) {
                    board.getGameBoard()[board.getSub(point.getRow(), point.getCol())] = board.getNoPlayer();
                    if (max == 1 || currentScore == 1) {
                        board.getGameBoard()[board.getSub(point.getRow(), point.getCol())] = board.getNoPlayer();
                        break;
                    }
                }
                // Plays a move for human AI
            } else if (player == board.getHumanMark()) {
                int currentScore = aiHumanMove(point, availableCells.size(), i, availableCells);
                // Returns the minimum value for the best move of all iterations
                min = Math.min(currentScore, min);
                printScore.terminalWinner(board.getRound(),point, currentScore, availableCells.size(), min, depth, i);
                // If AI computer wins playing index "i" as the next move in the given iteration. The index
                // is reset and added to the pool of available cells as the next index "i" is tested in the loop.
                if (currentScore == 1 || currentScore == 0 || currentScore == -1) {
                    board.getGameBoard()[board.getSub(point.getRow(), point.getCol())] = board.getNoPlayer();
                    if (min == -1 || currentScore == -1) {
                        board.getGameBoard()[board.getSub(point.getRow(), point.getCol())] = board.getNoPlayer();
                        break;
                    }
                }
            }
            board.getGameBoard()[board.getSub(point.getRow(), point.getCol())] = board.getNoPlayer();
        }
        return player == board.getComputerMark() ? max : min;
    }
}

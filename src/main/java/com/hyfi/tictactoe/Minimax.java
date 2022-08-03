package com.hyfi.tictactoe;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.RecursiveTask;

/**
 * The Minimax class implements the Minimax heuristic search
 * algorithm to look ahead and determine the best move for computer to
 * make during game play.  It returns/back-up to root the best move
 * to the game board on the computers turn.
 * Elements:
 * <ol>
 * <li>S<sub>0</sub>: The initial game board state, which specifies how
 * the game board is set up at the start.</li>
 * <li>To-Move(s): The player whose turn it is to move in state "s".</li>
 * <li>Actions(s): The set of legal moves/available cells in game board state "s".</li>
 * <li>Results(s,a): The transition model, which defines the game board state
 * resulting from taking action a or playing a move on the game board resulting from
 * a win, loss or draw.
 * in state s.</li>
 * </ol>
 * @version 2.2
 * @since 2019-04-12
 */
public class Minimax implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(Minimax.class);
//    private PointAndScore printScore;
    private Map<Integer,Point> bestmoves;
    private Long systemTime;
    private int index;
    private Board gameBoard;
    private Point computerMove;
//    private static int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;


    /**
     * Initialize minimax algorithm
     */
    public Minimax(Board gameBoard) {
        log.info("\n{} init...", Minimax.class);
        this.gameBoard = gameBoard;
        this.systemTime = System.currentTimeMillis();
//        System.out.printf("\nStart Time: %s",systemTime);
        this.index = 0;
//        this.printScore = new PointAndScore();
        this.bestmoves =  new HashMap<>();
    }
    /**
     * Initialize minimax algorithm
     */
    public Minimax(Board gameBoard,Object oppTokn, Object userToken) {
        log.info("\n{} init...", Minimax.class);
        this.gameBoard = gameBoard;
        this.systemTime = System.currentTimeMillis();
//        System.out.printf("\nStart Time: %s",systemTime);
        this.index = 0;
//        this.printScore = new PointAndScore();
        this.bestmoves =  new HashMap<>();
    }

    public Point getComputerMove(){
        return this.computerMove;
    }
    public Map<Integer,Point> getBestmoves(){return this.bestmoves;}

    /**
     * The setBestMove method returns the all moves that end
     * in a win for the computer or a draw.
     * @param point		The point returned from a win
     * @param score		The win or draw score returned
     * @param availableCells	Available cells of current depth
     * @param i			The current index to be captured
     * @param max		The best play returned
     * @param depth		The current iteration of game play
     */
    public boolean setBestMove(Point point, int score, int availableCells, int i, int max, int depth) {
        log.info("\nBest Move: {} | Index: {} | Max: {} | Score: {}\n",
                point,index, max, score);
        try {

            // check score and max for best point
            if (score >= 1 && max >= 1) {
                this.gameBoard.setComputerMove(max, point);
                this.bestmoves.put(max, point);
                this.computerMove = point;
                log.info("\nRound: {} | Point: {} | Score: {} | Available Cells: {} | Iteration: {} @ Depth: {} | MAX GAME RESULTS: {}",
                        gameBoard.getRound(), point, score, availableCells, i, depth, max);
                index++;
                return true;
            } else if (score <= -1 && max <= -1) {
                this.gameBoard.setComputerMove(max, point);
                this.bestmoves.put(max, point);
                this.computerMove = point;
                log.info("\nRound: {} | Point: {} | Score: {} | Available Cells: {} | Iteration: {} @ Depth: {} | MAX GAME RESULTS: {}",
                        gameBoard.getRound(), point, score, availableCells, i, depth, max);
                index++;
                return true;
            } else {
                this.gameBoard.setComputerMove(max, point);
                this.bestmoves.put(max, point);
                this.computerMove = point;
                log.info("\nRound: {} | Point: {} | Score: {} | Available Cells: {} | Iteration: {} @ Depth: {} | MAX GAME RESULTS: {}",
                        gameBoard.getRound(), point, score, availableCells, i, depth, max);
                index++;
                return true;
            }

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            log.error("\nUnable to set computer move...\n"+e.getMessage());
            log.error("\nRound: {} | Point: {} | Score: {} | Available Cells: {} | Iteration: {} @ Depth: {} | MAX GAME RESULTS: {}",
                    gameBoard.getRound() , point, score, availableCells, i, depth, max);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * The Score function takes a total of the matching players
     * tokens on the game board, then either subtracts from
     * the square of the board size. The best score is defined as the
     * least amount of moves leading to a winning state for either player.<br>
     * E.g. +AI computer score = (board_size^2) - (total count of tokens)<br>
     * OR   -AI human score = (total count of tokens) - (board_size^2)
     * @param playerToken the player token
     * @param board the game board
     * @return the score for the current state of the board
     */
    public Integer score(Object playerToken, Board board){
        int cnt = 0; // initialize our counter
        // count the player token
        for (int i = 0; i < board.getBoardSize(); i++)
        {
            for (int j = 0; j < board.getBoardSize(); j++)
            {
                if( playerToken.equals(gameBoard.getComputerMark()) && board.getGameBoard()[i][j].equals(playerToken) )
                {
                    cnt++;
                }
                else if( playerToken.equals(gameBoard.getHumanMark()) && board.getGameBoard()[i][j].equals(playerToken) )
                {
                    cnt++;
                }
            }
        }
        // calculate the score for the board state
        int score = 0;
        if ( cnt != 0 && playerToken.equals(board.getComputerMark()) ){
            score = ( (int) Math.pow(board.getBoardSize(),2) ) - cnt;
        }
        else if ( cnt != 0 && playerToken.equals(board.getHumanMark()) ){
            score =  cnt - (int) Math.pow(board.getBoardSize(),2);
        }
//        System.out.printf("\n%s Score: %s", playerToken, score);
//        return ( (int) Math.pow(board.getBoardSize(),2) ) - cnt;
        return score;
    }

    public Integer bestMove(int depth, int max, Map<Integer,Point> moves, Board board, Object turn){
        // make a copy of the board
        board = new Board(board);
        int move = (int) moves.keySet().toArray()[0];
        // base case
        if (moves.size() == 1)
        {
            return move;
        }

        if (turn == board.getComputerMark())
        {
            // test for winning state for computer first then remove move
            Set<Integer> keys = moves.keySet();
            for (int i : keys)
            {
                board.placeAMove(moves.get(i), turn);
                if (board.hasPlayerWon(turn))
                {
                    board.removeLastMove();
                    return i;
                }
                board.removeLastMove();
//                max = Math.max(bestMove(
//                        depth + 1,
//                        max,
//                        moves,
//                        board,
//                        turn), max);
            }
            turn = board.getHumanMark();
        }

        if (turn == board.getHumanMark())
        {
            // test for winning state for computer first then remove move
            Set<Integer> keys = moves.keySet();
            for (int i : keys)
            {
                board.placeAMove(moves.get(i), turn);
                if (board.hasPlayerWon(turn))
                {
                    board.removeLastMove();
                    return i;
                }
                board.removeLastMove();


            }
        }

        if (max == Integer.MIN_VALUE){
            max = (int) moves.keySet().toArray()[moves.size() - 1];
        }
        System.out.printf("\nMaximum: %s", max);
        return max;
    }



    /**
     * The minimax method is an AI algorithm that simulated the possible moves
     * that can be made at the current game state and returns the best move
     * for the computer to play. It tests all possible moves remaining
     * on the board for each iteration and returns the best possible
     * move for the computer to play towards a win or a draw.
     * @param depth		How many moves ahead to loop through.
     * @param player	THe mark of the player during each iteration.
     * @param board     the game board
     * @param alpha     the maximizing player score
     * @param beta      the minimizing player score
     * @return			The best possible move for computer to take.
     * @throws IOException throw exectption for file I/O event
     */
    public synchronized int minimax (int depth, Object player, Board board, int alpha, int beta) {
        ///////////////////////////////////////////////////////////////////////
        // initialize clock for runtime evaluation
        if (depth == 0) {this.systemTime = System.currentTimeMillis();}
        Random rand = new Random();
        ///////////////////////////////////////////////////////////////////////
        // Get all available positions/cells at the current depth
        List<Point> availableCells = board.getAvailableCells();
        ///////////////////////////////////////////////////////////////////////
        try {

            // check for a terminal state and return heuristic score
            // +computer_score = (board_size^2) - (number of player token on the board)
            // -human_score = (number of player token on the board) - (board_size^2)
            ///////////////////////////////////////////////////////////////////
            if ( board.hasPlayerWon(board.getComputerMark()) ) {
                return score(board.getComputerMark(), board);   // Returns value to "currentScore" on computer win
            }
            else if ( board.hasPlayerWon(board.getHumanMark()) ) {
                return score(board.getHumanMark(), board);    // Returns value to "currentScore" on human win
            }
            else if ( availableCells.isEmpty() ) {
                return 0;    // Returns value to "currentScore" on draw
            }
            ///////////////////////////////////////////////////////////////////
            // Simulates Computer AI game player
            if (player.equals(board.getComputerMark()))
            {
                int i = 0;
                ///////////////////////////////////////////////////////////////////
                // Iterates through remaining moves/node of game board to maximum
                // depth of moves remaining on the board.
                for( Point point : availableCells  ) // get the next position
                {
//                    System.out.printf("\nALPHA: %s | BETA: %s",alpha, beta);

                    log.info("\nDepth: {} | Point: {}", depth, point);
                    // place the next point
                    board.placeAMove(point, board.getComputerMark());

                    // capture stats
                    log.info("\n##################### {} PLAYS ######################", player);
                    log.info("\n{}",board.displayBoard(board.getGameBoard()));
                    log.info("\nAlpha Stats => \n\tIndex Position: {} \n\tAlpha: {} \n\tBeta: {} " +
                                    "\n\tPLAYER: {} \n\tPoint: {} \n\tDepth: {} \n\tAvailable Cells CNT: {}" +
                                    "\n\tAvailable Cells: {} \n\tLast Moves: {} \n\tComputer Moves: {} " +
                                    "\n\tRuntime Clock: {} ms\n",
                            i, alpha, beta, player, point, depth, board.getAvailableCells().size(),
                            board.getAvailableCells(), board.getLastMoves(), board.getComputerMoves(),
                            (System.currentTimeMillis() - systemTime));
                    log.info("\n####################################################");

                    // get the score for terminal state object
                    int currentScore = minimax(
                            depth + 1,
                            board.getHumanMark(),
                            board,
                            alpha, // capture best move as MAX between bounds of  [ 0 - +Infinity ]
                            beta
                    );
                    // Returns the maximum value for the best move of all iterations
                    alpha = Math.max( alpha, currentScore );

                    // capture stats
                    log.info("\n##################### {} WINS #######################", player);
                    log.info("\n{}",board.displayBoard(board.getGameBoard()));
                    log.info("\nAlpha Win Stats => \n\tIndex Position: {} \n\tAlpha: {} \n\tBeta: {} \n\tCurrent Score: {} " +
                                    "\n\tPLAYER: {} \n\tPoint: {} \n\tDepth: {} \n\tAvailable Cells CNT: {}" +
                                    "\n\tAvailable Cells: {} \n\tLast Moves: {} \n\tComputer Moves: {} " +
                                    "\n\tRuntime Clock: {} ms\n",
                            i, alpha, beta, currentScore, player, point, depth, board.getAvailableCells().size(),
                            board.getAvailableCells(), board.getLastMoves(), board.getComputerMoves(),
                            (System.currentTimeMillis() - systemTime));
                    log.info("\n####################################################");

                    // Checks and/or updates for winning position at the end of game play or during backup
                    // to root node, defined as depth == 0.
                    if ( depth == 0 && currentScore >= 0 )
                    {
                        // capture the best moves for each best case modeled at game play
                        // using recursive maximum score to learn what the best moves are
                        // during this iteration
                        setBestMove(
                                point,
                                currentScore,
                                availableCells.size(),
                                i,
                                alpha,
                                depth
                        );

                    }
                    // alpha beta pruning: prune current branch from game tree
                    if (alpha >= beta)
                    {
                        log.info("\n################### Alpha/Beta #####################");
                        log.info("\n{}",board.displayBoard(board.getGameBoard()));
                        log.info("\nALPHA => Alpha/Beta Pruning: \n\tIndex Position: {} \n\tAlpha: {} \n\tBeta: {} " +
                                        "\n\tCurrent Score: {} \n\tPLAYER: {} \n\tPoint: {} \n\tDepth: {} " +
                                        "\n\tAvailable Cells CNT: {} \n\tAvailable Cells: {} \n\tLast Moves: {} " +
                                        "\n\tComputer Moves: {} \n\tIndexed Point: {} \n\tRuntime Clock: {} ms\n",
                                i, alpha, beta, currentScore, player, point, depth, board.getAvailableCells().size(),
                                board.getAvailableCells(), board.getLastMoves(), board.getComputerMoves(),
                                availableCells.get(i), (System.currentTimeMillis() - systemTime));
                        log.info("\n####################################################");
                        // remove the last move played after game completes
                        board.getGameBoard()[point.getRow()][point.getCol()] = board.getNoPlayer();
                        // return winning alpha score and point
                        break;
                    }

                    // remove the last move played after game completes
                    board.getGameBoard()[point.getRow()][point.getCol()] = board.getNoPlayer();
                    // create a new board after a terminal state has been reached
                    board =  new Board(board);
                    i++;
                }
            }
            ///////////////////////////////////////////////////////////////////
            // Simulates game user human player
            if ( player.equals(board.getHumanMark()) )
            {
                int i = 0;
                ///////////////////////////////////////////////////////////////
                // Iterates through remaining moves of game board to maximum
                // depth of moves remaining on the board.
                for( Point point : availableCells ) // get the next position
                {
//                    System.out.printf("\nALPHA: %s | BETA: %s",alpha, beta);

                    log.info("\nDepth: {} | Point: {}", depth, point);
                    // place a move
                    board.placeAMove(point, board.getHumanMark());

                    // capture stats
                    log.info("\n###################### {} PLAY ######################", player);
                    log.info("\n{}",board.displayBoard(board.getGameBoard()));
                    log.info("\nBETA Stats => \n\tIndex Position: {} \n\tAlpha: {} \n\tBeta: {} " +
                                    "\n\tPLAYER: {} \n\tPoint: {} \n\tDepth: {} \n\tAvailable Cells CNT: {}" +
                                    "\n\tAvailable Cells: {} \n\tLast Moves: {} \n\tComputer Moves: {} " +
                                    "\n\tRuntime Clock: {} ms\n",
                            i, alpha, beta, player, point, depth, board.getAvailableCells().size(),
                            board.getAvailableCells(), board.getLastMoves(), board.getComputerMoves(),
                            (System.currentTimeMillis() - systemTime));
                    log.info("\n####################################################");

                    // get the score for terminal state object
                    int currentScore = minimax(
                            depth + 1,
                            board.getComputerMark(),
                            board,
                            alpha,
                            beta
                    );
                    // Returns the minimum value for the best move of all iterations
                    beta = Math.min( beta, currentScore );
                    // capture stats
                    log.info("\n##################### {} WINS #######################", player);
                    log.info("\n{}",board.displayBoard(board.getGameBoard()));
                    log.info("\nBeta Win Stats => \n\tIndex Position: {} \n\tAlpha: {} \n\tBeta: {} \n\tCurrent Score: {} " +
                                    "\n\tPLAYER: {} \n\tPoint: {} \n\tDepth: {} \n\tAvailable Cells CNT: {}" +
                                    "\n\tAvailable Cells: {} \n\tLast Moves: {} \n\tComputer Moves: {} " +
                                    "\n\tIndexed Point: {} \n\tRuntime Clock: {} ms\n",
                            i, alpha, beta, currentScore, player, point, depth, board.getAvailableCells().size(),
                            board.getAvailableCells(), board.getLastMoves(), board.getComputerMoves(),
                            availableCells.get(i), (System.currentTimeMillis() - systemTime));
                    log.info("\n####################################################");
                    // Checks and/or updates for winning position at the end of game play or during backup
                    // to root node, defined as depth == 0.
                    if ( depth == 0 && currentScore >= 0 )
                    {
                        // capture the best moves for each best case modeled at game play
                        // using recursive maximum score to learn what the best moves are
                        // during this iteration
                        setBestMove(
                                point,
                                currentScore,
                                availableCells.size(),
                                i,
                                alpha,
                                depth
                        );
                    }
                    // alpha beta pruning: prune current branch from game tree
                    if (alpha >= beta)
                    {

                        log.info("\n################### Alpha/Beta #####################");
                        log.info("\n{}", board.displayBoard(board.getGameBoard()));
                        log.info("\nBETA => Alpha/Beta Pruning: \n\tIndex Position: {} \n\tAlpha: {} \n\tBeta: {} " +
                                        "\n\tCurrent Score: {} \n\tPLAYER: {} \n\tPoint: {} \n\tDepth: {} " +
                                        "\n\tAvailable Cells CNT: {} \n\tAvailable Cells: {} \n\tLast Moves: {} " +
                                        "\n\tComputer Moves: {} \n\tIndexed Point: {} \n\tRuntime Clock: {} ms\n",
                                i, alpha, beta, currentScore, player, point, depth, board.getAvailableCells().size(),
                                board.getAvailableCells(), board.getLastMoves(), board.getComputerMoves(),
                                availableCells.get(i), (System.currentTimeMillis() - systemTime));
                        log.info("\n####################################################");
                        // remove the last move played after game completes
                        board.getGameBoard()[point.getRow()][point.getCol()] = board.getNoPlayer();
                        // return winning alpha score and point
                        break;
                    }

                    // remove the last move played after game completes
                    board.getGameBoard()[point.getRow()][point.getCol()] = board.getNoPlayer();
                    // create a new board after a terminal state has been reached
                    board =  new Board(board);
                    i++;
                }
            }
        } catch (Exception e){
            log.error( e.getLocalizedMessage() );
            e.printStackTrace();
        }
        log.info("\nDepth: {} \n\tAlgorithm Start Time: {} ms \n\tInstance Runtime: {} ms",
                depth,
                System.currentTimeMillis(),
                (System.currentTimeMillis()-systemTime) );
        if (depth == 0) {
            log.info("\n\n#################### TERMINAL ######################");
            log.info("\n{}",board.displayBoard(board.getGameBoard()));
            log.info("\nPATH VALUE => \n\tAlpha: {} \n\tBeta: {} " +
                            "\n\tPlayer: {} \n\tDepth: {} \n\tAvailable Cells CNT: {}" +
                            "\n\tAvailable Cells: {} \n\tLast Moves: {} \n\tComputer Moves: {} " +
                            "\n\tRuntime Clock: {} ms\n",
                    alpha, beta, player, depth, board.getAvailableCells().size(),
                    board.getAvailableCells(), board.getLastMoves(), board.getComputerMoves(),
                    (System.currentTimeMillis() - systemTime));
            log.info("\n####################################################");
        }
        // return best score for current player
        return ( player.equals(board.getComputerMark()) ? alpha : beta );
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ///////////////////////////////////////////////////

//        Board bd = new Board(new Object[][]{
//                {"X","","","","O"},
//                {"","","","O",""},
//                {"","","","",""},
//                {"","O","","X",""},
//                {"O","","","","X"}
//        });

        ///////////////////////////////////////////////////

//        Board bd = new Board(new Object[][]{
//                        {"","","","O"},
//                        {"","","","O"},
//                        {"X","","","O"},
//                        {"X","","",""}
//        });

        ///////////////////////////////////////////////////

        Board bd = new Board(new Object[][]{
                {"O","",""},
                {"","",""},
                {"","",""}
        });
        bd.setLastMove(new Point(0,0, bd.getBoardSize()));

        ///////////////////////////////////////////////////
        System.out.printf("\nStart of Minimax");
        bd.displayBoard();
        bd.setComputerMark("X");
        bd.setHumanMark("O");
        // pass game board to minimax class
        Minimax mx = new Minimax(bd);

        Object turn = bd.getComputerMark();
        System.out.printf("\n-----------------------------------------------------\n");
        // find the best move for this round of game play
        while (!bd.getAvailableCells().isEmpty() || !bd.hasPlayerWon("X") || !bd.hasPlayerWon("O"))
        {
            int move = mx.minimax(
                    0,
                    turn,
                    bd,
                    Integer.MIN_VALUE,
                    0

            );
            System.out.printf("\nMinimax initial score: %s\n",move);

//            move = mx.bestMove(
//                    0,
//                    Integer.MIN_VALUE,
//                    bd.getComputerMoves(),
//                    bd,
//                    turn
//            );

            System.out.printf("\nMaximum Score: %s", move);
            System.out.printf("\nMinimax Results Best Moves: \n\t%s\n",
                    mx.bestmoves);
            Point point = mx.bestmoves.get(move);
            bd.placeAMove(point, turn);
            bd.displayBoard();
            System.out.printf("\n\nBest Result: %s | Point: %S\n",
                    move, point);
            System.out.printf("\nMinimax Results Comp Moves: \n\t%s | Best Move: %s\n",
                    bd.getComputerMoves(), move);
            System.out.printf("\nLast Moves: %s\n",
                    bd.getLastMoves());

            System.out.printf("\nPlayer: %s has won: %s\n",
                    turn, bd.hasPlayerWon(bd.getComputerMark()));
            System.out.printf("\n-----------------------------------------------------\n");
            // terminal conditions used to break loop
            if (bd.hasPlayerWon("X") || bd.hasPlayerWon("O") || bd.getAvailableCells().isEmpty()){break;}
            // check to see who's turn it is before player swap?
            if (turn == "X") {
                turn = "O"; // swap turn
                bd.setComputerMark("O"); // swap player O
                bd.setHumanMark("X"); // swap player X
                mx.getBestmoves().clear(); // clear best move container
                bd.getComputerMoves().clear(); // clear computer move container
                bd = new Board(bd);
            }
            else {
                turn = "X"; // swap turn
                bd.setComputerMark("X"); // swap player X
                bd.setHumanMark("O"); // swap player O
                mx.getBestmoves().clear();
                bd.getComputerMoves().clear();
                bd = new Board(bd);
            }
        }



    }

    ///////////////////////////////////////////////////////////////////////////
}
package com.hyfi.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Minimax-Search algorithm involves two players MAX and MIN, MAX moves first and
 * then the players take turns moving until the game is over. At the end of the
 * game points are awarded to the winning player and penalties are given to the loser.
 * We use heuristics to identify the winning player, such as MIN=-1, MAX=1 or DRAW=0.<br>
 * <ul>
 *     <li>S<sub>0</sub>: the initial state, which specifies how the game is setup at
 *     the beginning of search.</li>
 *     <li>To-Move(s): the player whose turn it is to move in state "s".</li>
 *     <li>Actions(s): the set of legal moves in state "s", in games like Tic-Tac-Toe,
 *     this is the available cells left to be played.</li>
 *     <li>Results(s,a): the transition model, which defines the state resulting from
 *     taking action "a" in state "s".</li>
 *     <li>Is-Terminal(s): a terminal test, which is true when the game is over and false
 *     otherwise. States where the game has ended are call terminal states.</li>
 *     <li>Utility(s,p): a utility function (also called an objective function or payoff
 *     function, which defines the final numeric value assigned to player "p" when the
 *     game ends in terminal state "s". In Tic-Tac-Toe, the outcome is a win, loss or
 *     draw, with values 1, -1, or 0.</li>
 * </ul>
 */
public class MinimaxSearch implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(MinimaxSearch.class);
    private PointAndScore printScore = null;
    private static int index;
    // Holds the value of "row and column" reference to computer move.
    private static Map<Integer, Point> computerMoves = null;
    // set root
    private static final BTNode<Board> binaryTree = new BTNode<>(0);

    /**
     * Minimax-Search algorithm involves two players MAX and MIN, MAX moves first and
     * then the players take turns moving until the game is over. At the end of the
     * game points are awarded to the winning player and penalties are given to the loser.
     * We use heuristics to identify the winning player, such as MIN=-1, MAX=1 or DRAW=0.<br>
     */
    public MinimaxSearch(){
        log.info("{} init...", MinimaxSearch.class);

        this.index = 0;
        this.printScore = new PointAndScore();
        this.computerMoves = new HashMap<>();
//        this.alphaScore = Integer.MIN_VALUE;
//        this.betaScore = Integer.MAX_VALUE;
    }
    /**
     * The setBestMove method returns the all moves that end
     * in a win for the computer or a draw.
     * @param nodeIndex the index of the current node
     * @param point		The point returned from a win
     * @param score		The win or draw score returned
     * @param availableCells	Available cells of current depth
     * @param i			The current index to be captured
     * @param max		The best play returned
     * @param depth		The current iteration of game play
     * @param board     the current node
     */
    public synchronized void setBestMove(
            int nodeIndex,
            Point point,
            int score,
            int availableCells,
            int i,
            int max,
            int depth,
            Board board) {
        log.info("\nBest Move: {} | Index: {} | Max: {} | Score: {}\n",
                point,index, max, score);
        try{
//            board = board.getRootNode().getNode(0); // get the root node
//            GameBoardNode<Board> temp = board.getRootNode().getNode(1); // get the node with the next move for the point we need
//            System.err.printf("\nBest Score: \nDepth: %s | Node Index: %s | Score: %s | Max: %s | Point: %s\n",
//                    depth, board.getIndex(), score, max, point);
            // Test score and max for best point
            if (score >= 1 && max >= 1) {
                board.getBoard().setComputerMove( max , point );
                this.computerMoves.put( max , point );
                printScore.printBestScore(point, score, availableCells, i, max, depth, board.getBoard().getRound());
                log.info("\nRound: {} | Point: {} | Score: {} | Available Cells: {} | Iteration: {} @ Depth: {} | MAX GAME RESULTS: {}",
                        board.getBoard().getRound(), point, score, availableCells, i, depth, max);
                return;
            }
            if (score == 0 && max == 0) {
                board.getBoard().setComputerMove( max , point );
                this.computerMoves.put( max , point );
                printScore.printBestScore(point, score, availableCells, i, max, depth, board.getBoard().getRound());
                log.info("\nRound: {} | Point: {} | Score: {} | Available Cells: {} | Iteration: {} @ Depth: {} | MAX GAME RESULTS: {}",
                        board.getBoard().getRound(), point, score, availableCells, i, depth, max);
                return;
            }
            if (score <= -1 && max <= -1) {
                board.getBoard().setComputerMove( max , point );
                this.computerMoves.put( max , point );
                printScore.printBestScore(point, score, availableCells, i, max, depth, board.getBoard().getRound());
                log.info("\nRound: {} | Point: {} | Score: {} | Available Cells: {} | Iteration: {} @ Depth: {} | MAX GAME RESULTS: {}",
                        board.getBoard().getRound(), point, score, availableCells, i, depth, max);
                return;
            }
        } catch (IOException e) {
            log.error("\nUnable to set computer move...\n"+e.getMessage());
            log.error("\nRound: {} | Point: {} | Score: {} | Available Cells: {} | Iteration: {} @ Depth: {} | MAX GAME RESULTS: {}",
                    board.getBoard().getRound() , point, score, availableCells, i, depth, max);
            e.printStackTrace();
        }
        return;
    }

    /**
     * The minimax method is an AI algorithm that simulated the possible moves
     * that can be made at the current game state and returns the best move
     * for the computer to play. It tests all possible moves remaining
     * on the board for each iteration and returns the best possible
     * move for the computer for play towards a win or draw.
     * @param depth		How many moves ahead to loop through.
     * @param player	THe mark of the player during each iteration.
     * @param board     the game board
     * @return			The best possible move for computer to take.
     */
    public int minimax (int depth, Object player, Board board) {

        log.info("\nExecute Minimax => Depth: {}, Player: {}", depth, player);
        ///////////////////////////////////////////////////////////////////////
        List<Point> availablePoints = board.getAvailableCells();
        int beta = Integer.MAX_VALUE, alpha = Integer.MIN_VALUE;

        ///////////////////////////////////////////////////////////////////////
        try {
            ///////////////////////////////////////////////////////////////////
            // check for a winner
            ///////////////////////////////////////////////////////////////////
            if (board.hasPlayerWon(board.getBoard().getComputerMark())) {
                return 1;    // Returns value to "currentScore" on win
            }
            if (board.hasPlayerWon(board.getBoard().getHumanMark())) {
                return -1;    // Returns value to "currentScore" on loss
            }
            // default condition
            if (availablePoints.isEmpty()) {
                return 0;    // Returns value to "currentScore" on draw
            }
            ///////////////////////////////////////////////////////////////////
            // create new game board node for simulated Computer
//            GameBoardNode<Board> b = new GameBoardNode<>(new Board(board.getBoard()), (++index));

            // Iterates through remaining moves of game board to maximum
            // depth of moves remaining on the board.
            for (int i = 0; i < availablePoints.size(); i++) {
                board = new Board(board);
                // Simulates Computer AI game player
                if (player.equals(board.getComputerMark())) {
                        // create new game board node for simulated Computer
//                        board.setLeft(b); // connect left node head/root
                        // get new point from list of available cells
                        Point point = availablePoints.get(i);
                        binaryTree.setLeft(new BTNode<>(board,index));
//                        b.setPoint(point);
                        log.info("\nIteration: {} | Depth: {} | Point: {}", i, depth, point);
                    board.placeAMove(point, board.getComputerMark());
                        System.out.printf("\nIndex: %s", ++index);
                    board.displayBoard();
                        int currentScore = minimax(depth+1, board.getHumanMark(), board);
                        // Returns the maximum value for the best move of all iterations
                        alpha = Math.max(currentScore, alpha);
                        log.info("\nAlpha: {} | Beta: {} | Score: {} | PLAYER: {} | Point: {} | Depth: {}\n",
                                alpha, beta, currentScore, player, point, depth);
                        if (depth == 0){
                            System.out.printf("\nPosition: %s | Score: %s | Point: %s\n",(i+1), currentScore, point);
                        }
                        // If AI computer wins playing index "i" as the next move in the given iteration. The board
                        // is reset and added to the pool of available cells as the next index "i" is tested recursively.
                        if (currentScore == -1 || currentScore >= 0) {
                            System.out.printf("\nAlpha Player => Depth: %s | Node Index: %s | Current Score: %s | Alpha Score: %s | Beta Score: %s | Point: %s | Computer Moves: %s\n",
                                    depth, index, currentScore, alpha, beta, point, board.getComputerMoves());
                            if (depth == 0){
                                setBestMove(
                                        index,
                                        point,
                                        currentScore,
                                        availablePoints.size(),
                                        i,
                                        alpha,
                                        depth,
                                        board);
                            }
                            if (currentScore == 1){
                                board.getGameBoard()[point.getRow()][point.getCol()] = "";
                            }
                            // capture the best moves for each best case modeled at game play
                            // using recursive maximum score to learn what the best moves are
                            // during this iteration
                            if (i == availablePoints.size()-1 && alpha < 0) {
                                if (depth == 0) {
                                    setBestMove(
                                            index,
                                            point,
                                            currentScore,
                                            availablePoints.size(),
                                            i,
                                            alpha,
                                            depth,
                                            board);
                                }
                                board.getGameBoard()[point.getRow()][point.getCol()] = "";
                            }
                            // alpha beta pruning
                            if (alpha >= beta) {
                                break;
                            }
                        }
                    }
                ///////////////////////////////////////////////////////////////
                // Simulates game user human player
                else if (player.equals(board.getHumanMark())) {

                        // create new game board node for simulated User
//                        board.setRight(b); // connect left node head/root
                        // get new point from list of available cells
                        Point point = availablePoints.get(i);
                        binaryTree.setRight(new BTNode<>(board,index));
//                        b.setPoint(point);
                        log.info("\nIteration: {} | Depth: {} | Point: {}", i, depth, point);
                        board.placeAMove(point, board.getHumanMark());
                        System.out.printf("\nIndex: %s", index);
                        board.displayBoard();
                        int currentScore = minimax(++depth, board.getComputerMark(), board);
                        // Returns the minimum value for the best move of all iterations
                        beta = Math.min(currentScore, beta);
                        log.info("\nAlpha: {} | Beta: {} | Score: {} | PLAYER: {} | Point: {} | Depth: {}\n",
                                alpha, beta, currentScore, player, point, depth);
                        // If AI human wins playing index "i" as the next move in the given iteration. The board
                        // is reset and added to the pool of available cells as the next index "i" is tested recursively.
                        if (currentScore == -1) {
                            System.out.printf("\nBeta Player => Depth: %s | Node Index: %s | Current Score: %s | Alpha Score: %s | Beta Score: %s | Point: %s | Computer Moves: %s\n",
                                    depth, index, currentScore, alpha, beta, point, board.getComputerMoves());
                            if (beta == -1){
                                // capture the best moves for each best case modeled at game play
                                // using recursive maximum score to learn what the best moves are
                                // during this iteration. Apply opponent best move to alpha stack of moves.
//                                setBestMove(
//                                        index,
//                                        point,
//                                        currentScore,
//                                        availablePoints.size(),
//                                        i,
//                                        beta,
//                                        depth,
//                                        board
//                                );
                                board.getGameBoard()[point.getRow()][point.getCol()] = "";
                            }


                            // alpha beta pruning
                            if (alpha >= beta) {
                                break;
                            }
                        }
                    }
                board.removeLastMove();
                }

        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }

//        // return the index of the best move upon completion
//        if (board.getIndex() ==  0){
//            int bestMove = board.getBoard().getBestMove().getKey();
//            System.out.printf("\nNext Best move: %s", board.getIndex());
//            System.out.printf("\nFinal Alpha: %s | Final Beta: %s", alpha, beta);
//            return bestMove;
//        }

        // return best score for current player
        return (player.equals(board.getComputerMark()) ? alpha : beta );
    }
    ///////////////////////////////////////////////////////////////////////////
//    /**
//     * Main Method
//     * @param args command line arguments
//     */
//    public static void main(String[] args)
//    {
//        try {
//            ///////////////////////////////////////////////////
////        Board bd = new Board(new String[][]{
////                {"X","","X","","O"},
////                {"","O","O","O","X"},
////                {"","X","O","O",""},
////                {"","X","O","X","X"},
////                {"O","X","O","","X"}
////        });
//
//            ///////////////////////////////////////////////////
////        Board bd = new Board(new String[][]{
////                        {"X","O","O","O"},
////                        {"O","","",""},
////                        {"X","O","",""},
////                        {"X","O","X","X"}
////        });
//
//            ///////////////////////////////////////////////////
//            Board bd = new Board(new Object[][]{
//                    {"O", "O", ""},
//                    {"", "", ""},
//                    {"", "X", ""}
//            });
//
//            ///////////////////////////////////////////////////
////            bd.displayBoard();
//            bd.setComputerMark("X");
//            bd.setHumanMark("O");
//            int nn = 0;
//            BTNode<Board> btree = new BTNode<>(new Board(bd), 0);
//            MinimaxSearch mx = new MinimaxSearch();
//            int move = mx.minimax(0, bd.getComputerMark(), bd);
//            Point point = bd.getBestMove().getValue();
//            System.out.printf("\nBest Result: %s | Point: %S\n",
//                    move, point);
//            System.out.printf("\nComputer Moves: %s\n",
//                    bd.getComputerMoves());
//            if (point == null) {
//                System.exit(0);
//            }
//            bd.placeAMove(point, bd.getComputerMark());
//            System.out.printf("\nPlayer: %s has won: %s\n",
//                    bd.getComputerMark(), bd.hasPlayerWon(bd.getComputerMark()));
//            System.out.printf("\nLast Move: %s\n",
//                    bd.getLastMove());
//
//            System.out.printf("\nMinimax computer moves: %s", mx.computerMoves);
//            bd.displayBoard();
//        } catch (Exception e){
//            log.error(e.getLocalizedMessage());
//            e.printStackTrace();
//        }
//    }
    ///////////////////////////////////////////////////////////////////////////

}

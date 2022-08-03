package com.hyfi.tictactoe;

import org.junit.Test;

public class TicTacToeTest {

    @Test
    public static void TestOpponent(){

        Board bd = new Board(new Object[][]{
                {"O","",""},
                {"","O",""},
                {"","","X"}
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
                    mx.getBestmoves());
            Point point = mx.getBestmoves().get(move);
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

    public static void main(String[] args) {
        TestOpponent();
    }
}

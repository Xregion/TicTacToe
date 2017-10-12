package com.example.kyle.tictactoe;

/**
 * Created by Kyle on 10/5/2017.
 */

public class TicTacToe {
    public static final int SIDE = 3;
    private int turn;
    private int[][] game;

    public TicTacToe() {
        game = new int[SIDE][SIDE];
        ResetGame();
    }

    public int Play(int row, int col) {

        int oldTurn = turn;

        if (game[row][col] == 0) {
            game[row][col] = turn;
            if (turn == 1)
                turn = 2;
            else
                turn = 1;
            return oldTurn;
        }
        else
            return 0;
    }

    public int WhoWon() {

        if (CheckRows() != 0)
            return CheckRows();
        else if (CheckColumns() != 0)
            return CheckColumns();
        else if (CheckDiagonals() != 0)
            return CheckDiagonals();
        else
            return 0;
    }

    private int CheckRows() {

        for (int row = 0; row < SIDE; row++) {
            if (game[row][0] != 0 && game[row][0] == game[row][1] && game[row][0] == game[row][2])
                return game[row][0];
        }

        return 0;
    }

    private int CheckColumns() {

        for (int col = 0; col < SIDE; col++) {
            if (game[0][col] != 0 && game[0][col] == game[1][col] && game[0][col] == game[2][col])
                return game[0][col];
        }

        return 0;
    }

    private int CheckDiagonals() {

        if (game[0][0] != 0 && game[0][0] == game[1][1] && game[0][0] == game[2][2])
            return game[0][0];
        if (game[0][2] != 0 && game[0][2] == game[1][1] && game[0][2] == game[2][0])
            return game[0][2];

        return 0;
    }

    private boolean CannotPlay() {

        for (int row = 0; row < SIDE; row++)
            for (int col = 0; col < SIDE; col++)
                if (game[row][col] == 0)
                    return false;

        return true;
    }

    public boolean IsGameOver() {

        return CannotPlay() || WhoWon() != 0;

    }

    public void ResetGame() {
        for (int row = 0; row < SIDE; row++)
            for (int col = 0; col < SIDE; col++)
                game[row][col] = 0;
        turn = 1;
    }

    public String GetStatus() {
        if (WhoWon() == 1)
            return "Player 1 won!";
        else if (WhoWon() == 2)
            return "Player 2 won!";
        else
            return "Player " + turn + "'s turn";
    }

}

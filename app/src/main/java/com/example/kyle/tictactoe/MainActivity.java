package com.example.kyle.tictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TicTacToe game = new TicTacToe();
    ButtonGridView gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButtonClickHandler bch = new ButtonClickHandler();
        gridLayout = new ButtonGridView(this, bch, TicTacToe.SIDE);
        gridLayout.SetLabelText(getString(game.GetStatus()));

        //Set gridLayout as the View of this activity
        setContentView(gridLayout);
    }

    private void Update(int row, int col) {
        int player = game.Play(row, col);
        if (player == 1)
            gridLayout.SetButtonText(row, col, "X");
        else if (player == 2)
            gridLayout.SetButtonText(row, col, "O");

        gridLayout.SetLabelText(getString(game.GetStatus()));

        if (game.IsGameOver()) {
            PlayAgainButtonHandler playAgainButtonHandler = new PlayAgainButtonHandler();
            gridLayout.EnableButtons(false);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Tic Tac Toe");
            alert.setMessage("Play again?");
            alert.setPositiveButton("Yes", playAgainButtonHandler);
            alert.setNegativeButton("No", playAgainButtonHandler);
            alert.setCancelable(false);
            alert.show();
        }
    }

    private class ButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Outerloop:
            for (int row = 0; row < TicTacToe.SIDE; row++)
                for (int col = 0; col <TicTacToe.SIDE; col++)
                    if (gridLayout.CompareButtonView(row, col, view)) {
                        Update(row, col);
                        break Outerloop;
                    }
        }
    }

    private class PlayAgainButtonHandler implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -1) {
                gridLayout.ResetButtons();
                gridLayout.EnableButtons(true);
                game.ResetGame();
                gridLayout.SetLabelText(getString(game.GetStatus()));
            }
            else if (i == -2)
                MainActivity.this.finish();

        }
    }
}
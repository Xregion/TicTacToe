package com.example.kyle.tictactoe;

import android.app.Application;
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

    private Button[][] buttons;
    private TextView label;
    TicTacToe game = new TicTacToe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        buildGuiByCode();
    }

    public void buildGuiByCode(  ) {
        // get width of the screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / TicTacToe.SIDE;

        //Create the layout manager as a GridLayout
        GridLayout gridLayout = new GridLayout( this );
        gridLayout.setColumnCount(TicTacToe.SIDE);
        gridLayout.setRowCount(TicTacToe.SIDE + 1);

        ButtonClickHandler buttonClickHandler = new ButtonClickHandler();

        //Create the buttons and add them to gridLayout
        buttons = new Button[TicTacToe.SIDE] [TicTacToe.SIDE] ;
        for(int row = 0; row < TicTacToe.SIDE; row++) {
            for (int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col] = new Button (this);
                buttons[row][col].setOnClickListener(buttonClickHandler);
                buttons[row][col].setTextSize(w * 0.2f);
                gridLayout.addView(buttons[row][col], w, w);
            }
        }

        label = new TextView(this);
        GridLayout.Spec rowSpec = GridLayout.spec(3, 1);
        GridLayout.Spec colSpec = GridLayout.spec(0, 3);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
        label.setLayoutParams(params);
        label.setHeight(w);
        label.setWidth(w * 3);
        label.setGravity(Gravity.CENTER);
        label.setTextSize(w * 0.15f);
        label.setBackgroundColor(Color.GREEN);
        label.setText(game.GetStatus());
        gridLayout.addView(label);

        //Set gridLayout as the View of this activity
        setContentView(gridLayout);
    }

    private void Update(int row, int col) {
        int player = game.Play(row, col);
        if (player == 1)
            buttons[row][col].setText("X");
        else if (player == 2)
            buttons[row][col].setText("O");

        label.setText(game.GetStatus());

        if (game.IsGameOver()) {
            PlayAgainButtonHandler playAgainButtonHandler = new PlayAgainButtonHandler();
            EnableButtons(false);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Tic Tac Toe");
            alert.setMessage("Play again?");
            alert.setPositiveButton("Yes", playAgainButtonHandler);
            alert.setNegativeButton("No", playAgainButtonHandler);
            alert.show();
        }
    }

    private void EnableButtons(boolean enabled) {
        for (int row = 0; row < TicTacToe.SIDE; row++)
            for (int col = 0; col <TicTacToe.SIDE; col++)
                buttons[row][col].setEnabled(enabled);
    }

    private class ButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Outerloop:
            for (int row = 0; row < TicTacToe.SIDE; row++)
                for (int col = 0; col <TicTacToe.SIDE; col++)
                    if (buttons[row][col] == view) {
                        Update(row, col);
                        break Outerloop;
                    }
        }
    }

    private class PlayAgainButtonHandler implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -1)
                game.ResetGame();
            else if (i == -2)
                MainActivity.this.finish();

        }
    }
}
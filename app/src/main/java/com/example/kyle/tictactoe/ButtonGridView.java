package com.example.kyle.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

/**
 * Created by Kyle on 10/12/2017.
 */

class ButtonGridView extends GridLayout {

    private Button[][] buttons;
    private TextView label;
    private int side;

    public ButtonGridView(Activity context, View.OnClickListener bch, int side) {
        super(context);

        this.side = side;

        // get width of the screen
        Point size = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / side;

        //Create the layout manager as a GridLayout
        setColumnCount(side);
        setRowCount(side + 1);

        //Create the buttons and add them to gridLayout
        buttons = new Button[side] [side] ;
        for(int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                buttons[row][col] = new Button (context);
                buttons[row][col].setOnClickListener(bch);
                buttons[row][col].setTextSize(w * 0.2f);
                addView(buttons[row][col], w, w);
            }
        }

        label = new TextView(context);
        GridLayout.Spec rowSpec = GridLayout.spec(3, 1);
        GridLayout.Spec colSpec = GridLayout.spec(0, 3);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
        label.setLayoutParams(params);
        label.setHeight(w);
        label.setWidth(w * 3);
        label.setGravity(Gravity.CENTER);
        label.setTextSize(w * 0.15f);
        label.setBackgroundColor(Color.GREEN);
        addView(label);
    }

    public void SetLabelText(String text) {
        label.setText(text);
    }

    public void SetButtonText(int row, int col, String text) {
        buttons[row][col].setText(text);
    }

    public void EnableButtons(boolean enabled) {
        for (int row = 0; row < side; row++)
            for (int col = 0; col <side; col++)
                buttons[row][col].setEnabled(enabled);
    }

    public void ResetButtons() {
        for (int row = 0; row < side; row++)
            for (int col = 0; col <side; col++)
                SetButtonText(row, col, "");
    }

    public boolean CompareButtonView (int row, int col, View view) {
        return buttons[row][col] == view;
    }
}

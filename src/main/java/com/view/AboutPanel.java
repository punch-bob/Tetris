package com.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AboutPanel extends JPanel
{
    private JTextArea infoText;

    public AboutPanel()
    {
        super();
        infoText = new JTextArea();
        infoText.setEditable(false);
        infoText.setCursor(null);
        infoText.setOpaque(false);
        infoText.setFocusable(false);

        infoText.setText("""
            Rules of the game:
            Random tetramino figures fall from above 
            into a rectangular box with a width of 10 
            and a height of 20 cells.  The player gets 
            points for each filled row, so his task is 
            to fill the rows without filling the box 
            itself (vertically) as long as possible. 
            The goal is to score as many points as possible.

            Points are awarded for filled rows:
            1 - 10 points;
            2 - 25 points;
            3 - 50 points;
            4 - 80 points:
            Every 100 points the game accelerates.

            Management:
            A - move the shape to the left;
            S - move the shape down;
            D - move the shape to the right;
            W - rotate the shape;
            SPACE - drop a shape;
            H - set hold shape;
            ENTER - pause/unpause the game;
            R - restart game;


            TETRIS version 1.0
            """);

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.add(infoText, BorderLayout.CENTER);
        setVisible(false);
    }
}

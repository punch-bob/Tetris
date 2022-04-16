package com.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.model.ScoreCounter;
import com.publisher.ISubscriber;

public class GameOverPanel extends JPanel implements ISubscriber
{
    private JTextArea gameOverText;
    private ScoreCounter score;

    public GameOverPanel(ScoreCounter score)
    {
        super();
        gameOverText = new JTextArea();
        gameOverText.setEditable(false);
        gameOverText.setCursor(null);
        gameOverText.setOpaque(false);
        gameOverText.setFocusable(false);

        this.score = score;
        score.addSubscriber(this);
        this.gameOverText.setText("""
                      GAME OVER!
        
                    YOUR SCORE:""" + "  " + score.getScore() + 

                """
                    \n\nPRESS R - TO RESTART.
                """ 
        );

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.add(gameOverText, BorderLayout.CENTER);
        this.setVisible(false);
    }

    @Override
    public void reactOnNotify() 
    {
        this.gameOverText.setText("""
                      GAME OVER!
        
                    YOUR SCORE:""" + "  " + score.getScore() + 

                """
                    \n\nPRESS R - TO RESTART.
                """ 
        );
    }
}

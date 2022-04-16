package com.view;

import javax.swing.JLabel;

import com.model.ScoreCounter;
import com.publisher.ISubscriber;

public class ScoreDrawer extends JLabel implements ISubscriber
{
    private ScoreCounter score;

    public ScoreDrawer()
    {
        super("SCORE: ");
    }

    public void setScore(ScoreCounter score)
    {
        this.score = score;
        score.addSubscriber(this);
        reactOnNotify();
    }

    @Override
    public void reactOnNotify() 
    {
        this.setText("SCORE: " + score.getScore());
    }
}

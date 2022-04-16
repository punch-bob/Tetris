package com.view;

import javax.swing.JLabel;

import com.model.LevelCounter;
import com.publisher.ISubscriber;

public class LevelDrawer extends JLabel implements ISubscriber
{
    private LevelCounter level;

    public LevelDrawer()
    {
        super("LEVEL: ");
    }

    public void setLevel(LevelCounter level)
    {
        this.level = level;
        level.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() 
    {
        this.setText("LEVEL: " + level.getLevel());
    }
}

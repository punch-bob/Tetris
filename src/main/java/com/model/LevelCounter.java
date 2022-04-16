package com.model;

import com.publisher.Publisher;

public class LevelCounter extends Publisher
{
    private int level;
    private int lastLevelUpdateScore;

    public LevelCounter()
    {
        this.lastLevelUpdateScore = 0;
        level = 0;
    }

    public void increase(int score)
    {
        if (score - lastLevelUpdateScore >= 100)
        {
            level++;
            lastLevelUpdateScore = score;
        }
        publishNotify();
    }

    public void clear()
    {
        lastLevelUpdateScore = 0;
        level = 0;
        publishNotify();
    }

    public int getLevel()
    {
        return level;
    }
}

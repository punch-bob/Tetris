package com.model;

import com.publisher.Publisher;

public class ScoreCounter extends Publisher
{
    int score;

    public ScoreCounter()
    {
        score = 0;
        publishNotify();
    }

    public void increase(int value)
    {
        score += value;
        publishNotify();
    }

    public void clear()
    {
        score = 0;
        publishNotify();
    }

    public int getScore()
    {
        return score;
    }
}
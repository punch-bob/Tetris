package com.model;

public class TetrisSpeed
{
    private int startTime;
    private int timeForStep;
    private int prevLevel;

    public TetrisSpeed(int timeForStep)
    {
        this.timeForStep = timeForStep;
        startTime = timeForStep;
        prevLevel = 0;
    }

    public void increase(int level)
    {
        if (prevLevel < level && timeForStep > startTime / 3)
        {
            timeForStep -= 20;
        }
    }

    public int getTimeForStep()
    {
        return timeForStep;
    }

    public void clear()
    {
        timeForStep = startTime;
    }
}

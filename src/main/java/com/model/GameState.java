package com.model;

import com.publisher.Publisher;

public class GameState extends Publisher
{
    private StateEnum state;
    
    public GameState(StateEnum state)
    {
        this.state = state;
    }

    public StateEnum getState()
    {
        return state;
    }

    public void setState(StateEnum state)
    {
        this.state = state;
        publishNotify();
    }
}

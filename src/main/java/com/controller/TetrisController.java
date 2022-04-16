package com.controller;

import com.model.TetrisGame;

public class TetrisController 
{
    TetrisGame game;

    public TetrisController(TetrisGame game)
    {
        this.game = game;
    }

    public void execute(IComandStrategy comand, String argument)
    {
        comand.execute(game, argument);
    }
}

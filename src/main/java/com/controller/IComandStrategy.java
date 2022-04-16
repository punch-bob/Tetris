package com.controller;

import com.model.TetrisGame;

public interface IComandStrategy 
{
    void execute(TetrisGame game, String argument);   
}

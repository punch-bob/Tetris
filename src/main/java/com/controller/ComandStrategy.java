package com.controller;

import com.model.SideEnum;
import com.model.TetrisGame;

public enum ComandStrategy implements IComandStrategy
{
    RIGHT
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.moveSide(SideEnum.R);
        }
    },

    LEFT
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.moveSide(SideEnum.L);
        }
    },

    DOWN
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.moveDown();
        }
    },

    DROP
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.dropDown();
        }
    },

    ROTATE
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.rotate();
        }
    },

    SETHOLD
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.setHoldShape();
        }
    },

    SWITCHSTOPSTATE
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.switchStopState();
        }
    },

    CONTINUE
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.unpause();
        }
    },

    NEWGAME
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.newGame();
        }
    },

    ABOUT
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.about();
        }
    },

    HIGHSCORE
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            game.highScore();
        }
    },

    SAVESCORE
    {
        @Override
        public void execute(TetrisGame game, String argument) 
        {
            if (argument == null)
            {
                argument = "default"; 
            }
            game.saveNewResult(argument);
        }
    };
}

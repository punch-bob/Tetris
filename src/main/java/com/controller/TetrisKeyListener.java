package com.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener
{
    private TetrisController controller;

    public TetrisKeyListener(TetrisController controller)
    {
        super();
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        switch(e.getKeyChar())
        {
            case('a') -> controller.execute(ComandStrategy.LEFT, null);
            case('s') -> controller.execute(ComandStrategy.DOWN, null);
            case('d') -> controller.execute(ComandStrategy.RIGHT, null);
            case('w') -> controller.execute(ComandStrategy.ROTATE, null);
            case('h') -> controller.execute(ComandStrategy.SETHOLD, null);
            case('r') -> controller.execute(ComandStrategy.NEWGAME, null);
            case('\n') -> controller.execute(ComandStrategy.SWITCHSTOPSTATE, null);
            case(' ') -> controller.execute(ComandStrategy.DROP, null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}
}

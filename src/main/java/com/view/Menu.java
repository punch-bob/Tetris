package com.view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.controller.ComandStrategy;
import com.controller.TetrisController;

public class Menu extends JMenu
{
    public Menu(TetrisController controller) 
    {
        super("Menu");
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem cont = new JMenuItem("Continue");
        JMenuItem about = new JMenuItem("About");
        JMenuItem highScores = new JMenuItem("High Scores");
        this.add(newGame);
        this.add(cont);
        this.add(about);
        this.add(highScores);

        about.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controller.execute(ComandStrategy.ABOUT, null);
            }
        });
            
        newGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controller.execute(ComandStrategy.NEWGAME, null);
            }
        });

        highScores.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controller.execute(ComandStrategy.HIGHSCORE, null);
            }
        });

        cont.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                controller.execute(ComandStrategy.CONTINUE, null);
            }
        });
    }
}

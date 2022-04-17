package com.view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.controller.ComandStrategy;
import com.controller.TetrisController;

public class Menu extends JMenu
{
    TetrisController controller;

    public Menu(TetrisController controller) 
    {
        super("Menu");
        this.controller = controller;
        createMenuItem("New Game", ComandStrategy.NEWGAME);
        createMenuItem("Continue", ComandStrategy.CONTINUE);
        createMenuItem("About", ComandStrategy.ABOUT);
        createMenuItem("High Scores", ComandStrategy.HIGHSCORE);
    }

    private void addListener(JMenuItem menuItem, CommandActionListener listener)
    {
        menuItem.addActionListener(listener);
    }

    private void createMenuItem(String itemName, ComandStrategy comandStrategy)
    {
        JMenuItem menuItem = new JMenuItem(itemName);
        this.add(menuItem);
        CommandActionListener listener = new CommandActionListener(controller, comandStrategy, null);
        addListener(menuItem, listener);
    }
}

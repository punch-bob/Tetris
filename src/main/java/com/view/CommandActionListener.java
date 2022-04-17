package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.controller.ComandStrategy;
import com.controller.TetrisController;

public class CommandActionListener implements ActionListener
{
    TetrisController controller;
    private ComandStrategy comandStrategy;
    private String argument;

    public CommandActionListener(TetrisController controller, ComandStrategy comandStrategy, String argument)
    {
        this.argument = argument;
        this.comandStrategy = comandStrategy;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        controller.execute(comandStrategy, argument);
    }
}

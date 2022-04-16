package com.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BlockDrawer extends JPanel
{
    private int size;
    private Color color;

    public BlockDrawer(int size, Color color)
    {
        this.size = size;
        this.color = color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, size, size);
    }
}

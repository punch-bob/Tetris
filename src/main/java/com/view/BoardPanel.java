package com.view;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Color;

import com.model.board.Board;
import com.publisher.ISubscriber;

public class BoardPanel extends JPanel implements ISubscriber
{
    private BlockDrawer[][] board;
    private int sizeX;
    private int sizeY;
    private Board blocksMatrix;

    public BoardPanel(int sizeY, int sizeX)
    {
        super(new GridLayout(sizeY, sizeX, 2, 2));
        
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        board = new BlockDrawer[20][10];
        for (int i = 0; i < board.length; ++i) 
        {
			for (int j = 0; j < board[i].length; ++j) 
            {
				board[i][j] = new BlockDrawer(30, Color.GRAY);
				this.add(board[i][j]);
			}
		}
    }

    public void setBoard(Board blocksMatrix)
    {
        this.blocksMatrix = blocksMatrix;
        blocksMatrix.addSubscriber(this);
    }

    @Override
    public void reactOnNotify() 
    {
        Color[][] actualState = blocksMatrix.getBoardState();
        for (int i = 0; i < sizeY; ++i)
        {
            for (int j = 0; j < sizeX; ++j)
            {
                board[i][j].setColor(actualState[i][j]);
            }
        }
        this.repaint();
    }
}

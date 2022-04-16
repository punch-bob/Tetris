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
                if (actualState[i][j] == Color.GRAY)
                {
                    board[i][j].setColor(Color.GRAY);
                }
                else if (actualState[i][j] == Color.RED)
                {
                    board[i][j].setColor(Color.RED);
                }
                else if (actualState[i][j] == Color.YELLOW)
                {
                    board[i][j].setColor(Color.YELLOW);
                }
                else if (actualState[i][j] == Color.GREEN)
                {
                    board[i][j].setColor(Color.GREEN);
                }
                else if (actualState[i][j] == Color.MAGENTA)
                {
                    board[i][j].setColor(Color.MAGENTA);
                }
                else if (actualState[i][j] == Color.CYAN)
                {
                    board[i][j].setColor(Color.CYAN);
                }
                else if (actualState[i][j] == Color.ORANGE)
                {
                    board[i][j].setColor(Color.ORANGE);
                }
                else if (actualState[i][j] == Color.BLUE)
                {
                    board[i][j].setColor(Color.BLUE);
                }
            }
        }
        this.repaint();
    }
}

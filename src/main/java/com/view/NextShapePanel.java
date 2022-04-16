package com.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.model.shapes.NextTetromino;
import com.publisher.ISubscriber;

public class NextShapePanel extends JPanel implements ISubscriber
{
    private NextTetromino tetromino;
    private final int blocksX = 4;
    private final int blocksY = 4;
    private Color tetrominoColor;
    private BlockDrawer[][] miniBoard;

    public NextShapePanel(int blockSize)
    {
        super();
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); 
        JPanel center = new JPanel();
        JPanel top = new JPanel();
        miniBoard = new BlockDrawer[blocksY][blocksX];

        //set top part
        constraints.fill = GridBagConstraints.HORIZONTAL; 
        constraints.weightx = 0.5;
        constraints.gridy = 0;
        JTextArea nextTetromino = new JTextArea("NEXT:");
        nextTetromino.setEditable(false);
        top.setBackground(Color.WHITE);
        top.add(nextTetromino);
        this.add(top, constraints);

        //set middle part
        GridLayout miniBoardLayout = new GridLayout(blocksY, blocksX, 1, 1);
        center.setLayout(miniBoardLayout);
        center.setBackground(Color.WHITE);
        for (int i = 0; i < miniBoard.length; ++i) 
        {
			for (int j = 0; j < miniBoard[i].length; ++j) 
            {
				miniBoard[i][j] = new BlockDrawer(blockSize, Color.GRAY);
				center.add(miniBoard[i][j]);
			}
		}
        constraints.fill = GridBagConstraints.BOTH;
        constraints.ipady = 40;
        constraints.weightx = 0.0; 
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(center, constraints);
    }

    public void setNextTetromino(NextTetromino tetromino)
    {
        this.tetromino = tetromino;
        tetrominoColor = tetromino.getColor();
        tetromino.addSubscriber(this);
        reactOnNotify();
    }

    @Override
    public void reactOnNotify() 
    {
        boolean[][] blocksMatrix = tetromino.getBlock();
        tetrominoColor = tetromino.getColor();
        for (int i = 0; i < blocksMatrix.length; ++i)
        {
            for (int j = 0; j < blocksMatrix.length; ++j)
            {
                if (blocksMatrix[i][j])
                {
                    miniBoard[i][j].setColor(tetrominoColor);
                }
                else
                {
                    miniBoard[i][j].setColor(Color.GRAY);
                }
            }
        }
        repaint();
    }
}

package com.model.shapes;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;

import com.model.SideEnum;

public abstract class Tetromino implements IShapes 
{
    protected Color color;
    private Point currentPosition;
    protected boolean[][] block;
    private boolean[][] tmpBlock;
    protected TetrominosEnum type;

    public Tetromino()
    {
        block = new boolean[4][4];
        tmpBlock = new boolean[4][4];
        currentPosition = new Point(3, -4);
    }

    @Override
    public Color getColor() 
    {
        return this.color;
    }

    @Override
    public boolean[][] getBlock() 
    {
        return this.block;
    }

    @Override
    public TetrominosEnum getType()
    {
        return type;
    }

    @Override
    public void rotate(SideEnum side) 
    {
        /*
        *                |cos(90 or 270)    -sin(90 or 270)|
        * rotateMatrix = |                                 |
        *                |sin(90 or 270)    cos(90 or 270) |
        */
        int rotateMatrix[][];

        if (side == SideEnum.R)
        {
            rotateMatrix = new int[][]{new int[]{0, -1}, new int[]{1, 0}};
        }
        else
        {
            rotateMatrix = new int[][]{new int[]{0, 1}, new int[]{-1, 0}};
        }
        
        clear(tmpBlock);

        int pivotPoint[] = new int[]{1, 1};
        for(int i = 0; i < block.length; ++i)
        {
            for(int j = 0; j < block.length; ++j)
            {
                if(block[i][j])
                {
                    int currentPoint[] = new int[]{i, j};
                    int newCoords[] = calcCoordsInAnotherCoordSyst(currentPoint, pivotPoint, true);
                    int rotatedCoords[] = multMatrixVector(rotateMatrix, newCoords);
                    newCoords = calcCoordsInAnotherCoordSyst(pivotPoint, rotatedCoords, false);
                    tmpBlock[newCoords[0]][newCoords[1]] = true;
                }
            }
        }
        copy(tmpBlock, block);
        
    }

    /*
    coordsSystem == true => move to a new coordinate system
    coordsSystem == false => move to the canonical coordinate system
    */
    private int[] calcCoordsInAnotherCoordSyst(int[] aimPoint, int[] newOriginCoords, boolean coordsSystem)
    {
        int newCoords[] = new int[aimPoint.length];
        if(coordsSystem)
        {
            newCoords[0] = aimPoint[0] - newOriginCoords[0];
            newCoords[1] = aimPoint[1] - newOriginCoords[1];
        }
        else
        {
            newCoords[0] = aimPoint[0] + newOriginCoords[0];
            newCoords[1] = aimPoint[1] + newOriginCoords[1];
        }
        return newCoords;
    }

    private int[] multMatrixVector(int[][] matrix, int[] vector)
    {
        int[] result = new int[vector.length];
        result[0] = matrix[0][0] * vector[0] + matrix[0][1] * vector[1];
        result[1] = matrix[1][0] * vector[0] + matrix[1][1] * vector[1];
        return result;
    }

    private void copy(boolean[][] matrixA, boolean[][] matrixB) 
    {
        for (int i = 0; i < block.length; ++i)
        {
            for (int j = 0; j < block.length; ++j)
            {
                matrixB[i][j] = matrixA[i][j];
            }
        }
    }

    @Override
    public void setBlock(boolean[][] block) 
    {
        this.block = block;
    }

    @Override
    public Point getPosition()
    {
        return this.currentPosition;
    }

    @Override
    public void movePosition(int x, int y)
    {
        this.currentPosition.x += x;
        this.currentPosition.y += y;
    }


    private void clear(boolean[][] block)
    {
        for (boolean[] row : block) 
        {
            Arrays.fill(row, false);
        }
    }
}

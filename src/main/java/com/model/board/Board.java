package com.model.board;

import java.util.Arrays;
import java.awt.Color;
import java.awt.Point;

import com.model.SideEnum;
import com.model.factory.TetrominoFactory;
import com.model.shapes.EmptyTetromino;
import com.model.shapes.NotifierTetromino;
import com.model.shapes.IShapes;
import com.model.shapes.TetrominosEnum;
import com.publisher.Publisher;

public class Board extends Publisher
{
    TetrominoFactory factory = new TetrominoFactory();

    private int width;
    private int height;
    private Color[][] board;
    private Color[][] tmpBoard;
    private IShapes tetromino;
    private NotifierTetromino holdTetromino;
    private boolean isAlredyHeld = false;
    private NotifierTetromino nextTetromino;
    private boolean isGameOver;

    public Board(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.board = new Color[height][width];
        this.tmpBoard = new Color[height][width];
        for (Color[] row : board) 
        {
            Arrays.fill(row, Color.GRAY);
        }
        holdTetromino = new NotifierTetromino();
        holdTetromino.setTetromino(new EmptyTetromino());
        nextTetromino = new NotifierTetromino();
        nextTetromino.setTetromino(TetrominosEnum.getRandomType());
        isGameOver = false;
    }

    public void clear()
    {
        for (Color[] row : board) 
        {
            Arrays.fill(row, Color.GRAY);
        }
        isGameOver = false;
        tetromino = null;
        setNextTetromino(TetrominosEnum.getRandomType());
        holdTetromino.setTetromino(new EmptyTetromino());
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Color[][] getBoardState()
    {
        for (int i = 0; i < board.length; ++i)
        {
            tmpBoard[i] = Arrays.copyOf(board[i], width);
        }
        installTetromino(BoardsTypesEnum.TEMPORARY);
        return tmpBoard;
    }

    //REMOVE LINES
    public int removeLines()
    {
        int removedLinesCounter = 0;

        for (int i = 0; i < height; ++i)
        {
            if(canRemove(board[i]))
            {
                removedLinesCounter++;
                updateBoard(i);
            }
        }
        publishNotify();
        return removedLinesCounter;
    }    

    private boolean canRemove(Color[] row)
    {
        for (Color x : row)
        {
            if(x.equals(Color.GRAY))
            {
                return false;
            }
        }
        return true;
    }

    private void updateBoard(int startLineNumber)
    {
        Color[] emptyLine = new Color[width];
        Arrays.fill(emptyLine, Color.GRAY);

        for (int i = startLineNumber; i >= 0; --i)
        {
            if(i != 0)
            {
                board[i] = Arrays.copyOf(board[i - 1], width);
            }
            else
            {
                board[i] = emptyLine;
            }
        }
    }

    //HOLD TETROMINO
    public NotifierTetromino getHoldTetromino()
    {
        return holdTetromino;
    }

    public void setHoldTetromino(IShapes tetromino)
    {
        holdTetromino.setTetromino(tetromino);
    }

    public void HoldTetromino()
    {
        if (isAlredyHeld)
        {
            return;
        }
        if (holdTetromino.getType() == null)
        {
            setHoldTetromino(tetromino);
            tetromino = TetrominoFactory.create(nextTetromino.getType());
            setNextTetromino(TetrominosEnum.getRandomType());
        }
        else
        {
            TetrominosEnum newType = tetromino.getType();
            tetromino = TetrominoFactory.create(holdTetromino.getType());
            holdTetromino.setTetromino(newType);
        }
        isAlredyHeld = true;
    }

    //NEXT TETROMINO
    public NotifierTetromino getNextTetromino()
    {
        return nextTetromino;
    }

    public void setNextTetromino(TetrominosEnum type)
    {
        nextTetromino.setTetromino(type);
    }

    public void spawnRandomTetromino()
    {
        isAlredyHeld = false;
        tetromino = TetrominoFactory.create(nextTetromino.getType());
        setNextTetromino(TetrominosEnum.getRandomType());
    }

    //MOVES
    public void rotateTetramino()
    {
        tetromino.rotate(SideEnum.R);
        if (isTouchBorder(0, 1))
        {
            pushFromWall();
        }

        if (isTouchBorder(0, 1))
        {
            tetromino.rotate(SideEnum.L);
        }
        publishNotify();
    }

    private void pushFromWall()
    {
        if (!isTouchBorder(1, 0) && isTouchBorder(0, 0))
        {
            tetromino.movePosition(1, 0);
            if (isTouchBorder(0, 0))
            {
                tetromino.movePosition(-1, 0);
            }
        }

        if (!isTouchBorder(-1, 0) && isTouchBorder(0, 0))
        {
            tetromino.movePosition(-1, 0);
            if (isTouchBorder(0, 0))
            {
                tetromino.movePosition(1, 0);
            }
        }
    }

    public void moveX(SideEnum side)
    {
        int x = 0;
        if (side == SideEnum.L)
        {
            x = -1;
        }
        else
        {
            x = 1;
        }
        if (!isTouchBorder(x, 0))
        {
            tetromino.movePosition(x, 0);
        }
        publishNotify();
    }

    public void slowFall()
    {
        if (isTouchBorder(0, 1))
        {
            if (!installTetromino(BoardsTypesEnum.MAIN))
            {
                isGameOver = true;
            }
            else
            {
                spawnRandomTetromino();
            }
        }
        tetromino.movePosition(0, 1);
        publishNotify();
    }

    public void drop()
    {
        while (!isTouchBorder(0, 1))
        {
            tetromino.movePosition(0, 1);
        }
        if (!installTetromino(BoardsTypesEnum.MAIN))
        {
            isGameOver = true;
        }
        else
        {
            spawnRandomTetromino();
        }
    }

    //CHECKS
    public boolean installTetromino(BoardsTypesEnum type)
    {
        Color[][] currentBoard;
        boolean[][] currentTetromino = tetromino.getBlock();
        Point currentPoint = tetromino.getPosition();

        if (type == BoardsTypesEnum.MAIN)
        {
            currentBoard = board;
        }
        else
        {
            currentBoard = tmpBoard;
        }
        

        for (int i = 0; i < currentTetromino.length; ++i)
        {
            for (int j = 0; j < currentTetromino.length; ++j)
            {
                if (BoardsTypesEnum.MAIN == type && currentTetromino[i][j] && currentPoint.y + i < 0)
                {
                    return false;
                }
                
                if (currentTetromino[i][j] && currentPoint.y + i >= 0)
                {
                    currentBoard[currentPoint.y + i][currentPoint.x + j] = tetromino.getColor();
                }
            }
        }
        return true;
    }

    private boolean isTouchBorder(int x, int y)
    {
        Point currentPosition = tetromino.getPosition();
        boolean[][] tmp = tetromino.getBlock();
        int xChecked = currentPosition.x + x;
        int yChecked = currentPosition.y + y;
        for (int i = 0; i < tmp.length; ++i)
        {
            int toY = i + yChecked;
            for (int j = 0; j < tmp.length; ++j)
            {
                int toX = j + xChecked;
                if (tmp[i][j])
                {
                    if (toX < 0 || toX >= width || toY >= height || toY >= 0 && !board[toY][toX].equals(Color.GRAY))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isTetraminoSpawned()
    {
        if (tetromino == null)
        {
            return false;
        }
        return true;
    }

    public boolean isGameOver()
    {
        return isGameOver;
    }
}

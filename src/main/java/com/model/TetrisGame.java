package com.model;

import java.util.Timer;
import java.util.TimerTask;

import com.model.board.Board;
import com.model.high_score.HighScoreTable;
import com.model.high_score.TableToken;
import com.model.high_score.UserName;
import com.model.shapes.NotifierTetromino;
import com.model.shapes.TetrominosEnum;
import com.publisher.Publisher;

public class TetrisGame extends Publisher
{

    private Board board;
    private ScoreCounter score;
    private LevelCounter level;
    private GameState state;
    private TetrisSpeed timeForStep;
    private HighScoreTable highScoreTable;

    public TetrisGame(StateEnum state)
    {
        board = new Board(10, 20);
        score = new ScoreCounter();
        level = new LevelCounter();
        highScoreTable = new HighScoreTable("src/main/resources/HighScores.txt");
        this.state = new GameState(state);
        timeForStep = new TetrisSpeed(500);
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() 
        {
            @Override
            public void run() 
            {
                nextStep();
            }
        };
        timer.schedule(timerTask, 0, timeForStep.getTimeForStep());
    }


    //STATES
    public GameState getState()
    {
        return state;
    }

    public void pause()
    {
        state.setState(StateEnum.PAUSE);
    }

    public void unpause()
    {
        if (state.getState() == StateEnum.GAMEOVER)
        {
            return;
        }
        state.setState(StateEnum.RUN);
    }

    public void switchStopState()
    {
        if (state.getState() != StateEnum.RUN)
        {
            unpause();
            return;
        }
        pause();
    }

    public void about()
    {
        state.setState(StateEnum.ABOUT);
    }

    public void newGame()
    {
        board.clear();
        score.clear();
        level.clear();
        state.setState(StateEnum.RUN);
        highScoreTable.updateHighScoreTable();
    }

    public void highScore()
    {
        state.setState(StateEnum.HIGHSCORE);
        highScoreTable.updateHighScoreTable();
    }

    //SCORE
    public ScoreCounter getScoreCounter()
    {
        return score;
    }

    public void setScore(int removedLines) 
    {
        switch (removedLines) 
        {
            case 1:
              score.increase(10);
              break;
            case 2:
              score.increase(25);
              break;
            case 3:
              score.increase(50);
              break;
            case 4:
              score.increase(80);
              break;
        }
        level.increase(score.getScore());
    } 

    //LEVEL
    public LevelCounter getLevelCounter()
    {
        return level;
    }

    public void rotate() 
    {
        board.rotateTetramino();
    }

    //HIGHSCORE
    public HighScoreTable getHighScoreTable()
    {
        return highScoreTable;
    }

    public void saveNewResult(String userName)
    {
        UserName newUserName = new UserName(userName);
        highScoreTable.addNewScore(new TableToken(newUserName, score.getScore()));
    }

    //TETROMINO
    public NotifierTetromino getNextShape() 
    {
        return board.getNextTetromino();
    }

    public void setNextShape() 
    {
        if (state.getState() == StateEnum.RUN)
        {
            board.setNextTetromino(TetrominosEnum.getRandomType());
        }
    }

    public NotifierTetromino getHoldShape() 
    {
        if (state.getState() == StateEnum.RUN)
        {
            return board.getHoldTetromino();
        }
        return null;
    }

    public void setHoldShape()
    {
        board.HoldTetromino();
        publishNotify();
    }

    public void moveSide(SideEnum side) 
    {
        if (state.getState() == StateEnum.RUN)
        {
            board.moveX(side);
        }
    }

    public void moveDown() 
    {
        if (state.getState() == StateEnum.RUN)
        {
            board.slowFall();
        }  
    }

    public void fallDown() 
    {
        if (state.getState() == StateEnum.RUN)
        {
            board.slowFall();
        }
    }

    public void dropDown() 
    {
        if (state.getState() == StateEnum.RUN)
        {
            board.drop();
        }
    }

    //BOARD
    public Board getBoard()
    {
        return this.board;
    }

    public void nextStep()
    {
        if (state.getState() != StateEnum.RUN) 
        {
            return;
        }
        if (!board.isTetraminoSpawned())
        {
            board.spawnRandomTetromino();
        }
        fallDown();
        
        if (board.isGameOver())
        {
            this.state.setState(StateEnum.GAMEOVER);
        }

        int removedLines = board.removeLines();
        setScore(removedLines);
        level.increase(score.getScore());
        timeForStep.increase(level.getLevel());
    }
}

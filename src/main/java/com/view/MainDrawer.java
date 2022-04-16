package com.view;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controller.ComandStrategy;
import com.controller.TetrisController;
import com.controller.TetrisKeyListener;

import javax.swing.ImageIcon;

import com.model.GameState;
import com.model.StateEnum;
import com.model.TetrisGame;
import com.publisher.ISubscriber;

public class MainDrawer extends JFrame implements ISubscriber
{
    TetrisGame game;
    GameState state;
    TetrisController controller;

    private BoardPanel board;
    private AboutPanel about;
    private HighScoreDrawer highScore;
    private ScoreDrawer score;
    private LevelDrawer level;
    private NextShapePanel nextShape;
    private HoldShapePanel holdShape;
    private GameOverPanel gameOver;

    JPanel center = new JPanel();
	JPanel right = new JPanel();
    JPanel top = new JPanel();

    public MainDrawer(TetrisGame game)
    {
        //set icon
        super("TETRIS");
        iconSetting();

        this.game = game;

        //set mainlayout
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout mainLayout = new BorderLayout();
        mainLayout.setHgap(10);
        this.setLayout(mainLayout);
        this.setLocationRelativeTo (null);
        this.setSize(15 * 30, 24 * 30);
        this.state = game.getState();
        state.addSubscriber(this);

        //create and post all panel
        settingCentralPanel();
		this.add(center, BorderLayout.CENTER);

        settingRightPanel();
		this.add(right, BorderLayout.EAST);

        settingTopPanel();
        this.add(top, BorderLayout.NORTH);
        
        settingMenuBar();
    }

    private void panelOverlay(boolean flag)
    {
        board.setVisible(flag);
        top.setVisible(flag);
        right.setVisible(flag);
    }

    private void iconSetting()
    {
        ImageIcon icon = new ImageIcon("src/main/resources/tetris.png");
        this.setIconImage(icon.getImage());
    }

    private void settingCentralPanel()
    {
        board = new BoardPanel(20, 10);
        board.setBoard(game.getBoard());
        board.setPreferredSize(new Dimension(10 * 30, 20 * 30));

        about = new AboutPanel();
        
        gameOver = new GameOverPanel(game.getScoreCounter());

        highScore = new HighScoreDrawer(game.getHighScoreTable());
        highScore.setPreferredSize(new Dimension(10 * 30, 6 * 29));
        
        center.add(board);
        center.add(about);
        center.add(gameOver);
        center.add(highScore);
    }

    private void settingRightPanel()
    {
        nextShape = new NextShapePanel(20);
        nextShape.setNextTetromino(game.getNextShape());
        nextShape.setPreferredSize(new Dimension(4 * 20  + 4, 4 * 20 + 4));

        holdShape = new HoldShapePanel();
        holdShape.setHoldTetromino(game.getHoldShape());
        nextShape.setPreferredSize(new Dimension(4 * 20  + 4, 4 * 20 + 4));

        level = new LevelDrawer();
        level.setLevel(game.getLevelCounter());

        right.setLayout(new GridLayout(3, 1));
        right.add(nextShape);
        right.add(holdShape);
        right.add(level);
    }

    private void settingTopPanel()
    {
        score = new ScoreDrawer();
        score.setScore(game.getScoreCounter());

        top.add(score);
    }

    private void settingMenuBar()
    {
        controller = new TetrisController(game);
        addKeyListener(new TetrisKeyListener(controller));
        JMenuBar menu = new JMenuBar();
        menu.add(new Menu(controller));
        setJMenuBar(menu);
    }

    @Override
    public void reactOnNotify()
    {
        state = game.getState();
        about.setVisible(false);
        gameOver.setVisible(false);
        highScore.setVisible(false);
        panelOverlay(true);

        if (state.getState() == StateEnum.ABOUT)
        {
            panelOverlay(false);
            about.setVisible(true);
        }
        else if (state.getState() == StateEnum.HIGHSCORE)
        {
            panelOverlay(false);
            highScore.setVisible(true);
        }
        else if (state.getState() == StateEnum.GAMEOVER)
        {
            panelOverlay(false);
            gameOver.setVisible(true);
            String userName = JOptionPane.showInputDialog("Enter your name:");
            userName = userName.replaceAll("\\s+","_");
            controller.execute(ComandStrategy.SAVESCORE, userName);
        }
    }
}

package com;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.model.StateEnum;
import com.model.TetrisGame;
import com.view.MainDrawer;

public class Main {
    public static void main(String[] args) {
        TetrisGame game = new TetrisGame(StateEnum.RUN);
        MainDrawer app = new MainDrawer(game);

        app.setFocusable(true);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                app.setVisible(true);
            }
        });
    }
}

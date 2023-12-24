// Breakout.java
package com.zetcode;

import javax.swing.*;
import java.awt.*;

public class Breakout extends JFrame {

    private Board board;

    public Breakout() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        StartPanel startPanel = new StartPanel(this);
        add(startPanel, BorderLayout.CENTER);

        setTitle("Breakout");
        setSize(Commons.WIDTH, Commons.HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void startGame() {
        getContentPane().removeAll();

        board = new Board();
        add(board);

        board.setFocusable(true);
        board.requestFocusInWindow();

        SoundManager.startBackgroundMusic();

        pack();
        setVisible(true);

        board.startGame();
    }

    public static void main(String[] args) {

            var game = new Breakout();
            game.setVisible(true);

    }
}

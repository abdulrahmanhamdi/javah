package com.zetcode;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {

    private Breakout breakout;

    public StartPanel(Breakout breakout) {
        this.breakout = breakout;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/resources/back2.jpg");
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        JButton startButton = new JButton("Start Game");
        Font font = new Font("Bell MT", Font.BOLD, 12);
        startButton.setFont(font);
        startButton.setBackground(Color.GREEN);
        //startButton.setBorder(BorderFactory.createLineBorder(Color.black,1));



        int buttonX = 220;
        int buttonY = 220;
        int buttonWidth = 100;
        int buttonHeight = 30;
        startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                breakout.startGame();
            }
        });

        ImageIcon logoIcon = new ImageIcon("src/resources/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBounds(30, 50, 500, 150);

        backgroundPanel.add(startButton);
        backgroundPanel.add(imageLabel);

        int levelButtonX = 220;
        int levelButtonY = 270;
        int levelButtonWidth = 100;
        int levelButtonHeight = 30;

        for (int i = 1; i <= 7; i++) {
            JButton levelButton = new JButton("Level " + i);
            levelButton.setFont(font);
            levelButton.setBackground(new Color(255, 234, 0));
            levelButton.setBounds(levelButtonX, levelButtonY + (i - 1) * 40, levelButtonWidth, levelButtonHeight);

            int finalI = i;
            levelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Selected Level: " + finalI);
                }
            });

            backgroundPanel.add(levelButton);
        }

        add(backgroundPanel, BorderLayout.CENTER);
    }
}

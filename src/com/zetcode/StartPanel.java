package com.zetcode;

import javax.swing.*;
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
        addBackgroundPanel();
    }

    private void addBackgroundPanel() {
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/resources/back2.jpg");
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        addLogoLabel(backgroundPanel);
        addLevelButtons(backgroundPanel);

        add(backgroundPanel, BorderLayout.CENTER);
    }

    private void addLogoLabel(JPanel panel) {
        ImageIcon logoIcon = new ImageIcon("src/resources/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBounds(30, 50, 500, 150);

        panel.add(imageLabel);
    }

    private void addLevelButtons(JPanel panel) {
        int levelButtonX = 220;
        int levelButtonY = 270;
        int levelButtonWidth = 100;
        int levelButtonHeight = 30;

        for (int i = 1; i <= 7; i++) {
            JButton levelButton = createLevelButton(i);
            levelButton.setBounds(levelButtonX, levelButtonY + (i - 1) * 40, levelButtonWidth, levelButtonHeight);
            panel.add(levelButton);
        }
    }

    private JButton createLevelButton(int level) {
        JButton levelButton = new JButton("Level " + level);
        Font font = new Font("Bell MT", Font.BOLD, 12);
        levelButton.setFont(font);
        levelButton.setBackground(new Color(255, 234, 0));

        levelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selected Level: " + level);
            }
        });

        return levelButton;
    }
}

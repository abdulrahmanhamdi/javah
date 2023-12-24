package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {

    private final Breakout breakout;

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
        addChooseLevel(backgroundPanel);

        add(backgroundPanel, BorderLayout.CENTER);
    }

    private void addChooseLevel(JPanel panel) {
        int rectangleX = 192;
        int rectangleY = 210;
        int rectangleWidth = 150;
        int rectangleHeight = 50;

        JLabel rectangleLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(17, 255, 71));
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.BLACK);
                Font font = new Font("Arial", Font.BOLD, 20);
                g.setFont(font);
                FontMetrics fontMetrics = g.getFontMetrics();
                int textX = (getWidth() - fontMetrics.stringWidth("Choose Level")) / 2;
                int textY = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
                g.drawString("Choose Level", textX, textY);
            }
        };

        rectangleLabel.setBounds(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
        panel.add(rectangleLabel);
    }


    private void addLogoLabel(JPanel panel) {
        ImageIcon logoIcon = new ImageIcon("src/resources/logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(500, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBounds(25, 50, 500, 150);

        panel.add(imageLabel);
    }

    private void addLevelButtons(JPanel panel) {
        int levelButtonX = 220;
        int levelButtonY = 270;
        int levelButtonWidth = 100;
        int levelButtonHeight = 30;

        int totalLevels = 7;

        for (int i = 1; i <= totalLevels; i++) {
            JButton levelButton = createLevelButton(i);
            levelButton.setBounds(levelButtonX, levelButtonY + (i - 1) * 40, levelButtonWidth, levelButtonHeight);
            panel.add(levelButton);
        }
    }


    private JButton createLevelButton(int level) {
        JButton levelButton = new JButton("Level " + level);
        Font font = new Font("Times New Roman", Font.BOLD, 16);

        levelButton.setFont(font);
        levelButton.setForeground(new Color(255, 255, 255) );
        levelButton.setBackground(new Color(255, 25, 129));


        levelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Selected Level: " + level);
                breakout.startGame(level);
            }
        });

        return levelButton;
    }
}

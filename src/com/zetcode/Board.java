package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel {

    private JButton tryAgainButton;

    private Timer timer;
    private Timer scoreTimer;
    private Image youWinImage;
    private Image youLoseImage;
    private Ball ball;
    public static Paddle paddle;
    private int levelNumber;
    private int[][] level;

    private int bricksNum=0;


    private Random random = new Random();

    private ArrayList<Brick> bricks = new ArrayList<>();

    private boolean inGame = true;
    private Image backgroundImage;

    private int currentLevel;
    private int score = 0;
    private int timerSeconds = 0;
    private boolean youWin = true;


    public Board(int level) {
        levelNumber =level;
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));

        backgroundImage = new ImageIcon("src/resources/back.jpg").getImage();
        youLoseImage = new ImageIcon("src/resources/you_lose.png").getImage();
        youWinImage = new ImageIcon("src/resources/you_win.png").getImage();

        gameInit();
    }

    private void gameInit() {
        ball = new Ball();
        Ball.balls.add(ball);
        paddle = new Paddle();
        Levels levelsList = new Levels();
        level = levelsList.getlevel(levelNumber);

        loadbricks(level);

        timer = new Timer(Commons.PERIOD, new GameCycle());
        timer.start();

        scoreTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerSeconds++;

            }
        });
        scoreTimer.start();
    }

    private void loadbricks(int[][] level){

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if ((level[i][j] != -1)) {
                    bricks.add( new Brick(j * 40 + 30, i* 10 + 50, level[i][j],
                            Commons.BRICK_IMAGES[level[i][j]]));

                }
                bricksNum++;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0, 0, Commons.WIDTH, Commons.HEIGHT, this);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {
            drawObjects(g2d);
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {
        for (int i = 0; i < Ball.balls.size(); i++) {
            g2d.drawImage(Ball.balls.get(i).getImage(), Ball.balls.get(i).getX(), Ball.balls.get(i).getY(),
                    Ball.balls.get(i).getImageWidth(), Ball.balls.get(i).getImageHeight(), this);
        }

        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < bricks.size(); i++) {
            if (!bricks.get(i).isDestroyed()) {
                g2d.drawImage(bricks.get(i).getImage(), bricks.get(i).getX(),
                        bricks.get(i).getY(), bricks.get(i).getImageWidth(),
                        bricks.get(i).getImageHeight(), this);
            }
        }

        for (int i = 0; i < Features.features.size(); i++) {
            Features.features.get(i).drawFeature(g2d);
            if (Features.features.get(i).getY() > Commons.BOTTOM_EDGE)
                Features.features.remove(i);
        }

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.drawString("Score: " + score, 20, 20);

        g2d.drawString("Time: " + timerSeconds + "s", Commons.WIDTH - 120, 20);
    }


    private void gameFinished(Graphics2D g2d) {
        int imageX1 = ((Commons.WIDTH - youWinImage.getWidth(this)) / 2);
        int imageY1 = (Commons.HEIGHT - youWinImage.getHeight(this)) / 2;
        int imageX2 = (Commons.WIDTH - youLoseImage.getWidth(this)) / 2;
        int imageY2 = (Commons.HEIGHT - youLoseImage.getHeight(this)) / 2;

        if (youWin) {
            g2d.drawImage(youWinImage, imageX1, imageY1, this);
            SoundManager.playVictorySound();
        } else {
            g2d.drawImage(youLoseImage, imageX2, imageY2, this);


            tryAgainButton = new JButton("Try Again");
            tryAgainButton.setForeground(new Color(255, 255, 255));
            tryAgainButton.setBackground(new Color(65, 72, 134));
            tryAgainButton.setFont(new Font("Arial", Font.PLAIN, 16));
            tryAgainButton.setBounds((Commons.WIDTH - 100) / 2, imageY2 + youLoseImage.getHeight(this) + 20, 120, 30);
            tryAgainButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    restartGame();
                }
            });
            add(tryAgainButton);
            SoundManager.playGameOverSound();
        }
    }

    private void restartGame() {
        inGame = true;
        youWin = true;
        score = 0;
        timerSeconds = 0;
        bricksNum = 0;
        bricks.clear();
        Features.features.clear();
        Ball.balls.clear();
        gameInit();
        repaint();
        remove(tryAgainButton);
    }


    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private void doGameCycle() {
        for (int i = 0; i < Ball.balls.size(); i++) {
            Ball.balls.get(i).move();
        }

        for (int i = 0; i < Features.features.size(); i++) {
            Features.features.get(i).move();
        }
        paddle.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {
        inGame = false;
        timer.stop();
        SoundManager.stopBackgroundMusic();
    }

    void controlFeature(int i){
        if (bricks.get(i).getcorr() != 0) {

            int Feature_random = random.nextInt(0, 15);
            if (Feature_random == 14 && !bricks.get(i).isDestroyed()) {
                Features.features.add(new ThreeBalls(bricks.get(i).getX() + Commons.FEATURE_WIDTH,
                        bricks.get(i).getY() + Commons.FEATURE_WIDTH));
            } else if (Feature_random == 13 && !bricks.get(i).isDestroyed() ) {
                Features.features.add(new TallerPaddle(bricks.get(i).getX() + Commons.FEATURE_WIDTH,
                        bricks.get(i).getY() + Commons.FEATURE_WIDTH));
            } else if (Feature_random == 12 && !bricks.get(i).isDestroyed() ) {
                Features.features.add(new SmallerPaddle(bricks.get(i).getX() + Commons.FEATURE_WIDTH,
                        bricks.get(i).getY() + Commons.FEATURE_WIDTH));
            } else if (Feature_random == 11 && !bricks.get(i).isDestroyed()) {
                Features.features.add(new FasterBalls(bricks.get(i).getX() + Commons.FEATURE_WIDTH,
                        bricks.get(i).getY() + Commons.FEATURE_WIDTH));
            }
        }

    }

    private void checkCollision() {
        if (Ball.balls.isEmpty())
            stopGame();

        for (int i = 0; i < Ball.balls.size(); i++) {
            if (Ball.balls.get(i).getY() > Commons.BOTTOM_EDGE) {
                Ball.balls.remove(i);
                continue;
            }

            if (Ball.balls.get(i).getX() <= 0) {
                Ball.balls.get(i).setXDir(-1 * Ball.balls.get(i).getXDir());
            }

            if (Ball.balls.get(i).getX() >= Commons.WIDTH - 16) {
                Ball.balls.get(i).setXDir(-1 * Ball.balls.get(i).getXDir());
            }

            if (Ball.balls.get(i).getY() <= 0) {
                Ball.balls.get(i).setYDir(-1 * Ball.balls.get(i).getYDir());
            }
        }
        if (Ball.balls.isEmpty()) {
            stopGame();
            youWin = false;
        }

        for (int i = 0, j = 0; i < bricks.size(); i++) {
            if (bricks.get(i).isDestroyed()) {
                j++;
            }

            if (j == bricks.size()) {
                stopGame();
                youWin = true;
            }
        }

        for (int i = 0; i < Ball.balls.size(); i++) {
            if ((Ball.balls.get(i).getRect()).intersects(paddle.getRect())) {
                int paddleLPos = (int) paddle.getRect().getMinX();
                int ballLPos = (int) ball.getRect().getMinX();
                int first = paddleLPos + 6;
                int second = paddleLPos + 16;
                int third = paddleLPos + 24;
                int fourth = paddleLPos + 34;

                if (ballLPos < first) {
                    Ball.balls.get(i).setXDir(random.nextInt(1,3));
                    Ball.balls.get(i).setYDir(random.nextInt(1,3) * (-1));
                }

                if (ballLPos >= first && ballLPos < second) {
                    Ball.balls.get(i).setXDir(random.nextInt(1,3) * (-1));
                    Ball.balls.get(i).setYDir(random.nextInt(1,3) * (-1));
                }

                if (ballLPos >= second && ballLPos < third) {
                    Ball.balls.get(i).setXDir(0);
                    Ball.balls.get(i).setYDir( -3);
                }

                if (ballLPos >= third && ballLPos < fourth) {
                    Ball.balls.get(i).setXDir(random.nextInt(1,3) * (-1));
                    Ball.balls.get(i).setYDir(random.nextInt(1,3) * (-1));
                }

                if (ballLPos > fourth) {
                    Ball.balls.get(i).setXDir(random.nextInt(1,3));
                    Ball.balls.get(i).setYDir(random.nextInt(1,3) * (-1));
                }
            }
        }

        for (int i = 0; i < bricks.size(); i++) {
            for (int j = 0; j < Ball.balls.size(); j++) {
                if (Ball.balls.get(j).getRect().intersects(bricks.get(i).getRect())) {

                    controlFeature(i);

                    int ballLeft = (int) Ball.balls.get(j).getRect().getMinX();
                    int ballHeight = (int) Ball.balls.get(j).getRect().getHeight();
                    int ballWidth = (int) Ball.balls.get(j).getRect().getWidth();
                    int ballTop = (int) Ball.balls.get(j).getRect().getMinY();

                    var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                    var pointLeft = new Point(ballLeft - 1, ballTop);
                    var pointTop = new Point(ballLeft, ballTop - 1);
                    var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                    if (!bricks.get(i).isDestroyed()) {
                        if (bricks.get(i).getRect().contains(pointRight)) {
                            Ball.balls.get(j).setXDir(-1 * Ball.balls.get(j).getXDir());
                        } else if (bricks.get(i).getRect().contains(pointLeft)) {
                            Ball.balls.get(j).setXDir(-1 * Ball.balls.get(j).getXDir());
                        }

                        if (bricks.get(i).getRect().contains(pointTop)) {
                            Ball.balls.get(j).setYDir(-1 * Ball.balls.get(j).getYDir());
                        } else if (bricks.get(i).getRect().contains(pointBottom)) {
                            Ball.balls.get(j).setYDir(-1 * Ball.balls.get(j).getYDir());
                        }
                        if(bricks.get(i).getcorr() != 0)
                            bricks.get(i).setDestroyed(true);
                        score+=5;
                    }
                }
            }
        }

        for (int i = 0; i < Features.features.size(); i++) {
            if ((Features.features.get(i).getRect()).intersects(paddle.getRect())) {
                Features.features.get(i).activateFeature();
                Features.features.remove(i);
            }
        }
    }
}


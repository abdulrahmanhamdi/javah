package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Level1 extends JPanel {

    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private Random random = new Random();
    ArrayList<Brick> bricks = new ArrayList<>();
    private boolean inGame = true;
    private Image backgroundImage;

    public Level1() {

        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));

        // Load background image
        backgroundImage = new ImageIcon("src/resources/back.jpg").getImage();

        gameInit();
    }

    private void gameInit() {


        ball = new Ball();
        paddle = new Paddle();

        Sprite.sprites.add(ball);

        int k = 0;

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 20; j++) {

                bricks.add(k, new Brick(j * 40 + 30, i * 10 + 50));
                k++;
            }
        }

        timer = new Timer(Commons.PERIOD, new GameCycle());
        timer.start();
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

        for (int i = 0; i < Sprite.sprites.size() ; i++) {
            g2d.drawImage(Sprite.sprites.get(i).getImage(), Sprite.sprites.get(i).getX(), Sprite.sprites.get(i).getY(),
                    Sprite.sprites.get(i).getImageWidth(), Sprite.sprites.get(i).getImageHeight(), this);
        }

        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < Commons.N_OF_BRICKS_lvl1; i++) {

            if (!bricks.get(i).isDestroyed()) {

                g2d.drawImage(bricks.get(i).getImage(), bricks.get(i).getX(),
                        bricks.get(i).getY(), bricks.get(i).getImageWidth(),
                        bricks.get(i).getImageHeight(), this);
            }
        }

        for (int i = 0; i < Features.features.size(); i++) {

            Features.features.get(i).drawFeature(g2d);
            Features.features.get(i).move();
        }
    }

    private void gameFinished(Graphics2D g2d) {

        var font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message,
                (Commons.WIDTH - fontMetrics.stringWidth(message)) / 2,
                Commons.WIDTH / 2);
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

        ball.move();
        paddle.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {

        inGame = false;
        timer.stop();
    }

    private void checkCollision() {

        if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {

            stopGame();
        }

        for (int i = 0, j = 0; i < Commons.N_OF_BRICKS_lvl1; i++) {

            if (bricks.get(i).isDestroyed()) {

                j++;
            }

            if (j == Commons.N_OF_BRICKS_lvl1) {

                message = "Victory";
                stopGame();
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())){


            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {

                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {

                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {

                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {

                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {

                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }



        for (int i = 0; i < Commons.N_OF_BRICKS_lvl1; i++) {

            if ((ball.getRect()).intersects(bricks.get(i).getRect())) {

                int Feature_random = random.nextInt(1,10);
                if( Feature_random >  9 && ! bricks.get(i).isDestroyed()){
                    Features.features.add(new ThreeBalls(bricks.get(i).getX() + Commons.FEATURE_WIDTH,
                                                         bricks.get(i).getY() + Commons.FEATURE_WIDTH));
                }else if(Feature_random > 7 && ! bricks.get(i).isDestroyed()) {
                    Features.features.add(new TallerPaddle(bricks.get(i).getX() + Commons.FEATURE_WIDTH,
                                                           bricks.get(i).getY() + Commons.FEATURE_WIDTH));
                }


                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                var pointLeft = new Point(ballLeft - 1, ballTop);
                var pointTop = new Point(ballLeft, ballTop - 1);
                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks.get(i).isDestroyed()) {

                    if (bricks.get(i).getRect().contains(pointRight)) {

                        ball.setXDir(-1);
                    } else if (bricks.get(i).getRect().contains(pointLeft)) {

                        ball.setXDir(1);
                    }

                    if (bricks.get(i).getRect().contains(pointTop)) {

                        ball.setYDir(1);
                    } else if (bricks.get(i).getRect().contains(pointBottom)) {

                        ball.setYDir(-1);
                    }

                    bricks.get(i).setDestroyed(true);
                }
            }
        }

        for (int i = 0; i < Features.features.size(); i++) {
            if((Features.features.get(i).getRect()).intersects(paddle.getRect()) ){

            }
        }

    }
}

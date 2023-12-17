package com.zetcode;

import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Ball extends Sprite {

    private int xdir;
    private int ydir;

    public static ArrayList<Ball> balls = new ArrayList<>();




    public Ball() {

        initBall();
    }

    public Ball(int xdir,int ydir, Ball ball) {

        initBall(xdir,ydir,ball);

    }

    private void initBall(int xdir,int ydir,Ball ball){
        this.xdir = xdir;
        this.ydir = ydir;

        this.x = ball.getX();
        this.y = ball.getY();

        loadImage();
        getImageDimensions();


    }

    private void initBall() {
        
        xdir = 1;
        ydir = -1;

        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage() {

        var ii = new ImageIcon("src/resources/ball1.png");
        image = ii.getImage();
    }

    void move() {

        x += xdir;
        y += ydir;

        if (x == 0) {

            setXDir(1);
        }

        if (x == Commons.WIDTH - imageWidth) {

            System.out.println(imageWidth);
            setXDir(-1);
        }

        if (y == 0) {

            setYDir(1);
        }
    }

    private void resetState() {

        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
    }

    void setXDir(int x) {

        xdir = x;
    }

    void setYDir(int y) {

        ydir = y;
    }

    int getYDir() {

        return ydir;
    }
}

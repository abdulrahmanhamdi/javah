package com.zetcode;

import java.awt.*;
import java.util.ArrayList;

public abstract class Features extends Sprite{

    public static ArrayList<Features> features = new ArrayList<>();

    public Features(int X, int Y){
        this.x = X;
        this.y = Y;
    }

    abstract void drawFeature(Graphics g);
    abstract void activateFeature();

    public void move(){
        this.y += 1;
    }

    Rectangle getRect(){
        return new Rectangle(x,y,Commons.FEATURE_WIDTH,Commons.FEATURE_WIDTH);
    }

}

class ThreeBalls extends Features {

    ThreeBalls(int X ,int Y){
        super(X, Y);

    }

     public void drawFeature(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);
        g2d.fillOval(getX(),getY(),Commons.FEATURE_WIDTH,Commons.FEATURE_WIDTH);


    }

    @Override
    void activateFeature() {
        int ball_num;
        if (Ball.balls.size() > Commons.BALLS_NUM)
            ball_num = Commons.BALLS_NUM;
        else{
            ball_num = Ball.balls.size();
        }
        for (int i = 0; i <ball_num; i++) {
            Ball.balls.add(new Ball(1,1,Ball.balls.get(i)));
            Ball.balls.add(new Ball(-1,-1,Ball.balls.get(i)));
        }

    }
}

class TallerPaddle extends Features{

    TallerPaddle(int X ,int Y){
        super(X, Y);
    }

    public void drawFeature(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.cyan);
        g2d.fillOval(getX(),getY(),Commons.FEATURE_WIDTH,Commons.FEATURE_WIDTH);
    }

    void activateFeature(){

    }
}

class SmallerPaddle extends Features{



    SmallerPaddle(int X ,int Y ){
        super(X,Y);
    }

    @Override
    void drawFeature(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.orange);
        g2d.fillOval(getX(),getY(),Commons.FEATURE_WIDTH,Commons.FEATURE_WIDTH);
    }

    void activateFeature(){

    }

}

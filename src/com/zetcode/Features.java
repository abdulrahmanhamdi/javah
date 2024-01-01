package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


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
        Random r = new Random();

        if (Ball.balls.size() < Commons.BALLS_NUM) {
            ball_num = Ball.balls.size();
            for (int i = 0; i <ball_num; i++) {
                Ball.balls.add(new Ball(r.nextInt(1,4), r.nextInt(-2,0),Ball.balls.get(i)));
                Ball.balls.add(new Ball(r.nextInt(1,4), r.nextInt(-2,0),Ball.balls.get(i)));

            }
        }


    }
}

class TallerPaddle extends Features {

    private Image tallpaddle = new ImageIcon("src/resources/paddle2.png").getImage();

    TallerPaddle(int X ,int Y){

        super(X, Y);

    }

    public void drawFeature(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.cyan);
        g2d.fillOval(getX(),getY(),Commons.FEATURE_WIDTH,Commons.FEATURE_WIDTH);
    }

    void activateFeature(){

        Board.paddle.setImage(tallpaddle);

    }
}

class SmallerPaddle extends Features{

    private Image smallpaddle = new ImageIcon("src/resources/paddle.png").getImage();

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
        Board.paddle.setImage(smallpaddle);

    }

}


class FasterBalls extends Features{

    FasterBalls(int X ,int Y ){
        super(X,Y);
    }

    @Override
    void drawFeature(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.GRAY);
        g2d.fillOval(getX(),getY(),Commons.FEATURE_WIDTH,Commons.FEATURE_WIDTH);
    }

    void activateFeature(){
        for (int i = 0; i < Ball.balls.size(); i++) {
            Ball.balls.get(i).setYDir(-2);
        }

    }


}

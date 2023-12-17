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

    public void move(){
        this.y += 1;
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
}

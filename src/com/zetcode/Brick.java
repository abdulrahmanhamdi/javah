package com.zetcode;

import javax.print.DocFlavor;
import javax.swing.ImageIcon;

public class Brick extends Sprite {

    private boolean destroyed;
    private String brickType; // Add a variable to store the brick type

    public Brick(int x, int y, String brickType) {
        this.brickType = brickType;
        initBrick(x, y);
    }

    private void initBrick(int x, int y) {
        this.x = x;
        this.y = y;

        destroyed = false;

        loadImage();
        getImageDimensions();
    }

    private void loadImage() {

        var ii = new ImageIcon(brickType);
        setImage(ii.getImage());
    }

    boolean isDestroyed() {
        return destroyed;
    }

    void setDestroyed(boolean val) {
        destroyed = val;
    }
}

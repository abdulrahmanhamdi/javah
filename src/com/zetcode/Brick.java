package com.zetcode;

import javax.swing.ImageIcon;

public class Brick extends Sprite {

    private boolean destroyed;
    private int brickType; // Add a variable to store the brick type

    public Brick(int x, int y, int brickType) {
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
        // Use the brickType to get the corresponding image path from BRICK_IMAGES
        var ii = new ImageIcon(Commons.BRICK_IMAGES[brickType]);
        image = ii.getImage();
    }

    boolean isDestroyed() {
        return destroyed;
    }

    void setDestroyed(boolean val) {
        destroyed = val;
    }
}

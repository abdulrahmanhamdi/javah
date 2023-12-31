package com.zetcode;

import javax.swing.ImageIcon;

public class Brick extends Sprite {

    private boolean destroyed;
    private String brickType;
    private int blockcorr;

    public Brick(int x, int y, int blockcorr ,String brickType) {
        this.brickType = brickType;
        this.x = x;
        this.y = y;
        this.blockcorr = blockcorr;

        destroyed = false;

        loadImage();
        getImageDimensions();
    }

    private void loadImage() {

        var ii = new ImageIcon(brickType);
        setImage(ii.getImage());
    }

    int getcorr(){
        return blockcorr;
    }

    boolean isDestroyed() {
        return destroyed;
    }

    void setDestroyed(boolean val) {
        destroyed = val;
    }
}

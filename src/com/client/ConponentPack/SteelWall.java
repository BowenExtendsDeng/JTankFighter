package com.client.ConponentPack;

import com.client.ClientUnit.ClientModel;
import java.awt.*;

public class SteelWall implements Actor {

    private final Image steelWall = Toolkit.getDefaultToolkit().getImage("image\\54.jpg");
    private int xPos;
    private int yPos;

    public boolean[] shape;

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }


    public SteelWall(int xPos, int yPos, int orientation) {
        this.xPos = xPos;
        this.yPos = yPos;
        shape = new boolean[4];

        if (orientation == 0) {
            shape[2] = true;
            shape[3] = true;
        }
        if (orientation == 1) {
            shape[0] = true;
            shape[1] = true;
        }
        if (orientation == 2) {
            shape[1] = true;
            shape[3] = true;
        }
        if (orientation == 3) {
            shape[0] = true;
            shape[2] = true;
        }
    }

    @Override
    public void draw(Graphics g) {
        boolean wallDestroyed = true;
        for (boolean b : shape) {
            if (!b) {
                wallDestroyed = false;
                break;
            }
        }
        if (wallDestroyed) {
            return;
        }

        g.drawImage(steelWall, xPos - 12, yPos - 12, null);
        g.setColor(new Color(128, 64, 0));
        if (shape[0]) {
            g.fillRect(xPos - 12, yPos - 12, 13, 13);
        }
        if (shape[1]) {
            g.fillRect(xPos, yPos - 12, 13, 13);
        }
        if (shape[2]) {
            g.fillRect(xPos - 12, yPos, 13, 13);
        }
        if (shape[3]) {
            g.fillRect(xPos, yPos, 13, 13);
        }
    }

    @Override
    public int getXPos() {
        return xPos;
    }

    @Override
    public int getYPos() {
        return yPos;
    }

    public String getType() {
        return "steelWall";
    }

}
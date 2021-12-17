package com.client.ConponentPack;

import com.client.ClientUnit.ClientModel;

import java.awt.*;

/**
 * 这个类代表除了墙和钢墙外所有其他对象
 * @author 26317
 */
public class NormalObject implements Actor {
    private final String type;
    private Image image;
    private final int xPos;
    private final int yPos;
    private final ClientModel gameModel;

    public NormalObject(int xPos, int yPos, ClientModel gameModel, String type, int imageIndex) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.gameModel = gameModel;
        this.type = type;
        if (imageIndex != -1) {
            image = gameModel.textures[imageIndex];
        }
    }

    @Override
    public  void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, xPos - 12, yPos - 12, null);
        } else {
            g.setColor(new Color(0, 225, 0));
            for (int i = yPos - 11; i <= yPos + 12; i += 5) {
                g.drawLine(xPos - 12, i, xPos + 12, i);
            }
            for (int i = xPos - 11; i <= xPos + 12; i += 5) {
                g.drawLine(i, yPos - 12, i, yPos + 12);
            }
            g.setColor(new Color(0, 128, 0));
            for (int i = yPos - 10; i <= yPos + 12; i += 5) {
                g.drawLine(xPos - 12, i, xPos + 12, i);
            }
            for (int i = xPos - 10; i <= xPos + 12; i += 5) {
                g.drawLine(i, yPos - 12, i, yPos + 12);
            }
        }

        if (!"river".equals(type) && !"grass".equals(type) && !"base".equals(type)) {

            gameModel.removeActor(this);
        }
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public String getType() {
        return type;
    }
}
package com.SourceUnit.ClientPack;

import java.awt.*;

/**
 * The type Client steel client wall.
 * 铁墙
 * @author chenhong
 */
public class ClientSteelClientWall extends ClientWall implements ClientGameComponent {

    /**
     * The Shape.
     */
    public boolean[] shape;

    /**
     * Instantiates a new Client steel client wall.
     * 构造函数初始化
     * @param xPos        the x pos
     * @param yPos        the y pos
     * @param orientation the orientation
     */
    public ClientSteelClientWall(int xPos, int yPos, int orientation) {
        super(xPos, yPos, orientation,Toolkit.getDefaultToolkit().getImage("image\\54.jpg"));
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

    /**
     * 绘制物体
     * @param g the g
     */
    @Override
    public void draw(Graphics g) {

        g.drawImage(super.getImage(), super.getX() - 12, super.getY() - 12, null);
        g.setColor(new Color(128, 64, 0));
        if (shape[0]) {
            g.fillRect(super.getX() - 12, super.getY() - 12, 13, 13);
        }
        if (shape[1]) {
            g.fillRect(super.getX(), super.getY() - 12, 13, 13);
        }
        if (shape[2]) {
            g.fillRect(super.getX() - 12, super.getY(), 13, 13);
        }
        if (shape[3]) {
            g.fillRect(super.getX(), super.getY(), 13, 13);
        }
    }

    /**
     * 获取坐标
     * @return xPos
     */
    @Override
    public int getX() {
        return 0;
    }

    /**
     * 获取坐标
     * @return yPos
     */
    @Override
    public int getY() {
        return 0;
    }

    /**
     * Gets type.
     * 获取物体类型
     * @return the type
     */
    public String getType() {
        return "steelWall";
    }

}
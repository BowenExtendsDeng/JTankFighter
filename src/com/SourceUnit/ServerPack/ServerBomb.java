package com.SourceUnit.ServerPack;

import com.CommunicateUnit.Instruction;
import com.UI.ServerDrawingPanel;
import com.ProcessUnit.ServerPack.ServerStatus;

import java.awt.*;

public class ServerBomb implements ServerGameComponent {

    private final Rectangle border = new Rectangle(0, 0, 0, 0);
    private final String size;
    private int inner, middle, outer, jumpDistance;
    private int xPos, yPos;
    private int animationTime;

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


    public ServerBomb(int a, int b, String size) {
        this.size = size;
        if ("big".equals(size)) {
            inner = 6;
            middle = 9;
            outer = 14;
            jumpDistance = 8;
            animationTime = 6;
        } else if ("small".equals(size)) {
            inner = 2;
            middle = 4;
            outer = 7;
            jumpDistance = 4;
            animationTime = 4;
        }

        xPos = a;
        yPos = b;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(xPos - outer, yPos - outer, 2 * outer, 2 * outer);
        g.setColor(Color.orange);
        g.fillOval(xPos - middle, yPos - middle, 2 * middle, 2 * middle);
        g.setColor(Color.yellow);
        g.fillOval(xPos - inner, yPos - inner, 2 * inner, 2 * inner);
    }

    @Override
    public void move() {
        if (ServerStatus.isGamePaused()) {
            Instruction.getFromSever().append("o").append(xPos).append(",").append(yPos).append(",")
                    .append(size).append(";");
            return;
        }

        animationTime--;
        if (animationTime < 0) {
            ServerDrawingPanel.removeActor(this);
            return;
        }
        xPos = xPos + (int) (Math.random() * jumpDistance) - (int) (Math.random() * jumpDistance);
        yPos = yPos + (int) (Math.random() * jumpDistance) - (int) (Math.random() * jumpDistance);

        //将变化写入输出行
        Instruction.getFromSever().append("o").append(xPos)
                .append(",").append(yPos).append(",").append(size).append(";");

    }

    @Override
    public Rectangle getBorder() {
        return border;
    }

    @Override
    public String getType() {
        return "bomb";
    }


    //未使用的方法
    @Override
    public Rectangle[] getDetailedBorder() {
        return null;
    }

    @Override
    public boolean wallDestroyed() {
        return false;
    }

}
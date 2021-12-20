package com.server.ComponentPack;
import com.ProcessUnit.Instruction;
import com.server.ServerUnit.ServerModel;

import java.awt.*;

public class Base implements GameComponent {

    private final Rectangle border;
    private Image base = Toolkit.getDefaultToolkit().getImage("image\\1.jpg");
    private int xPos, yPos;
    private ServerModel gameModel;
    private int steelWallTime;
    private boolean baseKilled;

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

    public ServerModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(ServerModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setSteelWallTime(int steelWallTime) {
        this.steelWallTime = steelWallTime;
    }

    public Base() {
        xPos = 260;
        yPos = 498;
        border = new Rectangle(xPos - 11, yPos - 11, 23, 23);

    }

    @Override
    public Rectangle getBorder() {

        return border;
    }

    public void doom() {
        base = ServerModel.textures[1];
        if (!baseKilled) {
            ServerModel.addActor(new Bomb(xPos, yPos, "big", gameModel));
        }
        baseKilled = true;

        //记录变化到输出行
        Instruction.getFromSever().append("b").append(xPos).append(",").append(yPos).append(",").append("1;");
    }

    @Override
    public void move() {
        if (steelWallTime == 600) {
            SteelWall temp = new SteelWall(248, 498, 2, gameModel);
            ServerModel.gameComponents[0] = temp;
            writeToOutputLine("s", temp.shape, 248, 498);

            temp = new SteelWall(273, 498, 3, gameModel);
            ServerModel.gameComponents[1] = temp;
            writeToOutputLine("s", temp.shape, 273, 498);

            temp = new SteelWall(248, 473, 1, gameModel);
            ServerModel.gameComponents[2] = temp;
            writeToOutputLine("s", temp.shape, 248, 473);

            temp = new SteelWall(273, 473, 1, gameModel);
            ServerModel.gameComponents[3] = temp;
            writeToOutputLine("s", temp.shape, 273, 473);
        }
        if (steelWallTime > 0) {
            steelWallTime--;
        }
        if (steelWallTime == 1) {
            Wall temp = new Wall(248, 498, 2, gameModel);
            ServerModel.gameComponents[0] = temp;
            writeToOutputLine("w", temp.shape, 248, 498);

            temp = new Wall(273, 498, 3, gameModel);
            ServerModel.gameComponents[1] = temp;
            writeToOutputLine("w", temp.shape, 273, 498);

            temp = new Wall(248, 473, 1, gameModel);
            ServerModel.gameComponents[2] = temp;
            writeToOutputLine("w", temp.shape, 248, 473);

            temp = new Wall(273, 473, 1, gameModel);
            ServerModel.gameComponents[3] = temp;
            writeToOutputLine("w", temp.shape, 273, 473);
        }
    }

    public void writeToOutputLine(String type, boolean[] shape, int xPos, int yPos) {
        //记录变化到输出行
        Instruction.getFromSever().append(type).append(xPos).append(",").append(yPos).append(",");
		for (boolean b : shape) {
			if (b) {
                Instruction.getFromSever().append("1");
            } else {
                Instruction.getFromSever().append("0");
            }
		}
        Instruction.getFromSever().append(";");
    }

    @Override
    public String getType() {
        return "base";
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(base, xPos - 12, yPos - 12, null);
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
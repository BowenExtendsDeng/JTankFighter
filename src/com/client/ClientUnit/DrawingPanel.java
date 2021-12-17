package com.client.ClientUnit;

import com.client.ConponentPack.Actor;

import javax.swing.*;
import java.awt.*;

/**
 * @author chenhong
 * 绘图面板类属于客户端程序
 */
public class DrawingPanel extends JPanel {
    private Image offScreenImage;

    public String[] messageQueue;
    public Actor[] drawingList;

    private boolean gameStarted;
    private int green, red, blue;
    private int P1Life, P2Life, P1Score, P2Score, EnemyLeft, LevelIndex;
    private final Image P1Image;
    private final Image P2Image;



    public void setEnemyLeft(int enemyLeft) {
        EnemyLeft = enemyLeft;
    }


    public void setLevelIndex(int levelIndex) {
        LevelIndex = levelIndex;
    }


    public void setP1Life(int p1Life) {
        P1Life = p1Life;
    }


    public void setP2Life(int p2Life) {
        P2Life = p2Life;
    }


    public void setP1Score(int p1Score) {
        P1Score = p1Score;
    }

    public void setP2Score(int p2Score) {
        P2Score = p2Score;
    }


    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public DrawingPanel() {
        P1Image = Toolkit.getDefaultToolkit().getImage("image\\" + 55 + ".jpg");
        P2Image = Toolkit.getDefaultToolkit().getImage("image\\" + 73 + ".jpg");
    }


    @Override
    public void paintComponent(Graphics g) {

        Graphics offScreenGraphics;
        if (offScreenImage == null) {

            offScreenImage = createImage(640, 550);
        }
        offScreenGraphics = offScreenImage.getGraphics();
        myPaint(offScreenGraphics);
        g.drawImage(offScreenImage, 0, 0, this);
    }

    public void myPaint(Graphics g) {
        super.paintComponent(g);

        if (gameStarted) {
            //画游戏信息
            g.setColor(new Color(81, 111, 230));
            g.drawString("第  " + LevelIndex + "  关", 527, 39);
            g.drawString("敌人数 =  " + EnemyLeft, 527, 79);

            g.setColor(Color.yellow);
            g.drawImage(P1Image, 520, 380, null);
            g.drawString("x", 555, 395);
            g.drawString(P1Life + "", 565, 396);
            String SCORE = "000000000" + P1Score;
            g.drawString("P1" + " 得分:" + "", 515, 370);
            g.drawString(SCORE.substring(SCORE.length() - 7) + "", 566, 370);

            g.setColor(Color.green);
            g.drawImage(P2Image, 520, 460, null);
            g.drawString("x", 555, 475);
            g.drawString(P2Life + "", 565, 476);
            SCORE = "000000000" + P2Score;
            g.drawString("P2" + " 得分:" + "", 515, 450);
            g.drawString(SCORE.substring(SCORE.length() - 7) + "", 566, 450);


            //绘制背景
            g.setColor(Color.blue);
            g.drawRect(10, 10, 501, 501);

            //绘制坦克等等
            if (drawingList != null) {
                for (Actor actor : drawingList) {
                    if (actor != null) {
                        actor.draw(g);
                    }
                }
            }

            //绘制获胜场景
            if (Level.getWinningCount() > 150) {


                int temp = Level.getWinningCount() - 150;
                if (temp * 10 > 300) {
                    temp = 30;
                }
                if (Level.getWinningCount() > 470) {
                    temp = 500 - Level.getWinningCount();
                }
                g.setColor(Color.gray);
                g.fillRect(11, 11, 500, temp * 10);
                g.fillRect(11, 500 - temp * 10, 500, (1 + temp) * 10 + 2);

                if (Level.getWinningCount() > 190 && Level.getWinningCount() < 470) {

                    if (Level.getWinningCount() > 400) {

                        red += (int) ((128 - red) * 0.2);
                        green += (int) ((128 - green) * 0.2);
                    }
                    g.setColor(new Color(red, green, blue));
                    g.drawString("过 关 了  ！", 240, 250);
                }
            } else {

                green = 23;
                red = 34;
                blue = 128;
            }
        }

        //消息
        g.setColor(new Color(255, 255, 255));
        if (messageQueue != null) {

            for (int i = 0; i < 8; i++) {

                if (messageQueue[i] != null) {
                    g.drawString(messageQueue[i], 5, 12 + i * 16);
                } else {
                    break;
                }
            }
        }

    }
}
package com.server.ServerUnit;

import com.server.ComponentPack.GameComponent;

import javax.swing.*;
import java.awt.*;

/**
 * @author chenhong
 */ //drawingPanel类属于服务器程序
public class DrawingPanel extends JPanel {

    private Image offScreenImage;
    //这些是指出在serverModel都是真实的东西的参考
    public static GameComponent[] gameComponents;
    private static boolean gameStarted;
    private static int green, red, blue;

    private static String[] messageQueue;
    private static int messageIndex;
    public DrawingPanel() {
    }



    public void setGameStarted(boolean newGameStarted) {
        gameStarted = newGameStarted;
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
            //制作背景
            g.setColor(Color.blue);
            g.drawRect(10, 10, 501, 501);

            //制作坦克等等
            if (gameComponents != null) {
                for (GameComponent gameComponent : gameComponents) {
                    if (gameComponent != null) {
                        gameComponent.draw(g);
                    }
                }
            }

            //制作级别关卡
            g.setColor(new Color(81, 111, 230));
            g.drawString("第  " + Level.getCurrentLevel() + "  关", 527, 39);
            g.drawString("敌人数 =  " + Level.getEnemyLeft(), 527, 79);

            //制作获胜场景
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

    //删除屏幕上最早的信息
    public static void removeMessage() {

        if (messageIndex == 0) {

            return;
        }

        messageIndex--;
        if (messageIndex >= 0) {

            System.arraycopy(messageQueue, 1, messageQueue, 0, messageIndex);
        }
        messageQueue[messageIndex] = null;

        //调用视图重绘屏幕如果比赛还没开始
        if (!Status.isGameStarted()) {

            ServerModel.getView().getMainPanel().repaint();
        }
    }

    //在屏幕上显示一行消息
    public static void addMessage(String message) {
        if (messageIndex < 8) {
            messageQueue[messageIndex] = message;
            messageIndex++;
        } else {
            System.arraycopy(messageQueue, 1, messageQueue, 0, 7);
            messageQueue[7] = message;
        }

        //调用视图重绘屏幕如果游戏有没有开始
        if (!Status.isGameStarted()) {
            ServerModel.getView().getMainPanel().repaint();
        }
    }

    public static int getMessageIndex() {
        return messageIndex;
    }

    public static void setMessageQueue(String[] messageQueue) {
        DrawingPanel.messageQueue = messageQueue;
    }

    public static void removeActor(GameComponent gameComponent) {
        for (int i = 0; i < gameComponents.length; i++) {
            if (gameComponents[i] == gameComponent) {
                gameComponents[i] = null;
                break;
            }
        }
    }

    public static void addActor(GameComponent gameComponent) {
        for (int i = 0; i < gameComponents.length; i++) {
            if (gameComponents[i] == null) {
                gameComponents[i] = gameComponent;
                break;
            }
        }
    }

    public static GameComponent[] getGameComponents() {
        return gameComponents;
    }

    public static void setGameComponents(GameComponent[] gameComponents) {
        DrawingPanel.gameComponents = gameComponents;
    }
}
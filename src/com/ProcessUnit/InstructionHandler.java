package com.ProcessUnit;//该类从服务器程序解码指令字符串,然后将字符串转换为真正的指令

import com.ProcessUnit.ClientPack.ClientLevel;
import com.ProcessUnit.ClientPack.ClientModel;
import com.ProcessUnit.ClientPack.ClientStatus;
import com.SourceUnit.ClientPack.*;
import com.UI.ClientDrawingPanel;

/**
 * @author chenhong
 * 由客户端程序可读
 */
public class InstructionHandler {

    public static void handleInstruction(String instruction) {
        if (instruction.length() == 0) {
            return;
        }

        int i = 0;
        while (i < instruction.length()) {
            StringBuilder perInstruction = new StringBuilder();

            //指令由”;“开头在instruction-string分离
            while (instruction.charAt(i) != CommandTable.COMMAND_SPLIT.charAt(0)) {
                perInstruction.append(instruction.charAt(i));
                i++;
            }

            //指令“L”开头是负载水平,其次是“L”数量水平指数
            if (CommandTable.LOAD_SIGN.equals(perInstruction.substring(0, 1))) {
                ClientLevel.loadLevel(Integer.parseInt(perInstruction.substring(1, 2)));
                return;
            }

            //指令“w”开头意味着一些事情改变了在墙上的对象
            if (CommandTable.TURN_UP.equals(perInstruction.substring(0, 1))) {
                int xPos;
                int yPos;
                boolean[] shape = new boolean[16];
                StringBuilder temp = new StringBuilder();
                int j = 1;
                //得到x的位置
                while (!",".equals(perInstruction.substring(j, j + 1))) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                xPos = Integer.parseInt(temp.toString());

                //得到y的位置
                temp = new StringBuilder();
                while (!",".equals(perInstruction.substring(j, j + 1))) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                yPos = Integer.parseInt(temp.toString());

                //墙的详细的边界
                for (int k = 0; k < 16; k++) {

                    shape[k] = "1".equals(perInstruction.substring(j, j + 1));
                    j++;
                }

                //执行指令
                for (int k = 0; k < ClientDrawingPanel.drawingList.length; k++) {
                    if (ClientDrawingPanel.drawingList[k] != null) {
                        if (ClientDrawingPanel.drawingList[k].getX() == xPos && ClientDrawingPanel.drawingList[k].getY() == yPos) {
                            ClientBrickClientWall tempClientBrickWall = new ClientBrickClientWall(xPos, yPos, 4);
                            tempClientBrickWall.shape = shape;
                            ClientDrawingPanel.drawingList[k] = tempClientBrickWall;
                        }
                    }
                }
            }

            //指令“s”开头意味着一些事情改变了一个铁墙对象
            if (CommandTable.BASE_SHIELD.equals(perInstruction.substring(0, 1))) {
                int xPos;
                int yPos;
                boolean[] shape = new boolean[4];
                StringBuilder temp = new StringBuilder();
                int j = 1;
                //得到x的位置
                while (!",".equals(perInstruction.substring(j, j + 1))) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                xPos = Integer.parseInt(temp.toString());

                //得到y的位置
                temp = new StringBuilder();
                while (!",".equals(perInstruction.substring(j, j + 1))) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                yPos = Integer.parseInt(temp.toString());

                //详细的钢墙边境
                for (int k = 0; k < 4; k++) {
                    shape[k] = "1".equals(perInstruction.substring(j, j + 1));
                    j++;
                }

                //执行指令
                for (int k = 0; k < ClientDrawingPanel.drawingList.length; k++) {
                    if (ClientDrawingPanel.drawingList[k] != null) {
                        if (ClientDrawingPanel.drawingList[k].getX() == xPos && ClientDrawingPanel.drawingList[k].getY() == yPos) {
                            ClientSteelClientWall tempWall = new ClientSteelClientWall(xPos, yPos, 4);
                            tempWall.shape = shape;
                            ClientDrawingPanel.drawingList[k] = tempWall;
                        }
                    }
                }
            }

            //指令“b”开头意味着基地已被摧毁
            if (CommandTable.BASE_DESTROYED.equals(perInstruction.substring(0, 1))) {
                ClientGameComponent actor = new ClientNormalObject(260, 498,  "base", 1);
                ClientDrawingPanel.drawingList[4] = actor;
            }

            //指令“n”开头显示正常的对象,如坦克、启动符号
            if ("n".equals(perInstruction.substring(0, 1))) {
                int xPos;
                int yPos;
                int textureIndex;
                StringBuilder temp = new StringBuilder();
                int j = 1;
                //得到x对象的位置
                while (!",".equals(perInstruction.substring(j, j + 1))) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                xPos = Integer.parseInt(temp.toString());

                //得到y对象的位置
                temp = new StringBuilder();
                while (!",".equals(perInstruction.substring(j, j + 1))) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                yPos = Integer.parseInt(temp.toString());

                //获得对象的纹理指数
                temp = new StringBuilder();
                while (j < perInstruction.length()) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                textureIndex = Integer.parseInt(temp.toString());

                //执行指令
                ClientDrawingPanel.addActor(new ClientNormalObject(xPos, yPos,"normal", textureIndex));
            }


            //指令“t”开头表明子弹
            if ("t".equals(perInstruction.substring(0, 1))) {
                int xPos;
                int yPos;
                int direction;
                StringBuilder temp = new StringBuilder();
                int j = 1;
                //得到x子弹的位置
                while (!",".equals(perInstruction.substring(j, j + 1))) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                xPos = Integer.parseInt(temp.toString());

                //得到y子弹的位置
                temp = new StringBuilder();
                while (!",".equals(perInstruction.substring(j, j + 1))) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                yPos = Integer.parseInt(temp.toString());

                //子弹的方向
                temp = new StringBuilder();
                while (j < perInstruction.length()) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                direction = Integer.parseInt(temp.toString());

                //执行指令
                ClientDrawingPanel.addActor(new ClientBullet(xPos, yPos, direction));
            }

            //指令“o”开头表示一个炸弹
            if (perInstruction.charAt(0) == 'o') {
                int xPos;
                int yPos;
                int size;
                StringBuilder temp = new StringBuilder();
                int j = 1;
                //得到x炸弹的位置
                while (perInstruction.charAt(j) != ',') {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                xPos = Integer.parseInt(temp.toString());

                //得到y炸弹的位置
                temp = new StringBuilder();
                while (perInstruction.charAt(j) != ',') {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                yPos = Integer.parseInt(temp.toString());

                //炸弹的大小
                temp = new StringBuilder();
                while (j < perInstruction.length()) {
                    temp.append(perInstruction.charAt(j));
                    j++;
                }
                if ("small".equals(temp.toString())) {
                    size = 1;
                } else {
                    size = 0;
                }
                //执行指令
                ClientDrawingPanel.addActor(new ClientBomb(xPos, yPos, size));
            }

            //指令“i”开头表明坦克盾牌
            if (perInstruction.charAt(0) == 'i') {
                int xPos;
                int yPos;
                StringBuilder temp = new StringBuilder();
                int j = 1;
                //得到x位置的盾牌
                while (perInstruction.charAt(j) != ',') {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                j++;
                xPos = Integer.parseInt(temp.toString());

                //得到y位置的盾牌
                temp = new StringBuilder();
                while (j < perInstruction.length()) {
                    temp.append(perInstruction.charAt(j));
                    j++;
                }
                yPos = Integer.parseInt(temp.toString());

                //执行指令
                ClientDrawingPanel.addActor(new ClientShield(xPos, yPos));
            }

            //指令“p”开头表示水平和玩家信息
            if (perInstruction.charAt(0) == 'p') {
                StringBuilder temp = new StringBuilder();
                int j = 1;
                //得到敌人离开的数量
                while (perInstruction.charAt(j) != ',') {
                    temp.append(perInstruction.charAt(j));
                    j++;
                }
                j++;
                ClientModel.getView().getMainPanel().setEnemyLeft(Integer.parseInt(temp.toString()));

                //得到水平指数
                temp = new StringBuilder();
                while (perInstruction.charAt(j) != ',') {
                    temp.append(perInstruction.charAt(j));
                    j++;
                }
                j++;
                ClientModel.getView().getMainPanel().setLevelIndex(Integer.parseInt(temp.toString()));

                //玩家1的生命量
                temp = new StringBuilder();
                while (perInstruction.charAt(j) != ',') {
                    temp.append(perInstruction.charAt(j));
                    j++;
                }
                j++;
                ClientModel.getView().getMainPanel().setP1Life(Integer.parseInt(temp.toString()));

                //玩家1的分数
                temp = new StringBuilder();
                while (perInstruction.charAt(j) != ',') {
                    temp.append(perInstruction.charAt(j));
                    j++;
                }
                j++;
                ClientModel.getView().getMainPanel().setP1Score(Integer.parseInt(temp.toString()));

                //玩家2的生命量
                temp = new StringBuilder();
                while (perInstruction.charAt(j) != ',') {
                    temp.append(perInstruction.charAt(j));
                    j++;
                }
                j++;
                ClientModel.getView().getMainPanel().setP2Life(Integer.parseInt(temp.toString()));

                //玩家2的分数
                temp = new StringBuilder();
                while (j < perInstruction.length()) {

                    temp.append(perInstruction.charAt(j));
                    j++;
                }
                ClientModel.getView().getMainPanel().setP2Score(Integer.parseInt(temp.toString()));
            }

            //指令“g”开头表明获取胜利的统计数量
            if (perInstruction.charAt(0) == 'g') {
                StringBuilder temp = new StringBuilder();
                int j = 1;
                //得到敌人离开的数量
                while (j < perInstruction.length()) {
                    temp.append(perInstruction.substring(j, j + 1));
                    j++;
                }
                ClientLevel.setWinningCount(Integer.parseInt(temp.toString()));
            }

            //指令“m”开头表示服务器玩家的信息
            if (perInstruction.charAt(0) == 'm') {
                ClientDrawingPanel.addMessage("主机端玩家说：" + perInstruction.substring(1, perInstruction.length()));
            }

            //指令“a”开头表示游戏结束
            if (perInstruction.charAt(0) == 'a') {
                if (!ClientStatus.isGameOver()) {
                    ClientDrawingPanel.addMessage("GAME OVER ! 　想再玩一次吗 ( y / n ) ?");
                    ClientStatus.setGameOver(true);
                }
            }
            //指令“j”开头表示服务器玩家想在玩一次
            if (perInstruction.charAt(0) == 'j') {
                if (ClientStatus.isGameOver()) {
                    ClientStatus.setServerVote(true);
                }
            }

            //指令“x”开头表示服务器玩家暂停游戏
            if (perInstruction.charAt(0) == 'x') {
                int temp = Integer.parseInt(perInstruction.substring(1, 2));
                if (temp == 0) {
                    if (ClientStatus.isGamePaused()) {
                        ClientDrawingPanel.addMessage("主机端玩家取消了暂停");
                        ClientStatus.setGamePaused(false);
                    }
                } else {
                    if (!ClientStatus.isGamePaused()) {
                        ClientDrawingPanel.addMessage("主机端玩家暂停了游戏");
                        ClientStatus.setGamePaused(true);
                    }
                }
            }
            i++;
        }
    }
}
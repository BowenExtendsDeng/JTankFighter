package com.SourceUnit.ServerPack;

import com.CommunicateUnit.Instruction;
import com.UI.ServerDrawingPanel;
import com.ProcessUnit.ServerPack.ServerLevel;
import com.ProcessUnit.ServerPack.ServerModel;
import com.ProcessUnit.ServerPack.ServerStatus;

import java.awt.*;

/**
 * 玩家类
 */
public class ServerPlayer implements ServerGameComponent {
    private final int UP = 0;
    private final int size = 12;
    private final Rectangle map = new Rectangle(35, 35, 452, 452);
    public int scores;
    private String type;
    private int life;
    private int speed;
    private int direction;
    private int invulnerableTime;
    private int frozen;
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean fire;
    private int numberOfBullet;
    private int coolDownTime;
    private int status;
    private int health;
    private int xPos, yPos, xVPos, yVPos;
    private Rectangle border;
    private final Image standardImage;
    public Image[] textures;

    /**
     * 设置左移
     * @param moveLeft move left
     */
    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    /**
     * 设置右移
     * @param moveRight move right
     */
    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    /**
     * 设置开火
     * @param fire fire
     */
    public void setFire(boolean fire) {
        this.fire = fire;
    }

    /**
     * 获取子弹数量
     * @return the number
     */
    public int getNumberOfBullet() {
        return numberOfBullet;
    }

    /**
     * 设置子弹数量
     * @param numberOfBullet the number
     */
    public void setNumberOfBullet(int numberOfBullet) {
        this.numberOfBullet = numberOfBullet;
    }

    /**
     * 获取x坐标
     * @return x pos
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * 设置x坐标
     * @param xPos x pos
     */
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * 获取y坐标
     * @return y pos
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * 设置y坐标
     * @param yPos y pos
     */
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * 设置类型
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取生命
     * @return the life
     */
    public int getLife() {
        return life;
    }

    /**
     * 冰冻
     * @return forzen
     */
    public int getFrozen() {
        return frozen;
    }

    /**
     * 设置冰冻
     * @param frozen frozen
     */
    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }

    /**
     * 设置移动
     * @param moveUp move up
     */
    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    /**
     * 设置移动
     * @param moveDown move down
     */
    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    /**
     * 初始化属性
     * @param type type
     */
    public ServerPlayer(String type) {
        this.type = type;
        life = 3;
        direction = UP;
        status = 1;
        health = 1;
        numberOfBullet = 1;
        invulnerableTime = 150;

        textures = new Image[4];
        if ("1P".equals(type)) {
            //玩家1 游戏开启时位置
            xPos = 198;
            yPos = 498;
            //玩家1 的图像
            System.arraycopy(ServerModel.textures, 54, textures, 0, 4);
        } else {
            //玩家2 游戏开启时位置
            xPos = 323;
            yPos = 498;
            //玩家2的图像
            System.arraycopy(ServerModel.textures, 72, textures, 0, 4);
        }
        standardImage = textures[0];

        xVPos = xPos;
        yVPos = yPos;
        border = new Rectangle(xPos - size, yPos - size, 25, 25);

    }

    /**
     * 玩家开火处理
     */
    @Override
    public void move() {
        if (ServerStatus.isGamePaused()) {
            writeToOutputLine();
            return;
        }

        if (coolDownTime > 0) {
            coolDownTime--;
        }
        if (invulnerableTime > 0) {
            invulnerableTime--;
        }

        if (frozen == 1) {
            writeToOutputLine();
            return;
        }

        //如果玩家点击“开火”键，并且满足条件，则创建一个子弹目标（即发射子弹）
        int DOWN = 1;
        int LEFT = 2;
        if (fire && coolDownTime == 0 && numberOfBullet > 0) {
            //子弹方向
            int c = direction;
            //子弹位置
            int a, b;
            if (direction == UP) {
                a = xPos;
                b = yPos - size;
            } else if (direction == DOWN) {
                a = xPos;
                b = yPos + size;
            } else if (direction == LEFT) {
                a = xPos - size;
                b = yPos;
            } else {
                a = xPos + size;
                b = yPos;
            }
            //子弹速度
            int d;
            if (status == 1) {
                numberOfBullet = 1;
                d = 7;
            } else {
                d = 12;
            }
            //子弹能力
            int e;
            if (status == 4) {
                e = 2;
            } else {
                e = 1;
            }
            //添加子弹
            ServerDrawingPanel.addActor(new ServerBullet(a, b, c, d, e, this));
            //coolDownTime是你要等到你可以发射第二颗子弹时间（与魔兽争霸3相同）
            if (status > 2) {
                coolDownTime = 5;
            } else {
                coolDownTime = 8;
            }
            //减少子弹的可用数，子弹发射时numberOfBullet会增加
            //由玩家的坦克击中目标（例如，墙壁，敌人坦克等）；
            numberOfBullet--;
        }


        //保存当前位置信息，如果新的移动确定后无效，则更改
        //以前的位置
        int xPosTemp = xPos;
        int yPosTemp = yPos;
        Rectangle borderTemp = new Rectangle(xPosTemp - size, yPosTemp - size, 25, 25);

        //根据玩家坦克的移动定义玩家坦克的下一个边界，假设它的下一个移动是有效的；
        boolean notMoving = false;
        int RIGHT = 3;
        if (moveUp) {
            if (direction != UP && direction != DOWN) {
                xPos = xVPos;
            }
            yPos -= speed;
            direction = UP;
        } else if (moveDown) {
            if (direction != UP && direction != DOWN) {
                xPos = xVPos;
            }
            yPos += speed;
            direction = DOWN;
        } else if (moveLeft) {
            if (direction != LEFT && direction != RIGHT) {
                yPos = yVPos;
            }
            xPos -= speed;
            direction = LEFT;
        } else if (moveRight) {
            if (direction != LEFT && direction != RIGHT) {
                yPos = yVPos;
            }
            xPos += speed;
            direction = RIGHT;
        } else {
            notMoving = true;
        }
        if (notMoving) {
            if (speed > 0) {
                speed--;
            }
        } else {
            if (speed < 3) {
                speed++;
            }
        }

        //更新边界
        border.y = yPos - size;
        border.x = xPos - size;

        //检查下一个边界是否与地图边界相交，如果不移动到任何地方
        if (!border.intersects(map)) {
            xPos = xVPos;
            yPos = yVPos;
            border.x = xPos - size;
            border.y = yPos - size;
            writeToOutputLine();
            return;
        }


        //检查下个边界是否与其他对象相交，如玩家控制的坦克，墙等等
        for (int i = 0; i < ServerDrawingPanel.serverGameComponents.length; i++) {
            if (ServerDrawingPanel.serverGameComponents[i] != null) {
                if (this != ServerDrawingPanel.serverGameComponents[i]) {
                    if (border.intersects(ServerDrawingPanel.serverGameComponents[i].getBorder())) {
                        if ("powerUp".equals(ServerDrawingPanel.serverGameComponents[i].getType())) {
                            scores += 50;
                            ServerPowerUp temp = (ServerPowerUp) ServerDrawingPanel.serverGameComponents[i];
                            int function = temp.getFunction();
                            if (function == 0) {  //普通星星，增加速度
                                upgrade();
                            } else if (function == 1) {  //钢墙保护基地
                                ServerBase tempe = (ServerBase) ServerDrawingPanel.serverGameComponents[4];
                                tempe.setSteelWallTime(600);
                            } else if (function == 2) {   // 杀死所有的敌方坦克
                                for (int j = 0; j < ServerDrawingPanel.serverGameComponents.length; j++) {
                                    if (ServerDrawingPanel.serverGameComponents[j] != null) {
                                        if ("enemy".equals(ServerDrawingPanel.serverGameComponents[j].getType())) {
                                            ServerEnemy tempe = (ServerEnemy) ServerDrawingPanel.serverGameComponents[j];
                                            ServerDrawingPanel.addActor(new ServerBomb(tempe.getxPos(), tempe.getyPos(), "big"));
                                            ServerDrawingPanel.removeActor(ServerDrawingPanel.serverGameComponents[j]);
                                        }
                                    }
                                }
                                ServerLevel.setNoOfEnemy(0);
                                ServerLevel.setDeathCount(20 - ServerLevel.getEnemyLeft());
                            } else if (function == 3) {   //防护盾，刀枪不入
                                invulnerableTime = 300 + (int) (Math.random() * 400);
                            } else if (function == 4) {  //冻结所有敌人
                                ServerEnemy.setFrozenTime(300 + (int) (Math.random() * 400));
                                ServerEnemy.setFrozenMoment(ServerModel.getGameFlow());
                            } else if (function == 5) { //超级星星
                                if (status < 3) {
                                    numberOfBullet++;
                                }
                                status = 4;
                                health = 2;
                                if ("1P".equals(type)) {
                                    System.arraycopy(ServerModel.textures, 66, textures, 0, 4);
                                } else {
                                    System.arraycopy(ServerModel.textures, 84, textures, 0, 4);
                                }
                            } else if (function == 6) {  // 增加生命
                                life++;
                            }

                            ServerDrawingPanel.removeActor(ServerDrawingPanel.serverGameComponents[i]);

                        }
                        //静态对象，如墙壁，河流
                        else if ("steelWall".equals(ServerDrawingPanel.serverGameComponents[i].getType()) || "wall".equals(ServerDrawingPanel.serverGameComponents[i].getType())) {
                            if (!ServerDrawingPanel.serverGameComponents[i].wallDestroyed()) {
                                for (int j = 0; j < ServerDrawingPanel.serverGameComponents[i].getDetailedBorder().length; j++) {
                                    if (ServerDrawingPanel.serverGameComponents[i].getDetailedBorder()[j] != null) {
                                        if (ServerDrawingPanel.serverGameComponents[i].getDetailedBorder()[j].intersects(border)) {
                                            xPos = xVPos;
                                            yPos = yVPos;
                                            border.x = xPos - size;
                                            border.y = yPos - size;
                                            writeToOutputLine();
                                            return;
                                        }
                                    }
                                }
                            }
                        } else if ("river".equals(ServerDrawingPanel.serverGameComponents[i].getType()) || "base".equals(ServerDrawingPanel.serverGameComponents[i].getType())) {
                            xPos = xVPos;
                            yPos = yVPos;
                            border.x = xPos - size;
                            border.y = yPos - size;
                            writeToOutputLine();
                            return;
                        }
                        //移动对象，例如敌人坦克
                        else if ("enemy".equals(ServerDrawingPanel.serverGameComponents[i].getType()) || "Player".equals(ServerDrawingPanel.serverGameComponents[i].getType())) {
                            if (!borderTemp.intersects(ServerDrawingPanel.serverGameComponents[i].getBorder()) || "enemy".equals(ServerDrawingPanel.serverGameComponents[i].getType())) {
                                xPos = xPosTemp;
                                yPos = yPosTemp;
                                border.x = xPos - size;
                                border.y = yPos - size;
                                writeToOutputLine();
                                return;
                            }
                        }
                    }
                }
            }
        }

        //找到坦克的虚拟位置，当90度转弯时，虚拟位置用来调整坦克的真实位置。
        int a = (xPos - 10) / 25;
        int b = (xPos - 10) % 25;
        if (b < 7) {
            b = 0;
        }
        if (b > 18) {
            b = 25;
        }
        if ((b < 19 && b > 6) || xPos < 17 || xPos > 492) {
            b = 13;
        }
        xVPos = a * 25 + b + 10;
        int c = (yPos - 10) / 25;
        int d = (yPos - 10) % 25;
        if (d < 7) {
            d = 0;
        }
        if (d > 18) {
            d = 25;
        }
        if ((d < 19 && d > 6) || yPos < 17 || yPos > 492) {
            d = 13;
        }
        yVPos = c * 25 + d + 10;

        writeToOutputLine();
    }

    /**
     * 记录变化
     */
    public void writeToOutputLine() {
        //将变化写入输出行
        Instruction.getFromSever().append("n").append(xPos).append(",").append(yPos).append(",");
        int textureIndex;
        if ("1P".equals(type)) {
            if (status == 1) {
                textureIndex = 54 + direction;
            } else if (status == 2) {
                textureIndex = 58 + direction;
            } else if (status == 3) {
                textureIndex = 62 + direction;
            } else {
                textureIndex = 66 + direction;
            }
        } else {
            if (status == 1) {
                textureIndex = 72 + direction;
            } else if (status == 2) {
                textureIndex = 76 + direction;
            } else if (status == 3) {
                textureIndex = 80 + direction;
            } else {
                textureIndex = 84 + direction;
            }
        }

        Instruction.getFromSever().append(textureIndex).append(";");

        if (invulnerableTime > 0) {
            Instruction.getFromSever().append("i").append(xPos).append(",").append(yPos).append(";");
        }
    }

    /**
     * 绘制
     * @param g draw
     */
    @Override
    public void draw(Graphics g) {
        //绘制玩家坦克
        g.drawImage(textures[direction], xPos - size, yPos - size, null);
        if (invulnerableTime > 0) {
            g.setColor(Color.red);
            g.drawRect(xPos - 12, yPos - 12, 25, 25);
            g.drawRect(xPos - 11, yPos - 11, 23, 23);
        }

        //关于玩家的信息，如分数，生命等
        if ("1P".equals(type)) {
            g.setColor(Color.yellow);
            g.drawImage(standardImage, 520, 380, null);
            g.drawString("x", 555, 395);
            g.drawString(life + "", 565, 396);
            String SCORE = "000000000" + scores;
            g.drawString(type + " 得分:" + "", 515, 370);
            g.drawString(SCORE.substring(SCORE.length() - 7) + "", 566, 370);
        }
        if ("2P".equals(type)) {
            g.setColor(Color.green);
            g.drawImage(standardImage, 520, 460, null);
            g.drawString("x", 555, 475);
            g.drawString(life + "", 565, 476);
            String SCORE = "000000000" + scores;
            g.drawString(type + " 得分:" + "", 515, 450);
            g.drawString(SCORE.substring(SCORE.length() - 7) + "", 566, 450);
        }


    }

    /**
     * 获取边界
     * @return border
     */
    @Override
    public Rectangle getBorder() {
        return border;
    }

    /**
     * 获取类型
     * @return player
     */
    @Override
    public String getType() {
        return "Player";
    }

    /**
     * 受伤
     */
    public void hurt() {
        if (invulnerableTime != 0) {
            return;
        }

        //如果坦克只有1级的健康状态，被击中，那么玩家坦克失去一个生命，如果玩家坦克是最后一次生命，被击中，则game over
        //只有吃掉超级星星时，玩家才会有2级的生命健康状态
        if (health == 1) {
            ServerDrawingPanel.addActor(new ServerBomb(xPos, yPos, "big"));
            life--;
            if (life == 0) {
                xPos = 100000;
                yPos = 100000;           //this will make the player never come back to the main screen, thus looks like "dead"
                border = new Rectangle(xPos - size, yPos - size, 25, 25);
                xVPos = xPos;
                yVPos = yPos;
            } else {
                direction = UP;
                status = 1;
                health = 1;
                numberOfBullet = 1;
                invulnerableTime = 150;
                if ("1P".equals(type)) {
                    xPos = 198;
                    yPos = 498;
                    border = new Rectangle(xPos - size, yPos - size, 25, 25);
                    xVPos = xPos;
                    yVPos = yPos;
                    System.arraycopy(ServerModel.textures, 54, textures, 0, 4);
                } else {
                    xPos = 323;
                    yPos = 498;
                    border = new Rectangle(xPos - size, yPos - size, 25, 25);
                    xVPos = xPos;
                    yVPos = yPos;
                    System.arraycopy(ServerModel.textures, 72, textures, 0, 4);
                }
            }
        } else {
            health--;
            status = 3;
            if ("1P".equals(type)) {
                System.arraycopy(ServerModel.textures, 62, textures, 0, 4);
            } else {
                System.arraycopy(ServerModel.textures, 80, textures, 0, 4);
            }
        }
    }

    /**
     * 道具判断
     */
    public void upgrade() {
        //当玩家坦克吃掉正常的星星时，他的子弹将会升级
        if ("1P".equals(type)) {
            if (status == 1) {
                status = 2;
                System.arraycopy(ServerModel.textures, 58, textures, 0, 4);
            } else if (status == 2) {
                status = 3;
                numberOfBullet++;
                System.arraycopy(ServerModel.textures, 62, textures, 0, 4);
            } else if (status == 3) {
                status = 4;
                System.arraycopy(ServerModel.textures, 66, textures, 0, 4);
            }
        } else {
            if (status == 1) {
                status = 2;
                System.arraycopy(ServerModel.textures, 76, textures, 0, 4);
            } else if (status == 2) {
                status = 3;
                numberOfBullet++;
                System.arraycopy(ServerModel.textures, 80, textures, 0, 4);
            } else if (status == 3) {
                status = 4;
                System.arraycopy(ServerModel.textures, 84, textures, 0, 4);
            }
        }
    }

    /**
     * 重置
     */
    public void reset() {
        direction = UP;
        invulnerableTime = 150;
        if ("1P".equals(type)) {
            xPos = 198;
        } else {
            xPos = 323;
        }
        yPos = 498;

        xVPos = xPos;
        yVPos = yPos;
        border = new Rectangle(xPos - size, yPos - size, 25, 25);
    }

    /**
     * 未使用方法
     * @return null
     */
    @Override
    public Rectangle[] getDetailedBorder() {
        return null;
    }

    /**
     * 击毁墙处理
     * @return false
     */
    @Override
    public boolean wallDestroyed() {
        return false;
    }

}

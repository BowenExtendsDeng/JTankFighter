package com.server.ServerUnit;

import com.server.ComponentPack.*;

/**
 * @author chenhong
 */ //服务器端的类
//因为只有一层对象,所以在这个类是一个静态变量
public class Level {
    private static int currentLevel = 0;
    private static int enemySpawnTime = 150;
    private static int enemyLeft = 20;
    private static int deathCount = 0;
    private static int maxNoEnemy = 3;
    private static int NoOfEnemy = 0;
    public static int[] enemySequence;

    //制作获胜场景所需的变量
    private static int winningCount;

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static int getEnemyLeft() {
        return enemyLeft;
    }

    public static int getDeathCount() {
        return deathCount;
    }

    public static void setDeathCount(int deathCount) {
        Level.deathCount = deathCount;
    }

    public static int getNoOfEnemy() {
        return NoOfEnemy;
    }

    public static void setNoOfEnemy(int noOfEnemy) {
        NoOfEnemy = noOfEnemy;
    }

    public static int getWinningCount() {
        return winningCount;
    }

    public static void setWinningCount(int winningCount) {
        Level.winningCount = winningCount;
    }

    public static void loadLevel(ServerModel gameModel) {
        //增加关卡数量
        currentLevel++;

        //每次加载一个新的关卡将增加难度
        if (enemySpawnTime > 30) {
            enemySpawnTime -= 10;
        }
        if (maxNoEnemy < 10 && (currentLevel % 2 == 0)) {
            maxNoEnemy++;
        }

        //从上个关卡清除所有东西
        for (int i = 0; i < 400; i++) {
            ServerModel.gameComponents[i] = null;
        }

        //启动时各关卡共享
        enemyLeft = 20;

        //加载基地，每个关卡都一样
        ServerModel.gameComponents[0] = new Wall(248, 498, 2, gameModel);
        ServerModel.gameComponents[1] = new Wall(273, 498, 3, gameModel);
        ServerModel.gameComponents[2] = new Wall(248, 473, 1, gameModel);
        ServerModel.gameComponents[3] = new Wall(273, 473, 1, gameModel);
        ServerModel.gameComponents[4] = new Base();

        //加载一个关卡
        if (1 + (currentLevel - 1) % 8 == 1) {
            enemySequence = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2};
            String[] level = new String[]{
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "ss", "ss", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "s0", "##", "##", "__", "__", "__", "__", "__", "##", "s0", "s0", "##", "__", "__", "__", "__", "__", "##", "##", "s0",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "##", "##", "__", "__", "__", "__", "__", "##", "__", "__", "##", "__", "__", "__", "__", "__", "##", "##", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__"
            };
            loadLevel(gameModel, level);
        }

        if (1 + (currentLevel - 1) % 8 == 2) {
            enemySequence = new int[]{1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 3, 3, 3, 3};
            String[] level = new String[]{
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$",
                    "$$", "==", "==", "==", "==", "==", "==", "==", "$$", "$$", "==", "==", "==", "==", "==", "==", "==", "==", "==", "$$",
                    "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "ss", "##", "##", "##", "##", "##", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "ss", "##", "##", "##", "__", "__", "ss", "ss", "##", "##", "##", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "##", "__", "__", "__", "__", "__", "__", "##", "__", "__", "__", "__", "__", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "__", "__", "__", "__", "__", "##", "==", "==", "==", "==", "==", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "__", "__", "__", "__", "__", "##", "==", "==", "==", "==", "==", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "##", "##", "##", "##", "##", "##", "==", "==", "==", "==", "==", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "##", "##", "##", "##", "##", "##", "==", "==", "==", "==", "==", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "##", "##", "##", "##", "##", "##", "==", "==", "==", "==", "==", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "##", "##", "##", "##", "##", "##", "==", "==", "==", "==", "==", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "##", "##", "##", "##", "##", "##", "==", "==", "==", "==", "==", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "__", "__", "__", "__", "__", "##", "==", "==", "==", "==", "==", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "__", "__", "__", "__", "__", "##", "==", "==", "==", "==", "==", "__",
                    "__", "$$", "$$", "$$", "$$", "$$", "##", "__", "__", "__", "__", "__", "__", "##", "==", "==", "==", "==", "==", "__"
            };
            loadLevel(gameModel, level);
        }

        if (1 + (currentLevel - 1) % 8 == 3) {
            enemySequence = new int[]{1, 1, 1, 2, 2, 2, 4, 4, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 4, 4};
            String[] level = new String[]{
                    "__", "__", "__", "ss", "__", "__", "ss", "__", "__", "__", "__", "__", "__", "__", "__", "__", "s3", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "ss", "##", "##", "##", "__", "##", "##", "##", "##", "__", "s3", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "s3", "__", "__", "__",
                    "__", "__", "ss", "ss", "__", "##", "##", "##", "##", "__", "__", "##", "##", "__", "##", "##", "##", "__", "__", "__",
                    "__", "__", "__", "ss", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "##", "__", "##", "s1",
                    "__", "__", "__", "ss", "__", "##", "__", "ss", "##", "ss", "##", "ss", "##", "##", "##", "##", "##", "__", "__", "##",
                    "__", "__", "__", "ss", "__", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "__", "__", "__",
                    "__", "__", "__", "ss", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "__", "__", "__",
                    "__", "__", "__", "##", "ss", "ss", "ss", "ss", "##", "##", "##", "##", "##", "$$", "$$", "$$", "$$", "##", "##", "##",
                    "##", "__", "__", "##", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "__", "__", "__",
                    "##", "##", "__", "##", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "__", "__", "__",
                    "##", "##", "##", "##", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "##", "##", "##", "##", "##", "__", "__", "__",
                    "##", "##", "##", "##", "##", "##", "$$", "##", "$$", "##", "$$", "$$", "$$", "$$", "$$", "##", "__", "__", "__", "__",
                    "##", "##", "##", "##", "$$", "$$", "$$", "$$", "$$", "##", "$$", "$$", "$$", "$$", "$$", "##", "__", "__", "__", "__",
                    "#0", "s0", "s0", "s0", "$$", "ss", "ss", "ss", "ss", "##", "ss", "ss", "$$", "ss", "ss", "##", "##", "##", "ss", "##",
                    "__", "__", "__", "##", "__", "__", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "##",
                    "__", "__", "__", "##", "__", "__", "__", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$",
                    "__", "__", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__"
            };
            loadLevel(gameModel, level);
        }

        if (1 + (currentLevel - 1) % 8 == 4) {
            enemySequence = new int[]{3, 3, 3, 3, 2, 2, 2, 3, 3, 1, 1, 1, 3, 3, 3, 1, 1, 4, 4, 4};
            String[] level = new String[]{
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "__", "__", "__", "__", "__",
                    "__", "__", "__", "##", "##", "##", "##", "##", "##", "##", "##", "##", "ss", "##", "##", "##", "##", "__", "__", "__",
                    "__", "__", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "ss", "ss", "s2", "__", "__",
                    "__", "__", "##", "##", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "s2", "__", "__",
                    "__", "__", "##", "##", "##", "$$", "$$", "$$", "$$", "$$", "##", "$$", "$$", "$$", "$$", "$$", "$$", "s2", "__", "__",
                    "__", "##", "##", "##", "##", "##", "$$", "$$", "$$", "##", "$$", "##", "$$", "$$", "$$", "$$", "$$", "s2", "__", "__",
                    "__", "##", "##", "##", "##", "##", "$$", "$$", "##", "$$", "$$", "$$", "##", "$$", "$$", "$$", "$$", "s2", "__", "__",
                    "__", "##", "##", "##", "##", "##", "$$", "##", "$$", "$$", "$$", "$$", "$$", "##", "$$", "$$", "$$", "s2", "__", "__",
                    "__", "##", "##", "##", "##", "##", "$$", "$$", "##", "$$", "$$", "$$", "##", "$$", "$$", "$$", "$$", "s2", "__", "__",
                    "__", "##", "##", "##", "##", "$$", "$$", "$$", "$$", "##", "$$", "##", "$$", "$$", "$$", "$$", "##", "s2", "__", "__",
                    "__", "__", "##", "##", "$$", "$$", "$$", "$$", "$$", "$$", "##", "$$", "$$", "$$", "$$", "$$", "##", "##", "__", "__",
                    "__", "__", "##", "$$", "$$", "$$", "$$", "$$", "$$", "##", "##", "$$", "$$", "$$", "$$", "##", "##", "s0", "s0", "s0",
                    "__", "__", "##", "##", "##", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "##", "##", "__", "__", "__", "__",
                    "__", "__", "__", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__"
            };
            loadLevel(gameModel, level);
        }

        if (1 + (currentLevel - 1) % 8 == 5) {
            enemySequence = new int[]{2, 2, 2, 3, 3, 3, 2, 2, 2, 4, 4, 4, 3, 3, 3, 3, 3, 2, 2, 2};
            String[] level = new String[]{
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "==", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "==", "==", "==", "__", "__", "==", "==", "==", "==", "==", "==", "__", "==", "==", "==", "==", "==", "==", "__",
                    "__", "==", "ss", "__", "__", "__", "__", "==", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "==", "__",
                    "__", "==", "__", "__", "__", "__", "__", "==", "__", "==", "==", "__", "ss", "==", "==", "==", "==", "__", "==", "__",
                    "__", "==", "__", "==", "==", "==", "==", "==", "__", "==", "__", "__", "==", "__", "__", "==", "__", "__", "==", "__",
                    "__", "==", "__", "==", "__", "__", "__", "__", "__", "==", "__", "__", "==", "__", "__", "==", "__", "__", "==", "__",
                    "__", "==", "__", "==", "__", "__", "==", "==", "==", "==", "__", "__", "==", "__", "__", "==", "__", "==", "==", "__",
                    "__", "==", "__", "==", "__", "__", "==", "__", "__", "__", "__", "__", "==", "ss", "__", "__", "__", "==", "__", "__",
                    "__", "==", "__", "==", "__", "__", "==", "__", "__", "==", "__", "__", "==", "__", "__", "==", "__", "==", "__", "==",
                    "__", "==", "__", "==", "__", "__", "==", "__", "__", "==", "__", "__", "==", "__", "__", "==", "__", "==", "__", "__",
                    "__", "==", "__", "==", "__", "==", "==", "__", "__", "==", "__", "__", "==", "__", "==", "==", "__", "==", "==", "__",
                    "__", "==", "__", "__", "__", "__", "__", "__", "__", "==", "__", "__", "==", "__", "==", "__", "__", "__", "==", "__",
                    "__", "==", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "==", "__", "==", "==", "==", "__", "__", "__",
                    "__", "==", "==", "==", "__", "==", "==", "==", "__", "ss", "ss", "==", "==", "__", "__", "__", "==", "__", "==", "__",
                    "__", "__", "__", "__", "__", "__", "==", "__", "__", "__", "__", "__", "==", "__", "ss", "__", "==", "__", "==", "__",
                    "==", "==", "==", "==", "==", "__", "==", "__", "__", "__", "__", "__", "==", "__", "__", "__", "==", "__", "==", "__",
                    "__", "__", "__", "__", "__", "__", "==", "__", "__", "__", "__", "__", "==", "==", "==", "__", "==", "==", "==", "__",
                    "__", "__", "__", "__", "__", "__", "==", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "==", "__",
                    "__", "__", "__", "__", "__", "__", "==", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "==", "__"
            };
            loadLevel(gameModel, level);
        }

        if (1 + (currentLevel - 1) % 8 == 6) {
            enemySequence = new int[]{4, 4, 4, 4, 2, 2, 2, 4, 4, 1, 1, 1, 3, 3, 3, 1, 1, 4, 4, 4};
            String[] level = new String[]{
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "$$", "__", "__", "__", "__", "$$", "__", "__", "__", "__", "__", "__", "__", "__", "__", "$$", "__",
                    "__", "__", "$$", "ss", "$$", "__", "__", "$$", "ss", "$$", "__", "__", "__", "__", "__", "__", "__", "$$", "ss", "$$",
                    "__", "$$", "ss", "ss", "$$", "__", "$$", "ss", "ss", "ss", "$$", "__", "__", "__", "__", "__", "__", "$$", "ss", "$$",
                    "__", "$$", "ss", "ss", "ss", "$$", "ss", "ss", "ss", "$$", "$$", "$$", "$$", "__", "__", "__", "$$", "ss", "ss", "$$",
                    "__", "$$", "ss", "ss", "ss", "$$", "$$", "ss", "$$", "$$", "ss", "ss", "ss", "$$", "__", "__", "$$", "ss", "ss", "$$",
                    "$$", "ss", "ss", "ss", "ss", "$$", "__", "$$", "$$", "$$", "ss", "ss", "ss", "$$", "__", "__", "$$", "ss", "ss", "$$",
                    "$$", "ss", "ss", "ss", "ss", "$$", "__", "__", "$$", "$$", "ss", "$$", "$$", "__", "__", "$$", "ss", "ss", "ss", "$$",
                    "$$", "ss", "ss", "ss", "ss", "$$", "__", "$$", "ss", "ss", "ss", "$$", "__", "__", "__", "$$", "ss", "ss", "ss", "$$",
                    "$$", "ss", "ss", "ss", "ss", "$$", "__", "$$", "ss", "ss", "ss", "$$", "__", "__", "__", "$$", "ss", "ss", "$$", "__",
                    "$$", "ss", "ss", "ss", "$$", "__", "__", "ss", "ss", "$$", "$$", "__", "__", "__", "__", "$$", "ss", "ss", "$$", "__",
                    "$$", "ss", "ss", "ss", "$$", "__", "$$", "ss", "ss", "$$", "__", "__", "__", "__", "$$", "ss", "ss", "ss", "$$", "__",
                    "$$", "ss", "ss", "ss", "$$", "$$", "ss", "ss", "$$", "__", "__", "__", "__", "__", "__", "$$", "$$", "$$", "__", "__",
                    "$$", "ss", "ss", "ss", "$$", "$$", "ss", "$$", "__", "__", "$$", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "$$", "ss", "ss", "ss", "$$", "__", "$$", "__", "__", "$$", "ss", "$$", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "$$", "ss", "ss", "$$", "__", "__", "__", "__", "$$", "ss", "ss", "$$", "__", "__", "__", "__", "__", "__", "__",
                    "__", "$$", "ss", "ss", "$$", "__", "__", "__", "__", "$$", "ss", "ss", "ss", "$$", "$$", "$$", "$$", "$$", "__", "__",
                    "__", "$$", "ss", "ss", "$$", "__", "__", "__", "__", "__", "$$", "ss", "ss", "ss", "ss", "ss", "ss", "ss", "$$", "__",
                    "__", "__", "$$", "ss", "$$", "__", "__", "__", "__", "__", "__", "$$", "ss", "ss", "ss", "ss", "$$", "$$", "__", "__",
                    "__", "__", "__", "$$", "__", "__", "__", "__", "__", "__", "__", "__", "$$", "$$", "$$", "$$", "__", "__", "__", "__"

            };
            loadLevel(gameModel, level);
        }

        if (1 + (currentLevel - 1) % 8 == 7) {
            enemySequence = new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 4, 2, 4, 2, 4, 2, 4, 2, 4};
            String[] level = new String[]{
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "__", "__", "$$", "$$", "$$", "__", "__",
                    "__", "__", "__", "ss", "ss", "ss", "__", "__", "##", "##", "##", "__", "__", "__", "__", "$$", "$$", "$$", "__", "__",
                    "__", "__", "__", "ss", "ss", "ss", "__", "__", "##", "##", "##", "__", "__", "__", "__", "$$", "$$", "$$", "__", "__",
                    "__", "__", "__", "ss", "ss", "ss", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "==", "==", "==", "__",
                    "$$", "$$", "$$", "__", "__", "__", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "==", "==", "==", "__",
                    "$$", "$$", "$$", "__", "__", "__", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "==", "==", "==", "__",
                    "$$", "$$", "$$", "__", "__", "__", "ss", "ss", "ss", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "ss", "ss", "ss", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "ss", "ss", "ss", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "ss", "ss", "ss", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "ss", "ss", "ss", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "ss", "ss", "ss", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "##", "##", "##", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "##", "##", "##", "__", "__", "__", "__", "__", "__", "__", "__", "==", "==", "==", "__", "__",
                    "__", "__", "__", "__", "##", "##", "##", "__", "__", "__", "__", "__", "__", "__", "__", "==", "==", "==", "__", "__",
                    "__", "__", "__", "__", "##", "##", "##", "__", "__", "__", "__", "__", "__", "__", "__", "==", "==", "==", "__", "__"
            };
            loadLevel(gameModel, level);
        }

        if (1 + (currentLevel - 1) % 8 == 8) {
            enemySequence = new int[]{3, 4, 4, 2, 3, 4, 4, 2, 3, 4, 4, 2, 3, 4, 4, 2, 3, 4, 4, 2};
            String[] level = new String[]{
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "##", "##", "##", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "##", "##", "##", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "##", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "##", "__", "__", "##", "##", "##", "__", "##", "__", "__", "__", "##", "__", "__", "##", "##", "##", "__",
                    "__", "__", "##", "__", "##", "__", "__", "##", "__", "##", "__", "__", "__", "##", "__", "##", "__", "__", "##", "__",
                    "__", "__", "##", "__", "##", "__", "__", "##", "__", "__", "##", "__", "##", "__", "__", "##", "__", "__", "##", "__",
                    "__", "__", "##", "__", "##", "__", "__", "##", "__", "__", "##", "__", "##", "__", "__", "##", "__", "__", "##", "__",
                    "__", "__", "##", "__", "##", "__", "__", "##", "__", "__", "##", "__", "##", "__", "__", "##", "__", "__", "##", "__",
                    "##", "__", "##", "__", "##", "__", "__", "##", "__", "__", "__", "##", "__", "__", "__", "##", "__", "__", "##", "__",
                    "__", "##", "__", "__", "__", "##", "##", "__", "##", "__", "__", "##", "__", "__", "__", "__", "##", "##", "__", "##",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__"
            };
            loadLevel(gameModel, level);
        }

        ServerModel.addActor(ServerModel.getP1());
        ServerModel.addActor(ServerModel.getP2());
    }

    public static void loadLevel(ServerModel gameModel, String[] level) {
        for (int i = 0; i < level.length; i++) {
            if ("##".equals(level[i])) {
                ServerModel.addActor(new Wall(23 + (i % 20) * 25, 23 + (i / 20) * 25, gameModel));
            }
            if ("#0".equals(level[i])) {
                ServerModel.addActor(new Wall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 0, gameModel));
            }
            if ("#1".equals(level[i])) {
                ServerModel.addActor(new Wall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 1, gameModel));
            }
            if ("#2".equals(level[i])) {
                ServerModel.addActor(new Wall(23 + (i % 19) * 25, 23 + (i / 20) * 25, 2, gameModel));
            }
            if ("#3".equals(level[i])) {
                ServerModel.addActor(new Wall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 3, gameModel));
            }
            if ("ss".equals(level[i])) {
                ServerModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, gameModel));
            }
            if ("s0".equals(level[i])) {
                ServerModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 0, gameModel));
            }
            if ("s1".equals(level[i])) {
                ServerModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 1, gameModel));
            }
            if ("s2".equals(level[i])) {
                ServerModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 2, gameModel));
            }
            if ("s3".equals(level[i])) {
                ServerModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 3, gameModel));
            }
            if ("$$".equals(level[i])) {
                for (int j = 399; j >= 0; j--) {
                    if (ServerModel.gameComponents[j] == null) {
                        ServerModel.gameComponents[j] = new Grass(23 + (i % 20) * 25, 23 + (i / 20) * 25);
                        break;
                    }
                }
            }
            if ("==".equals(level[i])) {
                ServerModel.addActor(new River(23 + (i % 20) * 25, 23 + (i / 20) * 25, gameModel));
            }
        }
    }

    public static void spawnEnemy(ServerModel gameModel) {
        if (NoOfEnemy < maxNoEnemy && enemyLeft > 0 && (ServerModel.getGameFlow() % enemySpawnTime == 0)) {
            int xPos = 23 + (20 - enemyLeft) % 3 * 238;
            boolean flashing = (enemyLeft % 3 == 0);
            ServerModel.addActor(new Enemy(enemySequence[20 - enemyLeft], flashing, xPos, 23, gameModel));
            enemyLeft--;
            NoOfEnemy++;
        }
    }

    public static void reset() {
        currentLevel = 0;
        enemySpawnTime = 150;
        enemyLeft = 20;
        deathCount = 0;
        maxNoEnemy = 3;
        NoOfEnemy = 0;
    }
}
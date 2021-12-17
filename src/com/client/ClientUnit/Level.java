package com.client.ClientUnit;//服务器端的level类
//因为只有一层对象,所以在这个类是一个静态变量

import com.client.ConponentPack.NormalObject;
import com.client.ConponentPack.SteelWall;
import com.client.ConponentPack.BrickWall;

/**
 * @author chenhong
 */
public class Level {
    private static int winningCount;

    public static int getWinningCount() {
        return winningCount;
    }

    public static void setWinningCount(int winningCount) {
        Level.winningCount = winningCount;
    }

    public static void loadLevel(ClientModel gameModel, int levelIndex) {
        //清除所有的东西
        for (int i = 0; i < 400; i++) {
            gameModel.setDrawingList(i, null);
        }

        //加载基地
        gameModel.setDrawingList(0, new BrickWall(248, 498, 2));
        gameModel.setDrawingList(1, new BrickWall(273, 498, 3));
        gameModel.setDrawingList(2, new BrickWall(248, 473, 1));
        gameModel.setDrawingList(3, new BrickWall(273, 473, 1));
        gameModel.setDrawingList(4, new NormalObject(260, 498, gameModel, "base", 0));

        //加载一个级别
        if (1 + (levelIndex - 1) % 8 == 1) {
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

        if (1 + (levelIndex - 1) % 8 == 2) {
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

        if (1 + (levelIndex - 1) % 8 == 3) {
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

        if (1 + (levelIndex - 1) % 8 == 4) {
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
                    "__", "__", "##", "$$", "$$", "$$", "$$", "$$", "$$", "s3", "s2", "$$", "$$", "$$", "$$", "##", "##", "s0", "s0", "s0",
                    "__", "__", "##", "##", "##", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "$$", "##", "##", "__", "__", "__", "__",
                    "__", "__", "__", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "##", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__",
                    "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__", "__"
            };
            loadLevel(gameModel, level);
        }

        if (1 + (levelIndex - 1) % 8 == 5) {
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

        if (1 + (levelIndex - 1) % 8 == 6) {
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

        if (1 + (levelIndex - 1) % 8 == 7) {
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

        if (1 + (levelIndex - 1) % 8 == 8) {
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
    }

    public static void loadLevel(ClientModel gameModel, String[] level) {
        for (int i = 0; i < level.length; i++) {
            if ("##".equals(level[i])) {
                gameModel.addActor(new BrickWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 4));
            }
            if ("#0".equals(level[i])) {
                gameModel.addActor(new BrickWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 0));
            }
            if ("#1".equals(level[i])) {
                gameModel.addActor(new BrickWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 1));
            }
            if ("#2".equals(level[i])) {
                gameModel.addActor(new BrickWall(23 + (i % 19) * 25, 23 + (i / 20) * 25, 2));
            }
            if ("#3".equals(level[i])) {
                gameModel.addActor(new BrickWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 3));
            }
            if ("ss".equals(level[i])) {
                gameModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 4));
            }
            if ("s0".equals(level[i])) {
                gameModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 0));
            }
            if ("s1".equals(level[i])) {
                gameModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 1));
            }
            if ("s2".equals(level[i])) {
                gameModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 2));
            }
            if ("s3".equals(level[i])) {
                gameModel.addActor(new SteelWall(23 + (i % 20) * 25, 23 + (i / 20) * 25, 3));
            }

            if ("$$".equals(level[i])) {
                for (int j = 399; j >= 0; j--) {
                    if (gameModel.getDrawingList(j) == null) {
                        gameModel.setDrawingList(j, new NormalObject(23 + (i % 20) * 25, 23 + (i / 20) * 25, gameModel, "grass", -1));
                        break;
                    }
                }
            }
            if ("==".equals(level[i])) {
                gameModel.addActor(new NormalObject(23 + (i % 20) * 25, 23 + (i / 20) * 25, gameModel, "river", 71));
            }
        }
    }
}
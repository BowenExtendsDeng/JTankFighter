package com.ProcessUnit.ClientPack;

import com.CommunicateUnit.ClientPack.ClientCommunication;
import com.ProcessUnit.LogicalLoop;
import com.ProcessUnit.Ticker;
import com.UI.ClientDrawingPanel;
import com.UI.ClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 26317
 */
public class ClientModel implements ActionListener{
    //游戏变量
    private static int gameFlow;
    private static ClientView view;
    public static String playerTypedMessage = "";
    //实际的游戏运行在这个线程,而主线程听用户的输入
    private static Ticker t;

    public static void setGameFlow(int gameFlow) {
        ClientModel.gameFlow = gameFlow;
    }

    public static String getPlayerTypedMessage() {
        return playerTypedMessage;
    }

    public static void setPlayerTypedMessage(String playerTypedMessage) {
        ClientModel.playerTypedMessage = playerTypedMessage;
    }

    public static int getGameFlow() {
        return gameFlow;
    }

    public static ClientView getView() {
        return view;
    }

    public static Ticker getT() {
        return t;
    }




    public ClientModel(ClientView thisView) {
        view = thisView;
        ClientDrawingPanel.messageQueue = new String[8];
        ClientDrawingPanel.addMessage("欢迎来到坦克大战用户端！请输入主机IP地址然后点击\"连接主机\"按钮开始游戏");
        t = new Ticker(1000);
        t.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClientCommunication.connectServer();
        //如果程序不能连接到服务器然后什么都不做
        if (ClientStatus.isServerConnected()) {
            return;
        }
        LogicalLoop.clientLogic();
    }
}

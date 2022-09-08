package chillrain.launcher.util;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * @author ChillRain 2022 09 07
 */
public class GameLaucher {
    /**
     * 启动游戏
     * @param game 要启动的游戏
     */
    static File stater = new File("resources/stater.bat");
    public static void gameStartMode(File game, String gameStr, String gamePath, JComboBox gamemode) throws IOException {
        int mode = gamemode.getSelectedIndex();
        switch (mode){
            //        以bat脚本启动游戏
            case 0:makeGameStater(game, gameStr, gamePath);
                GameLaucher.launchMode(stater);
                break;
            //        以File对象启动游戏
            case 1:GameLaucher.launchMode(game);
                break;

        }



    }
//    创建游戏启动脚本
    public static void makeGameStater(File game, String gameStr, String gamePath) throws IOException {
        stater.createNewFile();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(stater), "ASCII");
        BufferedWriter Bwriter = new BufferedWriter(writer);
        String cmd = game.getPath().charAt(0) + ":\ncd " + gamePath + "\nstart " + gameStr;
        System.out.println(cmd);
        writer.write(cmd);
        writer.close();
        Bwriter.close();

    }
//    启动游戏
    public static void launchMode(File launch) throws IOException {
        Desktop.getDesktop().open(launch);
    }

}

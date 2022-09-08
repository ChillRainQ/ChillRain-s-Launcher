package chillrain.launcher.util;

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
    public static void gameStart(File game, String gameStr, String gamePath) throws IOException {
        makeGameStater(game, gameStr, gamePath);
//        以bat脚本启动游戏
        Desktop.getDesktop().open(stater);
//        以File对象启动游戏
        Desktop.getDesktop().open(game);
    }
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

}

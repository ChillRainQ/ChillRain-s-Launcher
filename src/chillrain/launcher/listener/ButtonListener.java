package chillrain.launcher.listener;

import chillrain.launcher.function.CompFunction;
import chillrain.launcher.util.Config;
import chillrain.launcher.util.FileIOUtil;
import chillrain.launcher.util.GameLaucher;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * @author ChillRain 2022 09 06
 */
public class ButtonListener {
    /**
     *  更新配置文件的游戏目录
     */
    public static void setGameConfig(Component top, JTextField path, JList<String> dir){
        File file = new CompFunction().openPath(top);
        path.setText(file.getPath());
        try {
            Config.gameDirPathWrite("gameDirPath", file.getPath());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
//                      向列表项中加入所有文件夹
        CompFunction.jListAddEle(file.getPath(), dir);
    }

    /**
     * 设定游戏启动设置
     * @param middle 中键根控件
     * @param dir 列表框
     */
    public static void setGameLauncher(Component middle ,JList<String> dir) throws IOException {
//        获取选择的文件目录
        List<String> path = CompFunction.getPath(dir);
        String gamePath = path.get(0);
        String gameDir = path.get(1);
//        向配置文件记录游戏启动文件
        JFileChooser gameFile = new JFileChooser(gamePath);
        gameFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = gameFile.showOpenDialog(middle);
        if (i == JFileChooser.APPROVE_OPTION){
            File gameExe = gameFile.getSelectedFile();
            Config.gameDirPathWrite(gameDir, gameExe.getName());
        }
    }

    /**
     * 启动游戏
     * @param dir 列表框
     */
    public static void lauchTheGame(JList<String> dir, JComboBox<String> gamemode) throws IOException {
        List<String> path = CompFunction.getPath(dir);
        String gamePath = path.get(0);
        String gameDir = path.get(1);
        Properties properties = Config.propertiesRead();
        String gameExeName = properties.getProperty(gameDir);
        String gameStr = gamePath + "\\" + gameExeName;
        if(gameExeName != null) {
            File game = new File(gameStr);
            GameLaucher.gameStartMode(game, gameExeName, gamePath, gamemode);
        }else {
            JOptionPane.showMessageDialog(dir,"游戏未设定启动设置！请进行启动设置");
        }
    }

    /**
     * 打开为文件夹
     * @param dirName 文件夹名称
     */
    public static void openAsDir(String dirName) throws IOException {
        Properties config = Config.propertiesRead();
        String dir = config.getProperty("gameDirPath");
        File opendir = new File(dir + "/" + dirName);
        Desktop.getDesktop().open(opendir);
        System.out.println(opendir.getPath());
    }

    public static void addGameDir(Component comp) throws IOException {
        File sourceDir = null;
        File targetDir = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int i = chooser.showOpenDialog(comp);
        if (i == JFileChooser.APPROVE_OPTION){
            Properties config = Config.propertiesRead();
            String dir = config.getProperty("gameDirPath");
            sourceDir = chooser.getSelectedFile();
            targetDir = new File(dir + "/" + sourceDir.getName());
        }
        if (sourceDir == null || targetDir == null){
            return;
        }
        FileIOUtil.copyDir(sourceDir, targetDir);
    }
    public static void update(Component comp) throws IOException {
        int i = JOptionPane.showConfirmDialog(comp, "启动更新程序，选择 是 来启动");
        Desktop.getDesktop().open(new File("update.exe"));
        if (i == 0) System.exit(1);

    }
}

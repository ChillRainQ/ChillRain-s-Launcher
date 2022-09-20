package chillrain.launcher;

import chillrain.launcher.frame.MainFrame;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * @author ChillRain 2022 09 06
 */
public class Launch {
    public static void main(String[] args) throws IOException {
//        检测并创建配置文件
        File config = new File("resources/config.properties");
        File dir = new File("resources");
        if (!dir.exists()) dir.mkdir();
        if (!config.exists()) config.createNewFile();
//        创建窗口
        MainFrame mainFrame = new MainFrame("ChillRain's Launcher!");
        mainFrame.setIconImage(new ImageIcon("resources/ico.jpg").getImage());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600,500);
//        设置不可改变窗口大小
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }
}

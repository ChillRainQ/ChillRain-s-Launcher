package chillrain.launcher.frame;

import chillrain.launcher.function.CompFunction;
import chillrain.launcher.listener.JListMouseListener;
import chillrain.launcher.listener.OpenListener;
import chillrain.launcher.util.Config;
import chillrain.launcher.util.RootAddComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * @author ChillRain 2022 09 06
 */
public class MainFrame extends JFrame{
    /**
     * 控件
     */
    JTextField path = new JTextField();
    JButton open = new JButton("选择目录");
    JButton setGameLauncher = new JButton("启动设置");
    JButton launchGame = new JButton("启动游戏");
    JList<String> dir = new JList<>();
    JScrollPane sp = new JScrollPane(dir);

    /**
     *
     * 主页面绘制
     * @param title 标题
     */
    public MainFrame(String title) throws HeadlessException, IOException {
        super(title);
        JPanel root = new JPanel();
        root.setLayout(null);
        List<Component> roots = new ArrayList<>();
        roots.add(top());
        roots.add(middle());
        RootAddComponents.addmian(root, roots);
        this.setContentPane(root);

    }

    /**
     * 顶部控件
     */
    private Component top() throws IOException {
        JPanel top = new JPanel();
        top.setLayout(null);
        List<Component> comps = new ArrayList<>();
        comps.add(path);
        comps.add(open);
        RootAddComponents.addtop(top, comps);
        path.setEnabled(false);
//        获取文本内容
        CompFunction.jTextGetPathByConfig(path, dir);
//        打开目录的按钮 如果配置文件中没有目录则写入目录
        open.addActionListener(e -> OpenListener.open(top, path, dir));
        top.setOpaque(true);
        top.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xA0A0A0)));
        return top;
    }

    /**
     * 中部控件
     * @return
     */
    private Component middle(){
        JPanel middle = new JPanel();
        middle.setLayout(null);
        List<Component> comps = new ArrayList<>();
        comps.add(sp);
        comps.add(setGameLauncher);
        comps.add(launchGame);
        RootAddComponents.addmidle(middle, comps);
//      单击按钮获取游戏启动设置
        setGameLauncher.addActionListener(e -> {
            try {
                OpenListener.setGameLauncher(middle, dir);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
//        单击按钮启动游戏
        launchGame.addActionListener(e -> {
            try {
                OpenListener.lauchTheGame(dir);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
//        双击列表框启动游戏
        dir.addMouseListener(new JListMouseListener(dir));
        middle.setOpaque(true);
//        middle.setBackground(new Color(255, 255, 0));
        middle.setBorder(BorderFactory.createLineBorder(new Color(111, 111, 111)));
        return middle;
    }

    public JTextField getPath() {
        return path;
    }

    public JButton getOpen() {
        return open;
    }

    public JButton getSetGameLauncher() {
        return setGameLauncher;
    }

    public JButton getLaunchGame() {
        return launchGame;
    }

    public JList<String> getDir() {
        return dir;
    }

    public JScrollPane getSp() {
        return sp;
    }
}

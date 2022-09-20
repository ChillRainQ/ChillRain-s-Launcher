package chillrain.launcher.frame;

import chillrain.launcher.function.CompFunction;
import chillrain.launcher.listener.JListMouseListener;
import chillrain.launcher.listener.ButtonListener;
import chillrain.launcher.myComp.TxtLable;
import chillrain.launcher.util.Config;
import chillrain.launcher.util.RootAddComponents;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    TxtLable mode = new TxtLable("启动模式：");
    JComboBox<String> gamemode = new JComboBox<>();
    JButton openAsDir = new JButton("打开为文件夹");
    JButton addGameDir = new JButton("添加游戏文件夹");
    JButton reShow = new JButton("刷新列表");
    JButton update = new JButton("更新");

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
        comps.add(reShow);
        RootAddComponents.addtop(top, comps);
        path.setEnabled(false);
//        获取文本内容
        CompFunction.jTextGetPathByConfig(path, dir);
//        打开目录的按钮 如果配置文件中没有目录则写入目录
        open.addActionListener(e -> ButtonListener.setGameConfig(top, path, dir));
//        刷新游戏目录
        reShow.addActionListener(e -> {
            try {
                CompFunction.reShowJList(dir);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        top.setOpaque(true);
        top.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xA0A0A0)));
        return top;
    }

    /**
     * 中部控件
     */
    private Component middle() throws IOException {
        JPanel middle = new JPanel();
        middle.setLayout(null);
        List<Component> comps = new ArrayList<>();
        comps.add(sp);
        comps.add(setGameLauncher);
        comps.add(launchGame);
        comps.add(mode);
        comps.add(gamemode);
        comps.add(openAsDir);
        comps.add(addGameDir);
        comps.add(update);
        RootAddComponents.addmidle(middle, comps);
//      单击按钮获取游戏启动设置
        setGameLauncher.addActionListener(e -> {
            try {
                ButtonListener.setGameLauncher(middle, dir);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
//        单击按钮启动游戏
        launchGame.addActionListener(e -> {
            try {
                ButtonListener.lauchTheGame(dir, gamemode);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
//        双击列表框启动游戏
        dir.addMouseListener(new JListMouseListener(dir, gamemode));
//        添加启动模式
        CompFunction.modeAdd(gamemode);
//        打开选中的游戏文件夹于Explorer
        openAsDir.addActionListener(e -> {
            try {
                ButtonListener.openAsDir(dir.getModel().getElementAt(dir.getSelectedIndex()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
//        向游戏目录中添加游戏
        addGameDir.addActionListener(e -> {
            try {
                ButtonListener.addGameDir(addGameDir);
                CompFunction.reShowJList(dir);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        update.addActionListener(e -> {
            try {
                ButtonListener.update(update);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        middle.setOpaque(true);
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

    public TxtLable getMode() {
        return mode;
    }

    public JComboBox<String> getGamemode() {
        return gamemode;
    }
}

package chillrain.launcher.function;

import chillrain.launcher.filter.DirFilter;
import chillrain.launcher.util.Config;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author ChillRain 2022 09 06
 */
public class CompFunction {
    /**
     * 选择打开目录
     * @param open 功能控件
     * @return  文件路径
     */
    public  File openPath(Component open){
        JFileChooser path = new JFileChooser();
        path.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int i = path.showOpenDialog(open);
        if (i == JFileChooser.APPROVE_OPTION){
            File dirPath = path.getSelectedFile();
            JOptionPane.showMessageDialog(open,"选择文件夹成功");
            return dirPath;
        }
        return null;
    }

    /**
     * 目录内文件夹展示
     * @param dirPath 目录路径
     * @param jList 列表框
     */
    public void jListAddEle(String dirPath, JList jList){
        File file = new File(dirPath);
        File[] dirs = file.listFiles(new DirFilter());
        DefaultListModel<String> model = new DefaultListModel<>();
        for (File dir : dirs) {
            model.addElement(dir.getName());
        }
        jList.setModel(model);
    }

    /**
     * 向列表框内展示文件夹
     * @param path 文本框控件
     * @param dir 列表框控件
     * @throws IOException
     */
    public static void jTextGetPathByConfig(JTextField path, JList dir) throws IOException {
        Properties properties = Config.propertiesRead();
        String gameDirPath = properties.getProperty("gameDirPath");
        Boolean isPath = false;
        if (gameDirPath != null){
            path.setText(gameDirPath);
            new CompFunction().jListAddEle(gameDirPath, dir);
            isPath = true;
        }
        if(isPath == false){
            JOptionPane.showMessageDialog(path, "未选择游戏目录，请单击“选择目录”！");
        }
    }

    /**
     * 获取选定的文件目录
     * @param dir 列表框控件
     * @return 返回 游戏路径 游戏文件夹名称
     * @throws IOException
     */
//    获取选定的文件目录
    public static List<String> getPath(JList dir) throws IOException {
//        获取游戏文件夹名
        int index = dir.getAnchorSelectionIndex();
        String gameDir = (String) dir.getModel().getElementAt(index);
        System.out.println(gameDir);
//        读取配置文件的游戏总目录，并合并成游戏路径返回
        Properties properties = Config.propertiesRead();
        String gameDirPath = properties.getProperty("gameDirPath");
        String gamePath = gameDirPath + "\\" + gameDir;
        return Arrays.asList(gamePath, gameDir);
    }



}

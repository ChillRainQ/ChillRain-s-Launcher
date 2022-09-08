package chillrain.launcher.util;

import chillrain.launcher.layout.MainLayOut;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author ChillRain 2022 09 06
 */
public class RootAddComponents {
//    把控件加入到面板并放入手动布局器数组
    public static void addtop(JPanel root, List<Component> comps){
        add(root, comps);
        MainLayOut.topLayoutRoot(comps);
    }
    public static void addmian(JPanel root, List<Component> comps){
        add(root, comps);
        MainLayOut.mainLayoutRoot(comps);
    }
    public static void addmidle(JPanel root, List<Component> comps){
        add(root, comps);
        MainLayOut.middleLayoutRoot(comps);
    }

    private static void add(JPanel root, List<Component> comps){
        for (Component comp : comps) {
            root.add(comp);
        }
    }

}

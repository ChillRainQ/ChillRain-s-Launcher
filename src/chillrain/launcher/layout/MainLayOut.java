package chillrain.launcher.layout;

import java.awt.*;;
import java.util.List;

/**
 * 手动布局类
 * @author ChillRain 2022 09 06
 */

public class MainLayOut  {
//    主Root布局
    public static void mainLayoutRoot(List<Component> roots){
//        top
        roots.get(0).setBounds(0, 0, 800, 30);
//        middle
        roots.get(1).setBounds(0, 30, 800, 403);
    }
    public static void topLayoutRoot(List<Component> comps){
        comps.get(0).setBounds(2, 3, 400, 25);
        comps.get(1).setBounds(405, 3, 90, 25);
        comps.get(2).setBounds(495, 3, 90, 25);
    }
    public static void middleLayoutRoot(List<Component> comps){
        comps.get(0).setBounds(2, 3, 400, 400);
        comps.get(1).setBounds(405, 3, 90, 25);
        comps.get(2).setBounds(495, 3, 90, 25);
        comps.get(3).setBounds(405, 28, 90, 25);
        comps.get(4).setBounds(495, 28, 90, 25);
        comps.get(5).setBounds(405, 53, 180, 25);
        comps.get(6).setBounds(405, 53 + 25, 180, 25);
        comps.get(7).setBounds(405, 53 + 25 + 25, 90, 25);
    }
}

package chillrain.launcher.listener;

import chillrain.launcher.frame.MainFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * @author ChillRain 2022 09 07
 */
public class JListMouseListener implements MouseListener {
    public JList dir;
    public JComboBox gamemode;
    public JListMouseListener(JList dir, JComboBox gamemode) {
        this.dir = dir;
        this.gamemode = gamemode;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            try {
                OpenListener.lauchTheGame(dir, gamemode);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

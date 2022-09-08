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
    public JListMouseListener(JList dir) {
        this.dir = dir;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2){
            try {
                OpenListener.lauchTheGame(dir);
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

package View;

import Controller.Thread.GameController;
import Model.Framework.TestMap;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Chris Young on 2017/5/23.
 */

public class GameView extends JFrame implements ActionListener,MouseListener,MouseMotionListener{
    TestMap testmap=new TestMap();
    GameController _gc = new GameController(testmap);

    GameView(){
        _gc.Start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}


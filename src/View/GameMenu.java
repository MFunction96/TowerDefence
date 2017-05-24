package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import Model.Map.Block;
import Model.BaseClass.*;
/**
 * Created by Chris Young on 2017/5/22.
 */
public class GameMenu extends JFrame implements ActionListener, MouseMotionListener, MouseListener {
    /**
     * 窗体宽
     * */
    private int w;
    /**
     * 窗体长
     */
    private int h;
    /**
     * 游戏区域左上顶点x坐标
     */
    private int gameX;

    /**
     * 游戏区域左上顶点y坐标
     */
    private int gameY;
    /*
    *地图宽
    */
    private int gameW;
    /*
    *地图长
     */
    private int gameH;
    /*
    *单块方格大小
     */
    private int squaresSize;
    /*
    *当焦点单位方格x坐标
     */
    private int focusX;
/*
当前焦点单位方格y坐标
 */

    private int focusY;
    /**
            * 鼠标坐标
     */
    int x, y;

    JButton _return;

    GameMenu() {
        super("0度塔防");
        _return = new JButton("返回");
        _return.addActionListener(this);
        _return.setBounds(200,200,200,100);
        this.add(_return);
        this.setVisible(true);
        this.setSize(1024,838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        init();

        this.setBounds(64, 64, w, h);
        this.setDefaultCloseOperation(WindowConstants .EXIT_ON_CLOSE);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setResizable(false);

    }
    private void init() {
        w = 1024;
        h = 838;
        x =64;
        y = 64;
        gameW = 1024;
        gameH = 800;
        focusX = -64;
        focusY = -64;
        squaresSize = 64;
    }
    public void paint(Graphics gr) {
        BufferedImage image = null;
        try{
            image = ImageIO .read(new File("src/Image/Map.png") );
        }catch (Exception e){
            e.printStackTrace() ;
        }
       gr.drawImage(image,0,0,null);
        Graphics g2 = image.getGraphics();
        g2.setColor(Color.white);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j <12; j++) {
                g2.drawRect(j * squaresSize + x, i * squaresSize
                        + y, squaresSize, squaresSize);
            }
        }
        g2.setColor(Color.blue);
        g2.fillRect(focusX, focusY, squaresSize, squaresSize);
        gr.drawImage(image, 0, 0, this);

    }

    public static void main(String[] args) {
        new GameMenu() ;
    }

    public void mouseDragged(MouseEvent e) {
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _return) {
            this.dispose();
            new MainMenu();
        }
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
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mx > x  && mx < x + gameW && my > y
                && my < y + gameH) {
            focusX = (mx - x) / squaresSize * squaresSize
                    + squaresSize;
            focusY = (my - y) / squaresSize * squaresSize
                    + squaresSize;
        } else {
            focusX = -128;
            focusY = -128;
        }
        repaint();
    }
}

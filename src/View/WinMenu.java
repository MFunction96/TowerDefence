package View;

import sun.swing.ImageIconUIResource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by hlys on 2017/5/29.
 */
     public class WinMenu extends JFrame implements ActionListener, MouseMotionListener, MouseListener, ItemListener, Runnable {
    /**
     * 窗体宽
     */
    private int w;
    /**
     * 窗体长
     */
    private int h;
    JButton _Next ;
    JButton _BackWhenWin ;
    public WinMenu(){
        super("0度塔防");//设置标题
        this.setVisible(true);//设置为可见
        this.setSize(1024, 838);//设置窗体大小
        this.setLayout(null);//设置为空布局
        this.setLocationRelativeTo(null);//设置为集中显示
        this.setResizable(false);//设置不可改变窗体大小
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭线程;
        this.setVisible(true);
        this.setSize(1024, 838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        init();
        _Next = new JButton(new ImageIcon("src/image/NextGame.png") );
        _BackWhenWin = new JButton(new ImageIcon("src/image/BackToMainMenu.png") );
        _Next.setBounds(250, 470, 217, 60);
        _Next.setVisible(true);
        _Next.addActionListener(this);
        _Next.setBorderPainted(false);
        this.getContentPane().add(_Next);


        _BackWhenWin.setBounds(527, 471, 217, 60);
        _BackWhenWin.setVisible(true);
        _BackWhenWin.addActionListener(this);
        _BackWhenWin.setBorderPainted(false);
        this.getContentPane().add(_BackWhenWin);

        }

    private void init() {
        w = 1024;
        h = 838;
    }
public void paint(Graphics g){
    BufferedImage images = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
    Image image = null;
    Graphics g2 = images.createGraphics();
    //画地图
    try {
        image = ImageIO.read(new File("src/Image/YOUWIN.png"));
    } catch (Exception e) {
        e.printStackTrace();
    }
    g2.drawImage(image, 0, 0, this);
    g2.dispose();//在此函数前面调用g2画笔画其它图
    g.drawImage(images, 0, 0, this);
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() ==_Next )
        {

        }
        else if (e.getSource() ==_BackWhenWin ){
            this.dispose() ;
            new MainMenu() ;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

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

    @Override
    public void run() {

    }
    public static void main(String args[]){
        new WinMenu() ;
    }
}

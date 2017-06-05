package View;

import Controller.Thread.MusicController;
//import Model.Audio.WinMusic;
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
 * 显示胜利界面
 *
 * @auther hlys
 */
     public class WinMenu extends JFrame implements ActionListener{
    /**
     * 窗体宽
     */
    private int w;
    /**
     * 窗体长
     */
    private int h;
    private JButton _Next ;
    private JButton _BackWhenWin ;
    private GameMenu _gm;
    //WinMusic winMusic=new WinMusic();

    public WinMenu( GameMenu gm){
        super("0度塔防");//设置标题
        this.setVisible(true);//设置为可见
        this.setSize(512, 419);//设置窗体大小
        this.setLayout(null);//设置为空布局
        this.setLocationRelativeTo(null);//设置为集中显示
        this.setResizable(false);//设置不可改变窗体大小
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭线程;
        this.setVisible(true);
        this.setSize(1024, 838);
        init();
        _Next = new JButton(new ImageIcon("src/image/NextGame.png") );
        _BackWhenWin = new JButton(new ImageIcon("src/image/BackToMainMenu.png") );
        _Next.setBounds(125, 235, 108, 30);
        _Next.setVisible(true);
        _Next.addActionListener(this);
        _Next.setBorderPainted(false);
        this.getContentPane().add(_Next);
        _gm=gm;


        _BackWhenWin.setBounds(263, 235, 108, 30);
        _BackWhenWin.setVisible(true);
        _BackWhenWin.addActionListener(this);
        _BackWhenWin.setBorderPainted(false);
        this.getContentPane().add(_BackWhenWin);

        }

    private void init() {
        w = 512;
        h = 419;
    }
public void paint(Graphics g){
    BufferedImage images = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
    Image image = null;
    Graphics g2 = images.createGraphics();

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
            _gm.dispose();
            new MainMenu() ;
        }
    }

    public void run() {
        MusicController music = new MusicController("src/Audio/InstallTower.wav");
        music.start();
    }

}

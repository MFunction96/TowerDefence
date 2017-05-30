package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * Created by hlys on 2017/5/29.
 */
public class DefeatMenu extends JFrame implements ActionListener, MouseMotionListener, MouseListener, ItemListener, Runnable {
    /**
     * 窗体宽
     */
    private int w;
    /**
     * 窗体长
     */
    private int h;

    JButton _BackWhenDefeat ;
    public DefeatMenu(){
        super("0度塔防");//设置标题
        this.setVisible(true);//设置为可见
        this.setSize(1024, 838);//设置窗体大小
        this.setLayout(null);//设置为空布局
        this.setLocationRelativeTo(null);//设置为集中显示
        this.setResizable(false);//设置不可改变窗体大小
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭线程;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        init();

        _BackWhenDefeat = new JButton(new ImageIcon("src/Image/BackToMainMenu.png") );
        _BackWhenDefeat .setVisible(true);
        _BackWhenDefeat .setBounds(400, 472, 217, 60);
        _BackWhenDefeat .addActionListener(this);
        _BackWhenDefeat .setBorderPainted(false);
        this.getContentPane().add(_BackWhenDefeat );

    }

    private void init() {
        w = 1024;
        h = 838;
    }
    public  void paint(Graphics g){

        BufferedImage images = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Image image = null;
        Graphics g2 = images.createGraphics();
        //画地图
        try {
            image = ImageIO.read(new File("src/Image/YOUDEFEAT.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        g2.drawImage(image, 0, 0, this);
        g2.dispose();//在此函数前面调用g2画笔画其它图
        g.drawImage(images, 0, 0, this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() ==_BackWhenDefeat  ){
            this.dispose() ;
            new MainMenu() ;
        }
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
        new DefeatMenu() ;
    }
}

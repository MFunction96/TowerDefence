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
import java.util.List;


import Controller.Thread.GameController;
import Model.BaseClass.*;
import Model.Framework.Map ;
import Model.Tower.TwNormal ;

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
    /**
    *地图宽
    */
    private int gameW;
    /*
    *地图长
     */
    private int gameH;
    /**
    *单块方格大小
     */
    private int squaresSize;
    /**
    *当焦点单位方格x坐标
     */
    private int focusX;
   /**
    *当前焦点单位方格y坐标
   */
    private int focusY;
    /**
     * 鼠标坐标
     */
    int x, y;


    private Map  map=new Map() ;

    private boolean  atTools;
    private boolean drawTowerTools;
    private int changeTowerType;

    /**
     * 塔数组
     */
    private List<Tower> towerList;

    /**
     * 升级工具栏x坐标
     */
    private int upX;

    /**
     * 升级工具栏y坐标
     */
    private int upY;

    /**
     * 是否升级塔
     */
    private boolean up;

    /**
     * 是否摧毁塔
     */
    private boolean broken;

    /**
     * 当前焦点塔
     */
    private Tower focusTower;

    /**
     * 是否绘制金钱（用以金钱不够时使金钱一闪一闪的）
     */
    private boolean drawMoney;
    JButton _return;
    JLabel Tools;
    ImageIcon Toolsicon;
    Image Toolsimg;
    GameMenu() {
        super("0度塔防");//设置标题
        this.setVisible(true);//设置为可见
        this.setSize(1024,838);//设置窗体大小
        this.setLayout(null);//设置为空布局
        this.setLocationRelativeTo(null);//设置为集中显示
        this.setResizable(false);//设置不可改变窗体大小
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭线程;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

/*
        _return = new JButton("暂停游戏");
        _return.addActionListener(this);
        _return.setBounds(200,200,200,100);
        this.getContentPane().add(_return);*/
        init();
    }
    private void init() {
        w = 1024;
        h = 838;
        gameX  =64;
        gameY  = 64;
        gameW = 1024;
        gameH = 800;
        focusX = -64;
        focusY = -64;
        squaresSize = 64;
        drawMoney =true ;
        GameController _gc = new GameController(new Map());
        _gc.Start();
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
                g2.drawRect(j * squaresSize + gameX, i * squaresSize
                        + gameY , squaresSize, squaresSize);
            }
        }
        g2.setColor(Color.blue);
        g2.fillRect(focusX, focusY, squaresSize, squaresSize);
        Toolsicon=new ImageIcon("src/image/Tools.png");
        Toolsimg=Toolsicon.getImage();
        g2.drawImage(Toolsimg,840,0,184,838,null );
        //drawTools(g2);

        drawTowers(g2);
        drawMoney(g2);
        gr.drawImage(image, 0, 0, this);


    }
    private void drawTools(Graphics g2){
        /*Tools =new JLabel() ;
        Tools.setIcon(new ImageIcon("src/Image/Tools.png") );
        Tools.setBounds(919,0,105,838);
    */}
    /**
     * 绘制防御塔
     */
    private void drawTowers(Graphics g2) {
      new TwNormal(g2,896,320);
    }
    /**
     * 绘制金钱
     */
    private void drawMoney(Graphics g) {
        if (drawMoney) {
            Font font = new Font("宋体", 30, 30);
            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("$" +map .money() , 910, 85);
        }
        Font font = new Font("宋体", 30, 30);
        g.setColor(Color.white);
        g.setFont(font);

        g.drawString("" + map .period() , 950,
                130);
    }

    /**
     * 升级塔
     * @param tower
     */
    private void UpTower(final Tower tower ){
        map.SetMoney( map .money() -tower.GetPrice()) ;
        tower .Upgrade() ;
    }

    /**
     * 摧毁塔
     * @param tower
     */
    private void downTower(Tower  tower ){
        map.SetMoney(map .money()+ tower.GetPrice()*tower .GetLevel() ) ;

    }
    public void Gamerun(){
        /*try{
            while(true){
                repaint() ;

            }
        }*/
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
        if (mx > gameX   && mx < gameX  + gameW && my > gameY
                && my < gameY  + gameH) {
            focusX = (mx - gameX ) / squaresSize * squaresSize
                    + squaresSize;
            focusY = (my - gameY ) / squaresSize * squaresSize
                    + squaresSize;
        } else {
            focusX = -128;
            focusY = -128;
        }
        repaint();
    }
}

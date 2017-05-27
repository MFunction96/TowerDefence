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
import Model.Map.Block;
import Model.Tower.TwNormal ;
import Model.BaseClass.Point ;
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

    /**
     * 地图数组
     */
    private int[][] squares;
    private boolean  atTools;
    private int changeTowerType;
    /**
     * 升级工具栏x坐标
     */
    private int upX;

    /**
     * 升级工具栏y坐标
     */
    private int upY;

    /**
     * 是否安装塔
     */
    private boolean _caninstalltower;
    /**
     * 是否升级塔
     */
    private boolean up;

    /**
     * 是否摧毁塔
     */
    private boolean broken;
    /**
     * 塔数组
     */
    private List<Tower>towerList;
    /**
     *
     */
    private boolean drawTowerTools;
    /**
     * 塔的类型
     */
    private int towerType;

    /**
     * 当前焦点塔
     */
    private Tower focusTower;
    /**
     * 建塔工具栏数组
     */
    private List<Point> toolsList;
    /**
     * 是否绘制金钱（用以金钱不够时使金钱一闪一闪的）
     */
    private boolean drawMoney;
    private Map  map=new Map() ;

    JButton NormalMonster;
    JButton _return;
    JButton _Stop;
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
        this.setVisible(true);
        this.setSize(1024,838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(64, 64, 1024, 838);
        this._caninstalltower=false;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        InitialMonsterButton();
        init();
    }


    private void InitialMonsterButton(){
        NormalMonster=new JButton(new ImageIcon("src/Image/TwNormal.png"));
        NormalMonster.setBounds(800,600,64,64);
        NormalMonster.addActionListener(this);
        this.add(NormalMonster);
    }
    /**
     * 初始化界面元素
     */
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

    /**
     * 绘制地图
     */
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

        _return = new JButton(new ImageIcon("src/Image/BackToMainMenu.png") );
        _return .setVisible(true);
        _return.addActionListener(this);
        _return.setBounds(80,800,217,60);
        _return .setBorderPainted(false) ;
        this.getContentPane().add(_return);

        _Stop =new JButton(new ImageIcon("src/Image/Stop.png"));
        _Stop.setVisible(true) ;
        _Stop .addActionListener(this);
        _Stop .setBounds(800,700,217,60);

        drawTowers(g2);
        drawMoney(g2);
        gr.drawImage(image, 0, 0, this);


    }
    /**
     * 绘制升级、摧毁工具栏
     */
    private void drawUpOrDown(Graphics g2) {
        if (upX != -100 && upY != -100 && !drawTowerTools) {
            g2.setColor(Color.white);
            g2.fillRect(upX, upY, 50, 10);
            g2.setColor(Color.orange);
            if (up) {
                g2.fillRect(upX, upY, 25, 10);
            }
            if (broken) {
                g2.fillRect(upX + 25, upY, 25, 10);
            }
            g2.setColor(Color.black);
            g2.drawLine(upX + squaresSize / 2, upY, upX + squaresSize / 2,
                    upY + 10);
            Font font = new Font("宋体", 5, 8);
            g2.setFont(font);
            if (focusTower.GetLevel() < 6) {
                g2.drawString("up" + focusTower.GetPrice() , upX, upY + 8);
            }
            g2.drawString(" down", upX + 25, upY + 8);
        }
    }
    /**
     * 绘制你赢了或你输了标语
     */
    private void drawDead(Graphics g) {
        /*
         *  if (GameController .Win()) {
         Font font = new Font("宋体", 70, 70);
         g.setFont(font);
         g.setC
         olor(Color.white);
         g.drawString("you win!", 200, 200);
         } else if (GameController .Lose()) {
         Font font = new Font("宋体", 70, 70);
         g.setFont(font);
         g.setColor(Color.white);
         g.drawString("you lose!", 200, 200);
         }
         */
    }
    /**
     * 绘制总生命
     */
    private void drawLife(Graphics g2){

    }
    /**
     * 绘制防御塔
     */
    private void drawTowers(Graphics g2) {

    }
    /**
     * 绘制建塔工具栏价格
     */
    private void drawTowersTools(Graphics g2) {
        /*
        private TwNormal twnormal=new TwNormal(g2) ;
        Font font = new Font("宋体", 5, 8);
        g2.setFont(font);
        if (drawTowerTools && focusX >= gameX && focusX < gameX + gameW
                && focusY >= gameY && focusY < gameY + gameH) {
            g2.drawString("$"+ TwNormal.);
        }
         */
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

        g.drawString("" + map.money() , 950,130);
    }

    /**
     * 绘制敌人
     * @param g2
     */
    private void drawMonster(Graphics g2){

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

    /**
     * 据说是刷新图片的线程
     */
    public void Gamerun(){
        try{
            while(true){
                repaint() ;
                Thread .sleep(1);
            }
        }catch(Exception  e){
            e.printStackTrace() ;
        }
    }
    /**
     * 金钱一闪一闪控制器
     */
    private void setDrawMoney() {
        if (drawMoney) {
            drawMoney = false;
        } else {
            drawMoney = true;
        }
    }
    /**
     * 金钱一闪一闪控制线程(这个有点奢侈了，其实可以在其他线程里将就一下)
     */
    private void noMoneyThread() {
        new Thread() {
            public void run() {
                try {
                    for (int i = 0; i < 4; i++) {
                        setDrawMoney();
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public static void main(String[] args) {
        new GameMenu() ;
    }

    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _return) {
            this.dispose();
            new MainMenu();
        }
        else if(e.getSource() ==_Stop ){

        }
        else if(e.getSource()==NormalMonster){
            if(_caninstalltower==true)
                _caninstalltower=false;
            else
                _caninstalltower=true;
        }
    }

    @Override
    /**
     *
     */
    public void mouseClicked(MouseEvent e) {
        if(_caninstalltower==true){
        }
    }

    @Override
    /**
     *
     */

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

    private int getSquerasY(int y) {
        return (y - gameY) / squaresSize * squaresSize + squaresSize;
    }

    private int getSquerasX(int x) {
        return (x - gameX) / squaresSize * squaresSize + squaresSize;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mx > gameX && mx < gameX + gameW && my > gameY
                && my < gameY + gameH) {
            focusX = (mx - gameX) / squaresSize * squaresSize
                    + squaresSize;
            focusY = (my - gameY) / squaresSize * squaresSize
                    + squaresSize;
        } else {
            focusX = -128;
            focusY = -128;
        }
        repaint();
    }
}

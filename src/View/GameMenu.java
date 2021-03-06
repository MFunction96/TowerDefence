package View;

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
import Controller.Thread.GameController;
import Model.Audio.TowerInstallMusic;
import Model.BaseClass.*;
import Model.Framework.Map;
import Model.Tower.TwNormal;
import Model.BaseClass.Point;


/**
 * Created by Chris Young on 2017/5/22.
 * 游戏主界面
 *
 */
public class GameMenu extends JFrame implements ActionListener, MouseMotionListener, MouseListener, ItemListener, Runnable {
    /**
     * 窗体宽
     */
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
     * 地图宽
     */
    private int gameW;
    /*
    *地图长
     */
    private int gameH;
    /**
     * 单块方格大小
     */
    private int squaresSize;
    /**
     * 当前焦点单位方格x坐标
     */
    private int focusX;
    /**
     * 当前焦点单位方格y坐标
     */
    private int focusY;
    /**
     * 鼠标坐标
     */
    int x, y;
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
     * 是否能安装塔
     */
    private boolean _caninstalltower;

    /**
     * 是否摧毁塔
     */
    private boolean broken;
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
    private int _time;




    private Map map = new Map();
    GameController _gc;

    private JRadioButton normalTower;
    private JButton _return;
    private JButton _BackWhenWin;
    private JButton _BackWhenDefeat;
    private JButton _Next;

    private JButton _Stop;
    JLabel Background;
    JLabel Tools;

    public GameMenu() {
        super("0度塔防");//设置标题
        this.setVisible(true);//设置为可见
        this.setSize(1024, 838);//设置窗体大小
        this.setLayout(null);//设置为空布局
        this.setLocationRelativeTo(null);//设置为集中显示
        this.setResizable(false);//设置不可改变窗体大小
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭线程;
        this.setVisible(true);
        this.setSize(1024, 838);
        /*
      绘制图片工具
     */
        Toolkit _tk = Toolkit.getDefaultToolkit();
        Image img = _tk.createImage("src/Image/logo.png");
        this.setIconImage(img);    //修改窗体默认图标
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置窗体关闭


        /*
      塔数组
     */
        List<Tower> towerList = new ArrayList<>();
        this._caninstalltower = false;
        this.InitialTower();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        _Stop = new JButton();
        _Stop.setVisible(true);
        _Stop.addActionListener(this);
        _Stop.setBounds(870, 580, 145, 40);
        _Stop.setBorderPainted(false);
        this.getContentPane().add(_Stop);

        _return = new JButton();
        _return.setVisible(true);
        _return.addActionListener(this);
        _return.setBounds(870, 630, 145, 40);
        _return.setBorderPainted(false);
        this.getContentPane().add(_return);
        _gc = new GameController(map, this);
        init();
        Thread thread = new Thread(this);
        thread.start();
        _gc.Start();
    }


    /**
     * 初始化窗口变量
     */
    private void init() {
        w = 1024;
        h = 838;
        gameX = 64;
        gameY = 64;
        gameW = 1024;
        gameH = 800;
        focusX = -64;
        focusY = -64;
        squaresSize = 64;
        drawMoney = true;
        _time=map.Period();
    }


    /***
     * 初始化塔
     */
    private void InitialTower() {
        normalTower = new JRadioButton(new ImageIcon("src/Image/TwNormal.png"), _caninstalltower);
        normalTower.setBounds(828, 318, 64, 64);
        normalTower.setOpaque(false);
        normalTower.addItemListener(this);
        ButtonGroup towerGroup = new ButtonGroup();
        this.add(normalTower);

    }

    public void update(Graphics g) {     //覆盖update方法，截取默认的调用过程
        paint(g);
    }

    /**
     * 绘制地图
     *
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        BufferedImage images = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Image image = null;
        Graphics g2 = images.createGraphics();

        //画地图
        try {
            image = ImageIO.read(new File("src/Image/Map.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //画工具栏
        g2.drawImage(image, 0, 0, this);
        try {
            image = ImageIO.read(new File("src/image/Tools.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, 840, 0, 184, 838, this);
        //画按钮

        try {
            image = ImageIO.read(new File("src/image/Stop.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, 870, 600, 145, 40, this);


        //画REturn
        try {
            image = ImageIO.read(new File("src/image/BackToMainMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, 870, 650, 145, 40, this);

        //画塔
        try {
            drawTowers(g2);
        } catch (Exception e) {
            System.err.print("画塔失败");

        }
        //画钱
        drawMoney(g2);
        g2.setColor(Color.white);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                g2.drawRect(j * squaresSize + gameX, i * squaresSize
                        + gameY, squaresSize, squaresSize);
            }
        }
        g2.setColor(Color.blue);
        if (focusX < 776) {
            try {
                image = ImageIO.read(new File("src/image/TwNormal.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (normalTower.isSelected()) {
                g2.drawOval(focusX - 64, focusY - 64, new TwNormal().GetAttackArea() * 64, new TwNormal().GetAttackArea() * 64);
                g2.drawImage(image, focusX, focusY, this);
            } else {
                g2.fillRect(focusX, focusY, squaresSize, squaresSize);
            }
        }
        if (!normalTower.isSelected()) {

            try {
                image = ImageIO.read(new File("src/image/TwNormal.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(image, 860, 350, this);

        } else {
            try {
                image = ImageIO.read(new File("src/image/TwNormal.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(image, 860, 350, this);
            g2.setColor(Color.WHITE);
            g2.fillOval(900, 900, 64, 64);
        }
        g2.setColor(Color.GREEN);
        g2.drawString("$5", 950, 390);
        drawMonster(g2);  //显示怪物
        drawLife(g2);
        drawRound(g2); //显示波数
        drawTime(g2);  //显示时间
        g2.setColor(Color.CYAN);
        g2.fillRect(64, 64, 64, 64);  //起点
        g2.setColor(Color.red);
        g2.fillRect(768, 768, 64, 64);//终点

        g2.setColor(Color.lightGray);
        for (int i = 1; i < 5; i++) {
            g2.fillRect(64, 64 * (i + 2), 60, 60);
        }
        for (int i = 1; i < 4; i++) {
            g2.fillRect(194, 64 * i, 60, 60);
        }
        for (int i = 3; i < 7; i++) {
            g2.fillRect(64 * i, 576, 60, 60);
        }
        for (int i = 7; i < 12; i++) {
            g2.fillRect(64 * i, 768, 60, 60);
        }
        for (int i = 6; i < 10; i++) {
            g2.fillRect(64 * i, 192, 60, 60);
        }
        for (int i = 4; i < 7; i++) {
            g2.fillRect(576, 64 * i, 60, 60);
        }



        g2.dispose();//在此函数前面调用g2画笔画其它图


        g.drawImage(images, 0, 0, this);
        //drawTools(g2);


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
            Font font = new Font("宋体", Font.BOLD, 8);
            g2.setFont(font);
            if (focusTower.GetLevel() < 6) {
                g2.drawString("up" + focusTower.GetPrice(), upX, upY + 8);
            }
            g2.drawString(" down", upX + 25, upY + 8);
        }
    }



    /**
     * 绘制总生命
     */
    private void drawLife(Graphics g) {

        Font font = new Font("宋体", Font.BOLD, 25);
        g.setColor(Color.white);
        g.setFont(font);

        g.drawString("生命值:" + map.HP(), 880, 192);

    }

    private void drawRound(Graphics g) {

        Font font = new Font("宋体", Font.BOLD, 25);
        g.setColor(Color.white);
        g.setFont(font);

        g.drawString("回合数：" + _gc.round() + "/" + map.total(), 880, 248);

    }

    /**
     * 绘制防御塔
     */
    private void drawTowers(Graphics g2) {
        Image image = new ImageIcon("src/Image/TwNormal.png").getImage();
        LinkedList<Tower> ll = _gc.Towers();
        for (Tower tower : ll) {
            g2.drawImage(image, tower.GetSurfaceLocation().x(), tower.GetSurfaceLocation().y(), this);
        }
    }
    /**
     * 绘制金钱
     */
    private void drawMoney(Graphics g) {
        if (drawMoney) {
            Font font = new Font("宋体", Font.BOLD, 30);
            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("$" + map.money(), 910, 85);
        }
        Font font = new Font("宋体", Font.BOLD, 30);
        g.setColor(Color.white);
        g.setFont(font);

        g.drawString("" + map.money(), 950, 130);
    }

    /**
     * 绘制怪物
     *
     * @param g2 画笔
     */
    private void drawMonster(Graphics g2) {
        LinkedList<Monster> ml = _gc.Monsters();
        for (Monster monster : ml) {  //遍历图中怪物链表
            monster.draw(g2);   //绘画每一个怪物
        }
    }

    /**
     * 显示倒计时
     * @param g 画笔
     */
    private void drawTime(Graphics g){
        Font font = new Font("宋体", Font.BOLD, 25); //新建字体对象
        g.setColor(Color.white);//设置字体颜色
        g.setFont(font); //设置字体

        g.drawString("下一波：" + _time , 880, 520);//显示倒计时

    }


    /**
     * 升级塔
     *
     */
    private void UpTower(final Tower tower) {
        map.SetMoney(map.money() - tower.GetPrice());
        tower.Upgrade();
    }

    /**
     * 摧毁塔
     *
     */
    private void downTower(Tower tower) {
        map.SetMoney(map.money() + tower.GetPrice() * tower.GetLevel());

    }

    /**
     * 刷新图片的线程
     */
    public void run() {
        int i=0;  //刷新次数
        try {
            while ( true) {
                Thread.sleep(20);  //每隔20毫秒刷新一次界面
                repaint();     //重绘
                if(i*20%1000==0&&_time>=0){  //每隔1秒时间减少1
                    _time--;
                }
                else if(_time<0){  //当时间变量为0时，重新赋值
                    _time=map.Period();
                }
                i++;   //刷新次数增1
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void mouseDragged(MouseEvent e) {
    }

    public  void actionPerformed(ActionEvent e) {
        if (e.getSource() == _return) {
            this.dispose();
        } else if (e.getSource() == _Stop) {
            try {
                _gc.wait();
                this.wait();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        new MainMenu().SetGameMenu(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (map.money() > new TwNormal().GetPrice() && focusX < 776 && normalTower.isSelected()) {
            _gc.AddTower(new Point(focusX, focusY), new TwNormal());
            if (SetMenu._isopenmusic) {
                TowerInstallMusic m = new TowerInstallMusic();
                m.start();
            }
            normalTower.setSelected(false);
            repaint();
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

    /**
     * 选项改变
     *
     * @param e 项事件
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        /*
        判断塔是否被选中
         */
        if (normalTower.isSelected() == true) {
            _caninstalltower = true;
            normalTower.setIcon(new ImageIcon("src/Image/MonNormal.png"));
            Image img = Toolkit.getDefaultToolkit().getImage("src/Image/TwNormal.png");
            Cursor cu = Toolkit.getDefaultToolkit().createCustomCursor(img, new java.awt.Point(), null);
            this.setCursor(cu);

        } else {
            _caninstalltower = false;
            normalTower.setIcon(new ImageIcon("src/Image/TwNormal.png"));
            this.setCursor(null);
        }
    }


}

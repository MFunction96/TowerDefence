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
import java.util.LinkedList;
import java.util.List;


import Controller.Thread.GameController;
import Model.BaseClass.*;
import Model.Framework.Map ;
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
    private boolean  atTools;
    private boolean drawTowerTools;
    private int changeTowerType;
    private Map  map=new Map() ;
    /**
     * 塔数组
     */
    private List<Tower> towerList;

    GameController _gc= new GameController(map);



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


    GameMenu() {
        super("0度塔防");
        this.setVisible(true);
        this.setSize(1024,838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(64, 64, 1024, 838);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setResizable(false);

        init();
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
        drawMoney =true ;
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
                g2.drawRect(j * squaresSize + x, i * squaresSize
                        + y, squaresSize, squaresSize);
            }
        }
        g2.setColor(Color.blue);
        g2.fillRect(focusX, focusY, squaresSize, squaresSize);
        drawTowers(g2);
        drawMoney(g2);
        drawMonster(g2);
        gr.drawImage(image, 0, 0, this);


    }
    /**
     * 绘制防御塔
     */
    private void drawTowers(Graphics g2) {

    }
    /**
     * 绘制金钱
     */
    private void drawMoney(Graphics g) {
        if (drawMoney) {
            Font font = new Font("宋体", 30, 30);
            g.setFont(font);
            g.setColor(Color.white);
            g.drawString("$" +map .money() , 900, 100);
        }
        Font font = new Font("宋体", 30, 30);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString("round" + map .period() , 890,
                200);
    }

    public void drawMonster(Graphics g) {
        LinkedList<Monster> monsterlist = _gc.getMonsterList();
        for (int i = 0; i < monsterlist.size(); i++) {
            Monster monster = monsterlist.get(i);
            if (monster != null) {
                monster.draw(g);
            }
        }
    }





    public static void main(String[] args) {
        new GameMenu() ;
    }

    public void mouseDragged(MouseEvent e) {

    }
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

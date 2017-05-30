package Model.Monster;

import Model.BaseClass.Monster;
import Model.BaseClass.Point;

import java.awt.*;
import java.util.ArrayDeque;

/**
 * Created by MFunction on 2017/4/19.
 * 事件驱动类
 */
public class MonNormal extends Monster{

    /**
     * 怪物的图片
     */
    private Image _nomal;

    /**
     * 构造普通怪
     * @param ad 路径
     */
    public MonNormal(ArrayDeque<Point> ad) {
        //初始化父类
        super("蘑菇怪",5,1,1,5,2,2,new Point(64,64),new Point(0,0));
        _nomal=Toolkit.getDefaultToolkit().getImage("src/Image/NormalMonster.png");//得到怪物的图片
        SetPath(ad);
    }

    /**
     * 画普通怪
     * @param g 画笔
     */
    public void draw(Graphics g) {
        g.drawImage(_nomal,_presurflocation.x(),_presurflocation.y(),null); //在表现层显示普通怪
        drawLifeStatus(g,_presurflocation.y(),_presurflocation.x()); //显示生命条
    }

}

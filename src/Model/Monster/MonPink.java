package Model.Monster;

import Model.BaseClass.*;
import Model.BaseClass.Point;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * Created by Chris Young on 2017/6/4.
 */
public class MonPink extends Monster{
    /**
     * 怪物的图片
     */
    private Image _pink;

    /**
     * 构造“鬼”怪
     *
     * @param ad 怪物的路径
     */
    public MonPink(LinkedList<Point> ad) {
        //初始化父类
        super("粉怪", 6, 4, 1, 5, 2, 2, new Model.BaseClass.Point(64, 64), new Model.BaseClass.Point(0, 0));
        //显示怪的样子
        _pink = Toolkit.getDefaultToolkit().getImage("src/Image/Monster2.png");
        //设置怪物的路径
        SetPath(ad);
    }

    /**
     * 绘画“鬼”怪
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(_pink, _presurflocation.x(), _presurflocation.y(), null);  //在表现层显示怪
        drawLifeStatus(g, _presurflocation.y(), _presurflocation.x());  //在相应位置显示生命条
    }
}

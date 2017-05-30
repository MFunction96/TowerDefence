package Model.Monster;

import Model.BaseClass.*;

import java.awt.*;
import java.util.ArrayDeque;

/**
 *
 * Created by Chris Young on 2017/5/29.
 */
public class MonGhost extends Monster {
    /**
     * 怪物的图片
     */
    private Image _ghost;

    /**
     * 构造“鬼”怪
     * @param ad 怪物的路径
     */
    public MonGhost(ArrayDeque<Model.BaseClass.Point> ad) {
        //初始化父类
        super("鬼",6,2,1,5,2,2,new Model.BaseClass.Point(64,64),new Model.BaseClass.Point(0,0));
        //显示怪的样子
        _ghost=Toolkit.getDefaultToolkit().getImage("src/Image/monster2.png");
        //设置怪物的路径
        SetPath(ad);
    }

    /**
     * 绘画“鬼”怪
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(_ghost,_presurflocation.x(),_presurflocation.y(),null);  //在表现层显示怪
        drawLifeStatus(g,_presurflocation.y(),_presurflocation.x());  //在相应位置显示生命条
    }
}

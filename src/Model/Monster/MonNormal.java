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

    private Image _nomal;

    /**
     * 构造普通怪
     * @param ad
     */
    public MonNormal(ArrayDeque<Point> ad) {
        super("蘑菇怪",5,1,1,5,2,2,new Point(64,64),new Point(0,0));

        _nomal=Toolkit.getDefaultToolkit().getImage("src/Image/NormalMonster.png");
        setPath(ad);
    }
    /*
        普通怪的形态
     */
    public void draw(Graphics g) {
        g.drawImage(_nomal,_surlocation.x(),_surlocation.y(),null);
    }

}

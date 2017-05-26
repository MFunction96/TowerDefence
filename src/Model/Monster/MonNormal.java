package Model.Monster;

import Model.BaseClass.Monster;
import Model.BaseClass.Point;
import View.GameMenu;

import java.awt.*;
import java.util.ArrayDeque;

/**
 * Created by MFunction on 2017/4/19.
 * 事件驱动类
 */
public class MonNormal extends Monster{

    private Image _nomal;
    private GameMenu _menu;


    public MonNormal(Monster monster, ArrayDeque<Point> ad) {
        super(monster,ad);
    }

    /**
     * 构造普通怪
     * @param monster 基础怪的元对象
     * @param ad 路径
     * @param menu 怪所在视图
     */
    public MonNormal(Monster monster, ArrayDeque<Point> ad,GameMenu menu) {
        super(monster,ad);
        _menu=menu;
        _nomal=Toolkit.getDefaultToolkit().getImage("src/Image/NormalMonster.png");

    }
    /*
        普通怪的形态
     */
    public void draw(Graphics g) {
        g.drawImage(_nomal,_surlocation.x(),_surlocation.y(),64,64,_menu);
    }

}

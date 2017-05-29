package Model.Monster;

import Controller.Thread.MonsterMoveController;
import Model.BaseClass.*;

import java.awt.*;
import java.awt.Point;
import java.util.ArrayDeque;

/**
 * Created by Chris Young on 2017/5/29.
 */
public class MonGhost extends Monster {
    private Image _ghost;
    public MonGhost(ArrayDeque<Model.BaseClass.Point> ad) {
        super("鬼",6,2,1,5,2,2,new Model.BaseClass.Point(64,64),new Model.BaseClass.Point(0,0));

        _ghost=Toolkit.getDefaultToolkit().getImage("src/Image/monster2.png");
        setPath(ad);
    }
    /*
        普通怪的形态
     */
    public void draw(Graphics g) {
        g.drawImage(_ghost,_presurflocation.x(),_presurflocation.y(),null);
        drawLifeStatus(g,_presurflocation.y(),_presurflocation.x());
    }
}

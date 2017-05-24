package Model.Monster;

import Model.BaseClass.Monster;
import Model.BaseClass.Point;

import java.util.ArrayDeque;

/**
 * Created by MFunction on 2017/4/19.
 * 事件驱动类
 */
public class MonNormal extends Monster{

    public MonNormal(Monster monster, ArrayDeque<Point> ad) {
        super(monster,ad);
    }

}

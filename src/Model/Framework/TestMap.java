package Model.Framework;

import Model.BaseClass.Point;
import Model.BaseClass.Monster;
import Model.BaseClass.Tower;
import Model.Monster.MonNormal;
import Model.Tower.TwNormal;

import java.util.LinkedList;


/**
 * Created by Chris Young on 2017/5/23.
 */
public class TestMap extends Map {
    public TestMap(){
        super(10,10,new Monster[5],new LinkedList<Tower>(),5,5,2,10,new Point(0,0),new Point(11,11));
    }
}

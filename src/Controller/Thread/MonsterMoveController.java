package Controller.Thread;

import Model.BaseClass.Monster;
import Model.BaseClass.Point;
import Model.Framework.Map;


import java.util.LinkedList;

/**
 * 怪物的表层移动线程
 * Created by Chris Young on 2017/5/25.
 */
public class MonsterMoveController implements Runnable {
    LinkedList<Monster> _monlist ;   //怪物链表
    GameController _gc;    //游戏主控制线程
    Map _map;   //地图信息
    Point _p;


    /**
     * 构造函数
     * @param map 地图信息
     */
    public MonsterMoveController(Map map) {
        _map = map;
        _gc = new GameController(map);
        _monlist = _gc._monsters;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < _monlist.size(); i++) {
                    Monster monster = _monlist.get(i);
                    if (monster == null)
                        break;
                    else if(monster.IsAlive()==true){
                        //如果怪活着
                        monster.SurfaceMove();  //调用怪物表层移动方法
                    }
                    else if(monster.IsAlive()==false){
                        _map.SetMoney(_map.money()+monster.GetPrice());//如果怪物死亡，更改地图金钱数
                        _monlist.remove(i); //移除死亡怪
                        i--;
                    }
                }
                Thread.sleep(640);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

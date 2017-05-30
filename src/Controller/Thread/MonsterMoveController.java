package Controller.Thread;

import Model.BaseClass.Monster;
import Model.Framework.Map;


import java.util.LinkedList;

/**
 * 怪物的表层移动线程
 * Created by Chris Young on 2017/5/25.
 */
public class MonsterMoveController extends Thread {

    /**
     * 游戏主控线程
     */
    GameController _gc;
    /**
     * 地图信息
     */
    Map _map;


    /**
     * 构造函数，初始化
     *
     * @param map 地图信息
     * @param gc  游戏主控线程
     */
    public MonsterMoveController(Map map, GameController gc) {
        _map = map;
        _gc = gc;
    }

    /**
     * 怪物移动方法：调用每个怪物的SurfaceMove()方法
     */
    @Override
    public synchronized void run() {
        try {
            LinkedList<Monster> _monlist = _gc._surmonsters;
            while (_monlist.size() > 0) {
                // Thread.sleep(500);
                for (Monster monster : _monlist) {
                    if (monster == null)          //如果没有怪物即链表为空，则跳出循环
                        break;
                    if (monster.IsAlive()) {      //如果怪活着
                        monster.SurfaceMove();  //调用怪物表层移动方法
                        //monster.OptMove();      //调用怪物逻辑层移动方法
                    } else {
                        _map.SetMoney(_map.money() + monster.GetPrice());//如果怪物死亡，更改地图金钱数
                        //_monlist.remove(i); //移除死亡怪
                    }
                    if (monster.GetOperationLocation() == _map.end()) {      //如果怪物走到出口
                        _map.Damage();                                   //调用地图Damage()方法，减少生命值
                        //_monlist.remove(i);                  //移除怪
                    }
                    wait(100);            //控制怪物的移动速度，每个100毫秒走一次
                }
            }
        } catch (Exception e) {                   //捕获异常
            e.printStackTrace();
        }

    }
}

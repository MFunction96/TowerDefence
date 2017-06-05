package Controller.Thread;

import Model.BaseClass.Monster;
import Model.Map.Block;

import java.util.LinkedList;

/**
 * Created by MFunction on 2017/5/20.
 * 攻击控制器线程类，计算怪物伤害
 *
 * @author MFunction
 */
public class AttackController extends Thread {
    /**
     * 游戏控制器线程
     */
    private GameController _gc;

    /**
     * 攻击控制器线程构造函数
     *
     * @param gc 游戏控制器线程对象
     */
    AttackController(GameController gc) {
        _gc = gc;
    }

    /**
     * 攻击控制器线程定义
     */
    public synchronized void run() {
        if (_gc._flag){
            LinkedList<Monster> ad = new LinkedList<>();
            for (Monster monster : _gc._monsters) {
                if (!_gc._flag) {
                    break;
                }
                Block b = _gc._map.block(monster.GetOperationLocation());
                monster.Hurt(b.Attack(monster));
                if (monster.Gethp() <= 0) {
                    ad.add(monster);
                    _gc._map.SetMoney(_gc._map.money() + monster.GetPrice());
                }
            }
            _gc._monsters.removeAll(ad);
        }
    }
}

package Controller.Thread;

import Model.BaseClass.Monster;
import Model.BaseClass.Tower;

import java.util.LinkedList;


/**
 * Created by MFunction on 2017/5/21.
 * 怪物控制器线程类，计算怪物行进路线
 *
 * @author MFunction
 */
public class MonsterController extends Thread {
    /**
     * 游戏控制器线程对象
     */
    private GameController _gc;
    /**
     * 路径控制器线程组
     */
    private PathController[] _pc;

    /**
     * 怪物控制器线程构造函数
     *
     * @param gc 游戏控制器线程对象
     */
    MonsterController(GameController gc) {
        _gc = gc;
        _pc = new PathController[_gc._monsters.size()];
    }

    /**
     * 怪物控制器主线程
     */
    public synchronized void run() {
        for (Monster monster : _gc._monsters) {
            if (monster.PreMove() == _gc._map.end()){
                _gc._map.Damage();
                if (_gc._map.HP() <= 0) {
                    _gc.Lose();
                    break;
                }
            }
            LinkedList<Tower> tw = _gc._map.block(monster.GetOperationLocation()).GetAtkTw();
            LinkedList<Tower> ptw = _gc._map.block(monster.PreMove()).GetAtkTw();
            for (Tower tower : tw){
                if (tower.GetTarget() == monster){
                    boolean flag = true;
                    for (Tower ptower : ptw){
                        if (tower == ptower) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag){
                        tower.SetTarget(null);
                    }
                }
            }
        }
        for (Monster monster : _gc._monsters) {
            LinkedList<Tower> ptw = _gc._map.block(monster.PreMove()).GetAtkTw();
            for (Tower tower : ptw){
                if (tower.GetTarget() == null){
                    tower.SetTarget(monster);
                }
            }
        }
    }
}

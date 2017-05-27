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
     * 怪物控制器线程构造函数
     *
     * @param gc 游戏控制器线程对象
     */
    MonsterController(GameController gc) {
        _gc = gc;
        _gc._pc = new PathController[200];
        _gc._pc[0] = new PathController(_gc, _gc._map.start(), _gc._sepath);
        //_gc._mvc=new MonsterMoveController[200];
        //_gc._mvc[0]=new MonsterMoveController(_gc._map);
        _gc._pc[0].run();
        //_gc._mvc[0].run();
    }

    /**
     * 怪物控制器主线程
     */
    public synchronized void run() {
        for (Monster monster : _gc._monsters) {
            if (monster.PreMove() == _gc._map.end()) {
                _gc._map.Damage();
                if (_gc._map.HP() <= 0) {
                    _gc.Lose();
                    break;
                }
            }
            LinkedList<Tower> tw = _gc._map.block(monster.GetOperationLocation()).GetAtkTw();
            LinkedList<Tower> ptw = _gc._map.block(monster.PreMove()).GetAtkTw();
            for (Tower tower : tw) {
                if (tower.GetTarget() == monster) {
                    boolean flag = true;
                    for (Tower pTower : ptw) {
                        if (tower == pTower) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        tower.SetTarget(null);
                    }
                }
            }
        }
        for (Monster monster : _gc._monsters) {
            LinkedList<Tower> ptw = _gc._map.block(monster.OptMove()).GetAtkTw();
            for (Tower tower : ptw) {
                if (tower.GetTarget() == null) {
                    tower.SetTarget(monster);
                }
            }
        }
    }
}

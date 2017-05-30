package Controller.Thread;

import Model.BaseClass.Monster;
import Model.BaseClass.Tower;
import com.sun.org.apache.bcel.internal.generic.NEW;

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
    }

    /**
     * 怪物控制器主线程
     */
    public synchronized void run() {
        LinkedList<Monster> ll = new LinkedList<>();
        if (_gc._monsters.size() > 0){
            for (Monster monster : _gc._monsters) {

                if (monster.PreMove() == _gc._map.end()) {
                    _gc._map.Damage();
                    if (_gc._map.HP() <= 0) {
                        _gc.Lose();
                        break;
                    }
                    ll.addLast(monster);
                }

                LinkedList<Tower> tw = _gc._map.block(monster.GetOperationLocation()).GetAtkTw();
                LinkedList<Tower> ptw = _gc._map.block(monster.PreMove()).GetAtkTw();
                if (tw.size() > 0){
                    for (Tower tower : tw) {
                        if (tower.GetTarget() == monster) {
                            boolean flag = true;
                            if (ptw.size() > 0){
                                for (Tower pTower : ptw) {
                                    if (tower == pTower) {
                                        flag = false;
                                        break;
                                    }
                                }
                            }
                            if (flag) {
                                tower.SetTarget(null);
                            }
                        }
                    }
                }

            }
            _gc._monsters.removeAll(ll);
            for (Monster monster : _gc._monsters) {

                monster.SurfaceMove();

                LinkedList<Tower> ptw = _gc._map.block(monster.OptMove()).GetAtkTw();
                if (ptw.size() > 0){
                    for (Tower tower : ptw) {
                        if (tower.GetTarget() == null) {
                            tower.SetTarget(monster);
                        }
                    }
                }
            }
        }
    }
}

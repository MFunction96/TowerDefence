package Controller.Thread;

import Model.Monster.*;

import java.util.LinkedList;

/**
 * Created by MFunction on 2017/4/17.
 * 怪物生成线程类
 *
 * @author MFunction
 */
public class MonsterGenerator extends Thread {
    /**
     * 等待时间
     */
    private int _stime;
    /**
     * 总计数
     */
    private int _total;
    /**
     * 游戏控制器线程
     */
    private GameController _gc;
    /**
     * 创建的怪物
     */
    private int _montyp;

    /**
     * 怪物生成器构造函数
     *
     * @param gc    游戏控制器
     * @param index 怪物索引
     */
    MonsterGenerator(GameController gc, int index) {
        _gc = gc;
        _stime = _gc._map.moninterval();
        _total = _gc._map.monnumber();
        _montyp = _gc._map.monster()[(index) % _gc._map.monster().length];
    }

    /**
     * 怪物生成器主线程
     */
    public synchronized void run() {
        try {
            for (int i = 0; i < _total && _gc._flag; i++) {
                if (_montyp == 1) {
                    _gc._monsters.addLast(new MonNormal(new LinkedList<>(_gc._spath)));
                } else if (_montyp == 2) {
                    _gc._monsters.addLast(new MonGhost(new LinkedList<>(_gc._spath)));
                } else if (_montyp == 3) {
                    _gc._monsters.addLast(new MonBlue(new LinkedList<>(_gc._spath)));
                } else if (_montyp == 4) {
                    _gc._monsters.addLast(new MonPink(new LinkedList<>(_gc._spath)));
                }
                wait(_stime);
            }

        } catch (InterruptedException e) {
            System.err.print(e.getMessage());

        }
    }
}

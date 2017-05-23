package Controller.Thread;

import Model.BaseClass.Monster;

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
    private Monster _monster;

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
        _monster = _gc._map.monster()[(index - 1) % _gc._map.monster().length];
        _monster.Upgrade();
    }

    /**
     * 怪物生成器主线程
     */
    public synchronized void run() {
        try {
            for (int i = 0; i < _total; i++) {
                _gc._monsters.addLast(_monster);
                sleep(_stime);
            }
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
    }
}

package Controller.Thread;

import Model.Monster.*;

/**
 * Created by MFunction on 2017/4/17.
 * 怪物生成线程类
 */
public class MonsterGenerator extends Thread {
    /**
     *
     */
    private int _stime;
    private int _cnt;
    private int _index;
    private GameController _gc;

    /**
     * @param time 时间间隔
     */
    MonsterGenerator(int time, int cnt, GameController gc, int index) {
        _stime = time;
        _gc = gc;
        _cnt = cnt;
        _index = index;
    }

    public synchronized void run() {
        try {
            for (int i = 0; i < _cnt; i++) {
                if (_index == 0) {
                    _gc._monsters.addLast(new MonNormal());
                }
                sleep(_stime);
            }
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
    }
}

package Controller;

import BaseClass.Monster;
import Monster.MonNormal;
import java.util.LinkedList;

/**
 * Created by MFunction on 2017/4/17.
 * 时间驱动类
 */
public class MonsterGenerator extends Thread {
    /**
     *
     */
    private int _stime;
    private int _cnt;
    private LinkedList _monsters;
    private Monster _monster;

    /**
     * @param time 时间间隔
     */
    public MonsterGenerator(int time, int cnt, LinkedList monsters, Monster monster) {
        _stime = time;
        _monsters = monsters;
        _cnt = cnt;
        _monster = monster;
    }

    public void run() {
        try {
            for (int i = 0; i < _cnt; i++) {
                _monsters.addLast(new MonNormal());
                Thread.sleep(_stime);
            }
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
    }
}

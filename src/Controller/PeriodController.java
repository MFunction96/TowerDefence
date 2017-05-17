package Controller;

import BaseClass.Monster;

import java.util.LinkedList;

/**
 * Created by MFunction on 2017/5/17.
 *
 * @author MFunction
 */
public class PeriodController extends Thread {
    private int _stime;
    private int _cnt;
    private LinkedList _monsters;
    private Monster _monster;
    private GameController _gc;

    public PeriodController(int time, int cnt, LinkedList monsters, Monster monster,GameController gc) {
        _stime = time;
        _monsters = monsters;
        _cnt = cnt;
        _monster = monster;
        _gc=gc;
    }

    public void run() {
        try {
            for (int i=0;i<_cnt;i++){
                _gc.GenMon();
                sleep(_stime);
            }
        }catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
    }
}

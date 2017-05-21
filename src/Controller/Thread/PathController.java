package Controller.Thread;

import Model.BaseClass.Monster;

/**
 * Created by MFunction on 2017/5/17.
 *
 * @author MFunction
 */
public class PathController extends Thread {
    private int _stime;
    private int _cnt;
    private Monster _monster;
    private GameController _gc;

    PathController(int time, int cnt, GameController gc) {
        _stime = time;
        _cnt = cnt;
        _gc = gc;
    }

    public synchronized void run() {
        try {
            for (int i = 0; i < _cnt; i++) {
                _gc._mongen.run();
                sleep(_stime);
            }
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
    }
}

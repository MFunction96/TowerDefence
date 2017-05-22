package Controller.Thread;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by MFunction on 2017/5/21.
 * 怪物控制器线程类，计算怪物行进路线
 *
 * @author MFunction
 */
public class MonsterController extends Thread {
    /**
     *
     */
    private GameController _gc;
    /**
     *
     */
    private LinkedList _monster;
    private PathController[] _pc;

    /**
     *
     * @param gc
     */
    MonsterController(GameController gc) {
        _gc = gc;
        _monster = _gc._monsters;
        _pc = new PathController[_monster.size()];
    }

    /**
     *
     */
    public synchronized void run() {
        int i=0;
        for (Iterator it = _monster.iterator();it.hasNext();){
            it.next();
            i++;

        }
    }
}

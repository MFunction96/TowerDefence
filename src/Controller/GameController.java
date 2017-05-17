package Controller;

import BaseClass.Monster;
import Frame.Map;
import java.util.LinkedList;

/**
 * Created by MFunction on 2017/4/17.
 *
 * @author MFunction
 */
public class GameController {
    private Map m = new Map("Config.xml");
    private int _round;
    private Map _m;
    private Monster[] _montmp;
    private LinkedList _monsters;

    GameController(Map m) {
        _m = m;
        _montmp = _m.monster();
    }

    /**
     * 获取当前回合
     *
     * @return 返回当前回合数
     */
    public int round() {
        return _round;
    }

    /**
     *
     */
    public void Win() {

    }

    /**
     *
     */
    public void Pause() {

    }

    /**
     *
     */
    public void Lose() {

    }

    public void run() {

    }

    void GenMon() {
        MonsterGenerator mg = new MonsterGenerator(_m.moninterval(), _m.monnumber(), _monsters, _montmp[0]);
        mg.run();
    }
}

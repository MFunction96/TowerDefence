package Controller;

import BaseClass.Monster;
import Frame.Map;

import java.util.LinkedList;

/**
 * Created by MFunction on 2017/4/17.
 *
 * @author MFunction
 */
public class GameController extends Thread {
    private int _round;
    private int _hp;
    MonsterGenerator _mongen;
    PeriodController _perctl;
    Map _map;
    Monster[] _montmp;
    LinkedList _monsters;

    /**
     * @param map 地图
     */
    GameController(Map map) {
        _map = map;
        _hp = _map.HP();
        _montmp = _map.monster();
        _perctl = new PeriodController(_map.period(), _map.total(), this);
    }

    /**
     *
     */
    void Start() {
        run();
        _perctl.run();
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
    private void Win() {

    }

    /**
     *
     */
    public void Pause() {

    }

    /**
     *
     */
    private void Lose() {

    }

    /**
     *
     */
    public synchronized void run() {
        while (true) {
            if (_hp == 0) {
                Lose();
                break;
            } else if (_monsters.size() == 0 && _round == _map.total()) {
                Win();
                break;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.err.print(e.getMessage());
            }
        }
    }
}

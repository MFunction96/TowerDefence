package Controller.Thread;

import Model.BaseClass.Monster;
import Model.BaseClass.Point;
import Model.BaseClass.Tower;
import Model.Framework.Map;

import java.util.ArrayDeque;
import java.util.LinkedList;

import View.DefeatMenu;
import View.GameMenu;
import View.WinMenu;

/**
 * Created by MFunction on 2017/4/17.
 * 游戏控制器线程类，控制游戏的进行与结束
 *
 * @author MFunction
 */
public class GameController extends Thread {
    /**
     * 当前回合
     */
    private int _round;
    /**
     * 当前生命值
     */
    private int _hp;
    private GameMenu _gm;
    private MonsterController _mc;
    private AttackController _ac;
    private MonsterGenerator _monger;
    private TowerController _tc;
    /**
     * 游戏地图
     */
    Map _map;
    /**
     * 路径控制器线程组
     */
    private volatile PathController _pc;
    /**
     * 在场怪物
     */
    volatile LinkedList<Monster> _monsters;

    volatile LinkedList<Tower> _towers;
    /**
     *
     */
    volatile ArrayDeque<Point> _spath;


    /**
     * 构造游戏控制器
     *
     * @param map 地图
     */
    public GameController(Map map, GameMenu gm) {
        _map = map;
        _hp = _map.HP();
        _round = 0;
        _gm = gm;
        _spath = new ArrayDeque<>();
        _monsters = new LinkedList<>();
        _towers = new LinkedList<>();
        _pc = new PathController(this, _map.start());
        _spath = _pc.CalPath();
    }

    /**
     * 游戏开始
     */
    public void Start() {
        this.start();
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
     * 游戏胜利
     */
    private void Win() {
        _ac.interrupt();
        _mc.interrupt();
        _monger.interrupt();
        interrupt();
        new WinMenu(_gm);
    }

    /**
     * 游戏暂停
     */
    public void Pause() {
        try {
            _ac.wait();
            _mc.wait();
            _monger.wait();
            _pc.wait();
            wait();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            wait();
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
    }

    /**
     * 游戏失败
     */
    void Lose() {
        _ac.interrupt();
        _mc.interrupt();
        _monger.interrupt();
        new DefeatMenu(_gm);
        interrupt();

    }

    /**
     * 游戏控制器主线程
     */
    public synchronized void run() {
        int time = 0;
        while (true) {
            try {
                wait(1000);
            } catch (InterruptedException e) {
                System.err.print(e.getMessage());
            }
            time++;
            if (_round <= _map.total() && _round >= 0 && time % _map.Period() == 0) {
                _monger = new MonsterGenerator(this, _round++);
                _monger.start();
            } else if (_monsters.size() == 0 && _round == _map.total()) {
                Win();
                break;
            } else if (_hp <= 0) {
                Lose();
                break;
            }
            if (time > _map.Period()) {
                _ac = new AttackController(this);
                _mc = new MonsterController(this);
                _ac.start();
                _mc.start();
            }
        }
    }

    public LinkedList<Monster> Monsters() {
        return _monsters;
    }

    public LinkedList<Tower> Towers() {
        return _towers;
    }

    public void AddTower(Point p, Tower tower) {
        tower.SetTower(p, p.SurConvOpt());
        _tc = new TowerController(this, p.SurConvOpt(), tower);
        _tc.setPriority(10);
        _tc.start();
    }
}

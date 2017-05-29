package Controller.Thread;

import Model.BaseClass.Monster;
import Model.BaseClass.Point;
import Model.Framework.Map;

import java.util.ArrayDeque;
import java.util.LinkedList;
import View.GameMenu;

import javax.swing.text.View;

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
    /**
     * 怪物控制器线程对象
     */
    private MonsterController _mc;
    /**
     * 游戏地图
     */
    Map _map;
    /**
     * 路径控制器线程组
     */
    volatile PathController[] _pc;
    /**
     * 在场怪物
     */
    volatile LinkedList<Monster> _monsters;
    volatile LinkedList<Monster>_surmonsters;
    /**
     *
     */
    volatile ArrayDeque<Point> _sepath;

    volatile MonsterMoveController _mvc;

    /**
     * 构造游戏控制器
     *
     * @param map 地图
     */
    public GameController(Map map) {
        _map = map;
        _hp = _map.HP();
        _round = 0;
        _sepath = new ArrayDeque<>();
        _monsters = new LinkedList<>();
        _surmonsters=new LinkedList<>();
        _mc = new MonsterController(this);
        _mvc = new MonsterMoveController(_map, this);
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
    new GameMenu() .showWin() ;
    }

    /**
     * 游戏暂停
     */
    public void Pause() {
        try {
            wait();
        } catch (InterruptedException e) {
            System.err.print(e.getMessage());
        }
    }

    /**
     * 游戏失败
     */
    public void Lose() {
        new GameMenu() .showDefeat() ;
    }

    /**
     * 游戏控制器主线程
     */
    public synchronized void run() {
        int time = 0;
        while (true) {
            time++;
            if ((_round <= _map.total() && _round > 0) || time == _map.Period()) {
                MonsterGenerator _mongen = new MonsterGenerator(this, _round++);
                _mongen.run();
            } else if (_monsters.size() == 0 && _round == _map.total()) {
                Win();
                break;
            } else if (_hp <= 0) {
                Lose();
                break;
            }
            if (time >= _map.Period()) {
                AttackController _ac = new AttackController(this);
                _ac.run();
                _mc.run();
                _mvc.run();
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    System.err.print(e.getMessage());
                }
            }
        }
    }

    public LinkedList<Monster> getMonsterList() {
        return _surmonsters;
    }
}

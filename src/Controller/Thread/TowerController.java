package Controller.Thread;

import Model.BaseClass.Monster;
import Model.BaseClass.Point;
import Model.BaseClass.Tower;

import java.util.LinkedList;

/**
 * Created by MFunction on 2017/5/30.
 *
 * @author MFunction
 */
public class TowerController extends Thread {
    private GameController _gc;
    private Point _p;
    private Tower _tower;

    /**
     * @param gc 游戏控制器
     */
    TowerController(GameController gc, Point p, Tower tower) {
        _gc = gc;
        _p = p;
        _tower = tower;
    }

    /**
     *
     */
    public synchronized void run() {
        final Point dp[] = {new Point(-1, -1), new Point(-1, 0), new Point(-1, 1), new Point(0, -1), new Point(0, 1), new Point(1, -1), new Point(1, 0), new Point(1, 1)};
        try {
            _gc._map.block(_p).SetTower(_tower);
        } catch (Exception e) {
            e.printStackTrace();
        }

        _gc._map.SetMoney(_gc._map.money() - _tower.GetPrice());
        _gc._towers.addLast(_tower);
        for (int i = 0; i < 8; i++) {
            Point pp = _p.Add(dp[i]);
            if (PathController.IsValid(pp)) {
                _gc._map.block(pp).AddTower(_tower);
            }
        }
        if (_gc._map.block(_p).IsPath())
        {
            _gc._map.Reset();
            PathController _pc = new PathController(_gc, _gc._map.start());
            _gc._spath = _pc.CalPath();
            LinkedList<Monster> monsters = _gc.Monsters();
            for (Monster monster : monsters) {
                _pc = new PathController(_gc, monster.GetOperationLocation());
                monster.SetPath(new LinkedList<>(_pc.CalPath()));
            }
        }
    }
}

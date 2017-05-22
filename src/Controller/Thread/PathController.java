package Controller.Thread;

import Model.BaseClass.Location;
import Model.BaseClass.Monster;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by MFunction on 2017/5/17.
 * 路径搜索线程
 *
 * @author MFunction
 */
public class PathController extends Thread {
    /**
     * 搜索的怪物
     */
    private Monster _monster;
    /**
     * 游戏控制器线程
     */
    private GameController _gc;

    /**
     * 线程类构造函数
     *
     * @param gc      游戏控制器线程对象
     * @param monster 搜索的怪物对象
     */
    PathController(GameController gc, Monster monster) {
        _gc = gc;
        _monster = monster;
    }

    /**
     * 判断新坐标是否可前进
     *
     * @param l 新坐标
     * @return 新坐标是否可前进
     */
    private boolean CanGo(Location l) {
        int n = 12, m = 12;
        if (l.y() % 2 == 0) {
            m--;
        }
        return l.x() >= 0 && l.y() >= 0 && l.x() < m && l.y() < n;
    }

    /**
     * 路径搜索主线程
     */
    public synchronized void run() {
        Queue<Location> q = new LinkedList<>();
        q.offer(_monster.GetOperationLocation());
        while (!q.isEmpty()) {
            Location l = q.poll();
            if (_gc._map.block(l).IsPath()) {
                break;
            }
        }
    }
}

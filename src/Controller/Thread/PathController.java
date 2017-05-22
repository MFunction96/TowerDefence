package Controller.Thread;

import Model.BaseClass.Point;
import Model.BaseClass.Monster;

import java.util.ArrayDeque;
import java.util.Deque;
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
    private Deque<Point> dq = new ArrayDeque<>();
    private Point _dl[] = {new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};

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
    private boolean CanGo(Point l) {
        return l.x() >= 0 && l.y() >= 0 && l.x() < 12 && l.y() < 12;
    }

    /**
     * 路径搜索主线程
     */
    public synchronized void run() {
        Queue<Point> q = new LinkedList<>();
        q.offer(_monster.GetOperationLocation());
        while (!q.isEmpty()) {
            Point l = q.poll();
            for (int i = 0; i < 4; i++) {
                Point ll = l.Add(_dl[i]);
                if (CanGo(ll)) {
                    if (_gc._map.block(l).IsPath()) {
                        break;
                    }
                }

            }
        }
    }
}

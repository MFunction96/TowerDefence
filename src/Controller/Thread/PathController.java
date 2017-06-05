package Controller.Thread;

import Model.BaseClass.Point;

import java.util.*;

/**
 * Created by MFunction on 2017/5/17.
 * 路径搜索线程
 *
 * @author MFunction
 */
class PathController {
    /**
     * 搜索的起点
     */
    private Point _p;
    /**
     * 游戏控制器线程
     */
    private GameController _gc;
    private final Point _dp[] = {new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};
    private int[][] vis;

    /**
     * 线程类构造函数
     *
     * @param gc 游戏控制器线程对象
     * @param p  搜索的怪物对象
     */
    PathController(GameController gc, Point p) {
        _gc = gc;
        _p = p;
        vis = new int[30][30];
    }

    /**
     * 判断新坐标是否可前进
     *
     * @param p 新坐标
     * @return 新坐标是否可前进
     */
    static boolean IsValid(Point p) {
        return p.x() >= 0 && p.y() >= 0 && p.x() < 12 && p.y() < 12;
    }

    /**
     * 路经计算核心函数
     *
     * @return 路径
     */
    LinkedList<Point> CalPath() {
        final int INF = 0x7fffffff;
        boolean flag = false;
        LinkedList<Point> q = new LinkedList<>();
        LinkedList<Point> ad = new LinkedList<>();
        q.addLast(_p);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                vis[i][j] = -1;
            }
        }
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (!_gc._map.block(new Point(j, i)).CanPass()) {
                    vis[i][j] = INF;
                }
            }
        }
        vis[_p.y()][_p.x()] = 0;
        while (!q.isEmpty() && _gc._flag) {
            Point p = q.removeFirst();
            for (int i = 0; i < 4 && _gc._flag; i++) {
                Point pp = p.Add(_dp[i]);
                if (IsValid(pp) && vis[pp.y()][pp.x()] < 0) {
                    vis[pp.y()][pp.x()] = vis[p.y()][p.x()] + 1;
                    if (pp.Equal(_gc._map.end())) {
                        flag = true;
                        break;
                    }
                    q.addLast(pp);
                }
            }
            if (flag) {
                break;
            }
        }
        if (flag && _gc._flag) {
            for (Point p = _gc._map.end(); !p.Equal(_gc._map.start()) && _gc._flag; ) {
                _gc._map.block(p).SetPath(true);
                ad.addFirst(p);
                for (int i = 0; i < 4; i++) {
                    Point pp = p.Add(_dp[i]);
                    if (IsValid(pp) && vis[pp.y()][pp.x()] == (vis[p.y()][p.x()] - 1)) {
                        p = pp;
                        break;
                    }
                }
            }
        }
        return ad;
    }
}

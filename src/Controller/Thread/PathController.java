package Controller.Thread;

import Model.BaseClass.Point;

import java.util.*;

/**
 * Created by MFunction on 2017/5/17.
 * 路径搜索线程
 *
 * @author MFunction
 */
public class PathController extends Thread {
    /**
     * 搜索的起点
     */
    private Point _p;
    /**
     * 游戏控制器线程
     */
    private GameController _gc;
    private ArrayDeque<Point> _ad;
    private Point _dp[] = {new Point(1, 0), new Point(0, 1), new Point(-1, 0), new Point(0, -1)};
    private int[][] vis;

    private final int INF = 0x7fffffff;

    /**
     * 线程类构造函数
     *
     * @param gc 游戏控制器线程对象
     * @param p  搜索的怪物对象
     */
    PathController(GameController gc, Point p, ArrayDeque<Point> ad) {
        _gc = gc;
        _p = p;
        _ad = ad;
        vis = new int[30][30];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                vis[i][j] = -1;
            }
        }
    }

    /**
     * 判断新坐标是否可前进
     *
     * @param p 新坐标
     * @return 新坐标是否可前进
     */
    private boolean CanGo(Point p) {
        return p.x() >= 0 && p.y() >= 0 && p.x() < 12 && p.y() < 12;
    }

    /**
     * 路经计算核心函数
     *
     * @return 路径
     */
    private ArrayDeque<Point> CalPath() {
        boolean flag = false;
        Queue<Point> q = new LinkedList<>();
        ArrayDeque<Point> ad = new ArrayDeque<>();
        q.offer(_p);
        vis[_p.y()][_p.x()] = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (!_gc._map.block(new Point(j, i)).CanPass()) {
                    vis[i][j] = INF;
                }
            }
        }
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                Point pp = p.Add(_dp[i]);
                if (CanGo(pp) && vis[pp.y()][pp.x()] < 0) {
                    vis[pp.y()][pp.x()] = vis[p.y()][p.x()] + 1;
                    if (pp.Equal(_gc._map.end())) {
                        flag = true;
                        break;
                    }
                    q.offer(pp);
                }
            }
        }
        if (flag) {
            for (Point p = _gc._map.end(); !p.Equal(_gc._map.start()); ) {
                ad.addFirst(p);
                for (int i = 0; i < 4; i++) {
                    Point pp = p.Add(_dp[i]);
                    if (CanGo(pp) && vis[pp.y()][pp.x()] == (vis[p.y()][p.x()] - 1)) {
                        p = pp;
                        break;
                    }
                }
            }
        }
        return ad;
    }

    /**
     * 路径搜索主线程
     */
    public synchronized void run() {
        ArrayDeque<Point> ad = CalPath();
        while (!ad.isEmpty()) {
            _ad.addFirst(ad.removeFirst());
        }
    }
}

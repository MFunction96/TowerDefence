package Model.BaseClass;

/**
 * Created by MFunction on 2017/4/15.
 * 点类，描述某对象的坐标的基础类
 *
 * @author MFunction
 */
public class Point {
    private int _x, _y;

    /**
     * 构造点对象
     *
     * @param x 点对象的横坐标
     * @param y 点对象的纵坐标
     */
    public Point(int x, int y) {
        _x = x;
        _y = y;
    }

    /**
     * 复制构造函数
     *
     * @param p 点对象
     */
    public Point(Point p) {
        _x = p._x;
        _y = p._y;
    }

    /**
     * 点对象的加操作
     *
     * @param p 点对象的相加对象
     * @return 返回加操作后的点对象
     */
    public final Point Add(final Point p) {
        return new Point(_x + p._x, _y + p._y);
    }

    /**
     * 点对象的相减操作
     *
     * @param p 减去的点坐标
     * @return 两点的方向向量
     */
    public final Point Minus(final Point p) {
        return new Point(_x - p._x, _y - p._y);
    }

    /**
     * 表现层坐标转换为业务层坐标
     *
     * @param p 表现层坐标
     * @return 业务层坐标
     */
    public final Point SurConvOpt() {
        return new Point((_x - 64) / 64, (_y - 64) / 64);
    }

    /**
     * 业务层坐标转换为业务层坐标
     *
     * @return 表现层坐标
     */
    public final Point OptConvSur() {
        return new Point(_x * 64 + 64, _y * 64 + 64);
    }

    /**
     * 判断两点坐标是否相同
     *
     * @param p 另一点对象
     * @return 返回boolean类型，true为两点相同，否则两点不同
     */
    public final boolean Equal(final Point p) {
        return _x == p._x && _y == p._y;
    }

    /**
     * 获取点对象横坐标
     *
     * @return 点对象的横坐标
     */
    public final int x() {
        return _x;
    }

    /**
     * 获取点对象纵坐标
     *
     * @return 点对象的纵坐标
     */
    public final int y() {
        return _y;
    }
}
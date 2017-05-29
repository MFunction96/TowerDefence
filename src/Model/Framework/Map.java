package Model.Framework;

import Model.BaseClass.Point;
import Model.BaseClass.Monster;
import Model.BaseClass.Tower;
import Model.Map.Block;
import Model.Monster.MonNormal;
import Model.Tower.TwNormal;

import java.util.LinkedList;

/**
 * Created by MFunction on 2017/4/17.
 * This class is about game map.
 */
public class Map {
    /**
     * 生命值
     */
    private int _hp;
    /**
     * 关卡数
     */
    private int _total;
    /**
     * 怪物信息
     */
    private int[] _monster = new int[2];
    /**
     * 塔信息
     */
    private Tower[] _tower;
    /**
     * 出怪数量
     */
    private int _monnumber;
    /**
     * 出怪间隔
     */
    private int _moninterval;
    /**
     * 金钱数
     */
    private int _money;
    /**
     * 起点
     */
    private Point _start;
    /**
     * 终点
     */
    private Point _end;
    /**
     * 区块
     */
    private Block[][] _blocks = new Block[12][12];
    /**
     * 每波周期
     */
    private int _period;


    public Map() {
        _hp = 5;
        _total = 5;
        _tower = new TwNormal[5];
        _monnumber = 5;
        _moninterval = 50;
        _money = 100;
        _start = new Point(0, 0);
        _end = new Point(11, 11);
        _period = 2000;
        for (int i = 0,j=1; i <2 ; i++,j++) {
            _monster[i] = j;
        }

        for (int i = 0, y = 1; i < 12; i++, y++) {
            for (int j = 0, x = 1; j < 12; j++, x++) {
                _blocks[i][j] = new Block(new Point(y * 64, x * 64), new Point(j, i), true, false);
            }
        }
    }

    /**
     * 获取生命值
     *
     * @return 当前生命值
     */
    public int HP() {
        return _hp;
    }

    /**
     * 获取总波数
     *
     * @return 总波数
     */
    public int total() {
        return _total;
    }

    /**
     * 获取出怪数量
     *
     * @return 出怪数量
     */
    public int monnumber() {
        return _monnumber;
    }

    /**
     * 获取出怪间隔
     *
     * @return 出怪间隔
     */
    public int moninterval() {
        return _moninterval;
    }

    /**
     * 获取金钱
     *
     * @return 金钱
     */
    public int money() {
        return _money;
    }

    /**
     * 获取起点
     *
     * @return 起点
     */
    public Point start() {
        return _start;
    }

    /**
     * 获取终点
     *
     * @return 终点
     */
    public Point end() {
        return _end;
    }

    /**
     * 获取怪物信息
     *
     * @return 怪物信息
     */
    public int[] monster() {
        return _monster;
    }

    /**
     * 获取塔信息
     *
     * @return 塔信息
     */
    public Tower[] tower() {
        return _tower;
    }

    /**
     * 获取砖块
     *
     * @param l 砖块位置
     * @return 指定位置砖块
     */
    public Block block(Point l) {
        return _blocks[l.y()][l.x()];
    }

    /**
     * 扣除生命
     */
    public void Damage() {
        _hp--;
    }

    /**
     * 修改金钱
     */
    public void SetMoney(int money) {
        _money = money;
    }

    /**
     * 重置地图标记状态
     */
    public void Reset() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                _blocks[i][j].Reset();
            }
        }
    }

    /**
     * 周期时间
     * @return 周期时间
     */
    public int Period(){
        return _period;
    }

}

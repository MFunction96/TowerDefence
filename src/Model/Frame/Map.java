package Model.Frame;

import Model.BaseClass.Location;
import Model.BaseClass.Monster;
import Model.BaseClass.Tower;
import Model.Map.Block;

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
    private Monster[] _monster;
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
     * 波数
     */
    private int _period;
    /**
     * 金钱数
     */
    private int _money;
    /**
     * 起点
     */
    private Location _start;
    /**
     * 终点
     */
    private Location _end;
    /**
     * 区块
     */
    private Block[][] _blocks = new Block[12][12];

    /**
     * 从文件构造对象
     *
     * @param cfgpath 外部文件路径
     */
    public Map(String cfgpath) {

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
     * 获取波数
     *
     * @return 波数
     */
    public int period() {
        return _period;
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
    public Location start() {
        return _start;
    }

    /**
     * 获取终点
     *
     * @return 终点
     */
    public Location end() {
        return _end;
    }

    /**
     * 获取怪物信息
     *
     * @return 怪物信息
     */
    public Monster[] monster() {
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
    public Block block(Location l) {
        return _blocks[l.y()][l.x()];
    }

    /**
     * 扣除生命
     */
    public void Damage() {

    }

    /**
     * 修改金钱
     */
    public void UpdateMoney() {

    }

    /**
     * 修改波数
     */
    public void UpdatePeriod() {

    }
}

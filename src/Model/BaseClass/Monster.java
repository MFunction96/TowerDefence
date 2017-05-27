package Model.BaseClass;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.UUID;

/**
 * Created by MFunction on 2017/4/17.
 * This class is about Monster.
 */
abstract public class Monster {
    /**
     * 生命值
     */
    protected int _hp;
    /**
     * 移动速度
     */
    protected int _speed;
    /**
     * 价值
     */
    protected int _price;
    /**
     * 身份标识
     */
    protected UUID _uuid;
    /**
     * 名称
     */
    protected String _name;
    /**
     * 类型
     */
    protected  int _type;
    /**
     * 表现层位置
     */
    protected Point _surlocation;
    /**
     * 业务层位置
     */
    protected Point _optlocation;
    /**
     * 升级生命值
     */
    protected int _uphp;
    /**
     * 升级加价值
     */
    protected int _upprice;
    /**
     * 怪物行进路径
     */
    protected ArrayDeque<Point> _ad = new ArrayDeque<>();

    /**
     * 构造基础怪
     *
     * @param name          怪的名字
     * @param hp            怪的生命值
     * @param speed         怪的速度
     * @param price         怪的价值
     * @param upgradehp     怪的血量升级
     * @param upgradePrice  怪的价值升级
     */

    public Monster(String name, int hp,int type, int speed, int price, int upgradehp, int upgradePrice,Point surfacePoint,  Point operatorPoint,ArrayDeque<Point> ad ) {
        _name = name;
        _type=type;
        _uuid = UUID.randomUUID();
        _speed = speed;
        _price = price;
        _surlocation = surfacePoint;
        _optlocation = operatorPoint;
        _upprice = upgradePrice;
        _uphp = upgradehp;
        _hp = hp;
        _ad=ad;
    }



    /*
    public Monster(Monster monster, ArrayDeque<Point> ad) {
        _name = monster._name;
        _uuid = UUID.randomUUID();
        _speed = monster._speed;
        _price = monster._price;
        _surlocation = monster._surlocation;
        _optlocation = monster._optlocation;
        _upprice = monster._upprice;
        _uphp = monster._uphp;
        _hp = monster._hp;
    }
*/


    /**
     * 获取怪的名称
     *
     * @return 返回怪的名称
     */
    public final String GetName() {
        return _name;
    }

    /**
     * 获取怪的ID
     *
     * @return 返回怪的ID
     */
    public final UUID GetUUID() {
        return _uuid;
    }

    /**
     * 获取怪的生命值
     *
     * @return 返回怪的生命值
     */
    public final int Gethp() {
        return _hp;
    }

    /**
     * 获取怪的速度
     *
     * @return 返回怪的速度
     */
    public final int GetSpeed() {
        return _speed;
    }

    /**
     * 获取怪的价值
     *
     * @return 返回怪的价值
     */
    public final int GetPrice() {
        return _price;
    }

    /**
     * 获取怪升级血量
     *
     * @return 返回怪增长的血量
     */
    public final int GetUpgradehp() {
        return _uphp;
    }

    /**
     * 获取怪升级的价格
     *
     * @return 返回怪升级的价格
     */
    public final int GetUpgradePrice() {
        return _upprice;
    }

    /**
     * 获取怪在UI界面的位置
     *
     * @return 返回怪在UI界面的位置
     */
    public final Point GetSurfaceLocation() {
        return _surlocation;
    }

    /**
     * 获取怪在后台操作的位置
     *
     * @return 返回怪在后台操作的位置
     */
    public final Point GetOperationLocation() {
        return _optlocation;
    }

    /**
     * 怪在UI上的移动
     */
    public void SurfaceMove() {
        Point temp;
        temp=_ad.getFirst().Minus(_optlocation);  //计算方向向量
        _surlocation=new Point(temp.x()*64,temp.y()*64); //转换成表现层的下一个坐标
    }

    /**
     * 怪在后台操作的移动
     *
     * @return 新坐标
     */
    public Point OptMove() {
        return _optlocation = _ad.removeFirst();
    }

    /**
     * 怪下一步移动坐标
     *
     * @return 新坐标
     */
    public Point PreMove() {
        return _ad.getFirst();
    }

    /**
     * 对怪造成伤害
     *
     * @param damage 对怪的伤害值
     */
    public void Hurt(int damage) {
        _hp -= damage;
    }

    /**
     * 对怪进行升级,提高难度
     */
    public void Upgrade() {
        _price += _upprice;
        _hp += _uphp;
    }

    /**
     * 怪物行进路径
     *
     * @param ad 行进路径
     */
    public void UpdatePath(ArrayDeque<Point> ad) {
        _ad = ad;
    }
    /**
     *判断怪物是否活着
     */
    public boolean IsAlive(){
        if(_hp<=0){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 绘画怪样子的方法，由子类完成
     * @param g
     */
    public void draw(Graphics g){ }
}

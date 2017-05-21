package Model.Map;

import Model.BaseClass.Location;
import Model.BaseClass.Monster;
import Model.BaseClass.Tower;

/**
 * Created by MFunction on 2017/4/19.
 * 地图块类
 *
 * @author
 */
public class Block {
    /**
     * 表现层位置
     */
    protected Location _surlocation;
    /**
     * 业务层位置
     */
    protected Location _optlocation;
    /**
     * 通行状态
     */
    protected boolean _canpass;
    /**
     * 路径状态
     */
    protected boolean _ispath;
    /**
     * 可攻击的塔
     */
    protected Tower[] _towers = new Tower[6];
    /**
     * 区块本身是否放塔
     */
    protected Tower _tower;

    /**
     * 构造块对象
     *
     * @param surfaceLocation   UI界面的位置
     * @param operationLocation 后台操作的位置
     * @param pass              判断是否能通过
     * @param isPath            判断是否能做为路径
     */
    public Block(Location surfaceLocation, Location operationLocation, boolean pass, boolean isPath) {
        _surlocation = surfaceLocation;
        _optlocation = operationLocation;
        _canpass = pass;
        _ispath = isPath;
    }

    /**
     * 获取模块的UI界面位置
     *
     * @return 返回模块的界面位置
     */
    public final Location GetSurfaceLocation() {
        return _surlocation;
    }

    /**
     * 获取模块的后台操作位置
     *
     * @return 返回模块的后台操作位置
     */
    public final Location GetOperationLocation() {
        return _optlocation;
    }

    /**
     * 判断模块是否能够让塔通过
     *
     * @return 返回塔是否能让塔通过
     */
    public final boolean CanPass() {
        return _canpass;
    }

    /**
     * 判断模块是否是塔的路径
     *
     * @return 返回模块是否为路径
     */
    public final boolean IsPath() {
        return _ispath;
    }

    /**
     * 攻击模块上的怪
     *
     * @param monster 被攻击的怪
     */
    public void Attack(Monster monster) {
        int damage = 0;
        for (int i = 0; i < _towers.length; i++) {
            damage += _towers[i].GetDamage();
        }
        monster.Hurt(damage);
    }

    /**
     * 更新
     *
     * @param isPath 更新的路径
     */
    public void Update(boolean isPath) {
        _ispath = isPath;
    }

    /**
     * 在模块上安装塔
     *
     * @param tw 该模块上安装的塔
     */
    public void AddTower(Tower tw) {
        _canpass = false;
        _ispath = false;
        _tower = tw;
        _tower.SetTower(_surlocation,_optlocation);

    }

    /**
     * 摧毁当前模块的塔
     */
    public void DestroyTower() {
        _canpass = true;
        _tower = null;
    }

    /**
     * 判断模块上是否有塔
     *
     * @return 返回模块上是否有塔
     */
    public boolean HasTower() {
        return _tower == null;
    }
}

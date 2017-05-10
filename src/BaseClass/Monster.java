package BaseClass;

/**
 * Created by MFunction on 2017/4/17.
 * This class is about Monster.
 */
abstract public class Monster {
    protected int _life;
    protected int _speed;
    protected int _price;
    protected String _id;
    protected String _name;
    protected Location _surlocation;
    protected Location _optlocation;
    protected int _upvalue;
    protected int _upprice;

    /**
     * 构造基础怪
     * @param name 怪的名字
     * @param id 怪的ID
     * @param life 怪的生命值
     * @param speed 怪的速度
     * @param price 怪的价值
     * @param upgradeLife 怪的血量升级
     * @param upgradePrice 怪的价值升级
     * @param surfaceLocation 怪的UI位置
     * @param operatorLocation 怪的后台操作位置
     */
    public Monster(String name,String id,int life,int speed,int price,int upgradeLife,int upgradePrice,Location surfaceLocation,Location operatorLocation){
        _name=name;
        _id=id;
        _speed=speed;
        _price=price;
        _surlocation=surfaceLocation;
        _optlocation=operatorLocation;
        _upprice=upgradePrice;
        _upvalue=upgradeLife;
        _life=life;
    }

    /**
     * 获取怪的名称
     * @return 返回怪的名称
     */
    public final String GetName(){
        return _name;
    }

    /**
     * 获取怪的ID
     * @return 返回怪的ID
     */
    public final String GetId(){
        return _id;
    }

    /**
     * 获取怪的生命值
     * @return 返回怪的生命值
     */
    public final int GetLife(){
        return _life;
    }

    /**
     * 获取怪的速度
     * @return 返回怪的速度
     */
    public final int GetSpeed(){
        return _speed;
    }

    /**
     * 获取怪的价值
     * @return 返回怪的价值
     */
    public final int GetPrice(){
        return _price;
    }

    /**
     * 获取怪升级血量
     * @return 返回怪增长的血量
     */
    public final int GetUpgradeLife(){
        return _upvalue;
    }

    /**
     * 获取怪升级的价格
     * @return 返回怪升级的价格
     */
    public final int GetUpgradePrice(){
        return _upprice;
    }

    /**
     * 获取怪在UI界面的位置
     * @return 返回怪在UI界面的位置
     */
    public final Location GetSurfaceLocation(){
        return _surlocation;
    }

    /**
     * 获取怪在后台操作的位置
     * @return 返回怪在后台操作的位置
     */
    public final Location GetOperationLocation(){
        return _optlocation;
    }

    /**
     * 怪在UI上的移动
     */
    public void SurfaceMove(){
    }

    /**
     * 怪在后台操作的移动
     */
    public void OperationMove(){
    }

    /**
     * 对怪造成伤害
     * @param damage 对怪的伤害值
     */
    public void Hurt(int damage){
        _life-=damage;
    }

    /**
     * 对怪进行升级,提高难度
     */
    public void Upgrade(){
        _price+=_upprice;
        _life+=_upvalue;
    }
}

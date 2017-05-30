package Controller.Thread;

import Model.BaseClass.Tower;

/**
 * Created by MFunction on 2017/5/29.
 * 塔控制器
 *
 * @author MFunction
 */
public class TowerController extends Thread {
    /**
     * 游戏控制器
     */
    private GameController _gc;
    /**
     * 坐标
     */
    private Tower _tw;

    /**
     * 塔控制器构造函数
     * @param gc 游戏控制器
     * @param tw 放置的塔
     */
    TowerController(GameController gc, Tower tw) {
        _gc = gc;
        _tw = tw;
    }

}

package Controller;

import Frame.Map;

/**
 * Created by MFunction on 2017/4/17.
 * @author MFunction
 */
public class GameController extends Thread {
    private Map m = new Map("Config.xml");
    private int _round;

    /**
     * 获取当前回合
     * @return 返回当前回合数
     */
    public int round() {
        return _round;
    }

    /**
     *
     */
    public void Win(){

    }

    /**
     *
     */
    public void Pause(){

    }

    /**
     *
     */
    public void Lose(){

    }

    public void run(){

    }
}

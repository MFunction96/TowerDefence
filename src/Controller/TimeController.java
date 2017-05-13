package Controller;

/**
 * Created by MFunction on 2017/4/17.
 * 时间驱动类
 */
public class TimeController extends Thread {
    /**
     *
     */
    private int _stime;

    /**
     *
     * @param time 时间间隔
     */
    public void TimeController(int time) {
        _stime = time;
    }

    public void run() {

    }
}

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
    private int _cnt;
    private Object _obj;

    /**
     * @param time 时间间隔
     */
    public TimeController(int time, int cnt, Object obj) {
        _stime = time;
        _obj = obj;
        _cnt = cnt;
    }

    public void run() {
        try {
            for (int i = 0; i < _cnt; i++) {

                Thread.sleep(_stime);
            }
        } catch (InterruptedException e) {
            System.err.print(e);
        }
    }
}

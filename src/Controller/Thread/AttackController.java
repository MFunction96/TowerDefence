package Controller.Thread;

import Model.BaseClass.Monster;

/**
 * Created by MFunction on 2017/5/20.
 * 伤害控制器线程类，计算怪物伤害
 * @author MFunction
 */
public class AttackController extends Thread{
    private Monster _monster;
    private static int _stime = 1000;
    AttackController(Monster monster){
        _monster=monster;
    }

    public void run(){

    }
}

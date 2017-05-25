package Controller.Thread;

import Model.BaseClass.Monster;
import Model.Framework.Map;


import java.util.LinkedList;

/**
 * Created by Chris Young on 2017/5/25.
 */
public class MonsterMoveController implements Runnable {
    LinkedList<Monster> _monlist ;
    GameController _gc;
    Map _map;

    public MonsterMoveController(Map map) {
        _map = map;
        _gc = new GameController(map);
        _monlist = _gc._monsters;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < _monlist.size(); i++) {
                    Monster monster = _monlist.get(i);
                    if (monster == null)
                        break;
                    else if(monster.IsAlive()==true){
                        monster.SurfaceMove();
                        monster.OptMove();
                    }
                    else if(monster.IsAlive()==false){
                        _map.SetMoney(_map.money()+monster.GetPrice());
                        _monlist.remove(i);
                        i--;
                    }
                }
                Thread.sleep(640);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

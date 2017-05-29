package Controller.Thread;

import Model.BaseClass.Monster;
import Model.BaseClass.Tower;
import Model.Framework.Map;
import Model.Map.Block;

public class TowerController extends Thread {
    private  Tower _tower;
    private Map _map;
    private Monster _monster[]=new Monster[_map.monster().length];
    TowerController(Tower tower,Map map){
        _tower=tower;
        _map=map;
    }
    @Override
    public void run() {
        super.run();
        Block gameBlock=_map.block(_tower.GetOperationLocation());
        for(int i=_tower.GetOperationLocation().x()-_tower.GetAttackArea()/2;i<=_tower.GetAttackArea();i++){
            for(int j=_tower.GetOperationLocation().y()-_tower.GetAttackArea()/2;j<=_tower.GetAttackArea();j++){

            }
        }
        if( _tower.GetCanAttack()==true){
        }

    }
}

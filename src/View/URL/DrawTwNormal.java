package View.URL;

import javax.swing.*;
import java.awt.*;
import Model.BaseClass.Point;
import  Model.* ;
import Model.BaseClass.Tower;
/**
 * Created by hlys on 2017/5/25.
 */
/*
*@author
 */

public class DrawTwNormal extends JFrame  {

    public void drawTw(String name,Tower tower,Point towerLocation ){
        if(name.equals("炮塔") ){
            JLabel _TwNormal ;
            tower.SetTower(towerLocation,towerLocation);
            _TwNormal =new JLabel(new ImageIcon("src/Image/TwNormal.png") ) ;
            _TwNormal .setBounds(towerLocation.x(),towerLocation.y(),64,64);
            _TwNormal .setOpaque(false );//设置背景透明
            this.getContentPane() .add(_TwNormal );
        }

    }


}

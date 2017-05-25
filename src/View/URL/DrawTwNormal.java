package View.URL;

import javax.swing.*;
import java.awt.*;
import  Model.* ;
import Model.BaseClass.Tower;
/**
 * Created by hlys on 2017/5/25.
 */
/*
*@author
 */

public class DrawTwNormal extends JFrame  {

    public void drawTw(String name,Tower tower ){
        if(name.equals("炮塔") ){
            JLabel _TwNormal ;

            _TwNormal =new JLabel(new ImageIcon("src/Image/TwNormal.png") ) ;
            _TwNormal .setBounds(900,300,64,64);
            _TwNormal .setOpaque(false );//设置背景透明
            this.getContentPane() .add(_TwNormal );
        }

    }


}

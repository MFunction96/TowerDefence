package Model.Tower;

import Model.BaseClass.Tower;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MFunction on 2017/4/19.
 */
public  class TwNormal extends Tower {

    ImageIcon icon;
    Image img;

    public TwNormal(Graphics g){
        super("炮塔",1,2,5,1,1,3 );
        icon=new ImageIcon("src/image/TwNormal.png");
        img=icon.getImage();
        g.drawImage(img,963,320,64,64,null );

    }



}

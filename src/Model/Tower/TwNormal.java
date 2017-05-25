package Model.Tower;

import Model.BaseClass.Tower;

import javax.swing.*;
import java.awt.*;

/**
 * Created by MFunction on 2017/4/19.
 */
public class TwNormal extends Tower {
    ImageIcon icon;
    Image img;

    public TwNormal(){
        super("炮塔",1,2,5,1,1,3 );
        icon=new ImageIcon("src/image/tower.png");
        img=icon.getImage();
    }
    public void DrawOutLook(Graphics g){
        g.drawImage(img,0,0,null );
    }


}

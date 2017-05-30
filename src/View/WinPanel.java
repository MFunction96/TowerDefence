package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Created by hlys on 2017/5/29.
 */
public class WinPanel extends JPanel {

    //设置主页背景图片的JPnel类

    ImageIcon icon;
    Image img;

    public WinPanel() {
        //  /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片
        icon = new ImageIcon(getClass().getResource("src/Image/YOUWIN.png"));
        img = icon.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(img, 0, 0, 1024, 838, this);
    }

}

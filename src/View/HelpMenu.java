package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Chris Young on 2017/5/23.
 */
public class HelpMenu extends JFrame implements ActionListener {
    JButton _return;
    Toolkit _tk;
    JLabel _title;


    HelpMenu() {
        this.setTitle("0度塔防—帮助");
        this.setVisible(true);
        this.setSize(1024, 838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        _tk = Toolkit.getDefaultToolkit();
        Image img = _tk.createImage("src/Image/logo.png");
        this.setIconImage(img);    //修改窗体默认图标
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        /*
        设置界面背景
         */
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("src/Image/Map.png");
        label.setIcon(icon);      //将图片填充到Label中
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); //设置Label位置，以背景图像的宽和高设置label大小
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  //将Label放在LayerP

        _title = new JLabel("帮助");
        _title.setBounds(100, 50, 100, 50);
        _title.setFont(new Font("宋体", Font.BOLD, 40));
        _title.setForeground(Color.WHITE);
        _title.setVisible(true);
        this.getContentPane().add(_title);


        _return = new JButton(new ImageIcon("src/Image/return.png"));
        _return.addActionListener(this);
        _return.setBounds(680, 580, 286, 208);
        _return.setBorderPainted(false);
        _return.setContentAreaFilled(false);
        this.getContentPane().add(_return);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _return) {
            this.dispose();
            new MainMenu();
        }
    }
}

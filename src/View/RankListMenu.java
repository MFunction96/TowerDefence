package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Chris Young on 2017/5/23.
 * 显示排行榜
 */
public class RankListMenu extends JFrame implements ActionListener {
    private JButton _return;

    RankListMenu() {
        this.setTitle("0度塔防—排行榜");
        this.setVisible(true);
        this.setSize(1024, 838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Toolkit _tk = Toolkit.getDefaultToolkit();
        Image img = _tk.createImage("src/Image/logo.png");
        this.setIconImage(img);    //修改窗体默认图标
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);

        /*
        设置界面背景
         */
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("src/Image/Map.png");
        label.setIcon(icon);      //将图片填充到Label中
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); //设置Label位置，以背景图像的宽和高设置label大小
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  //将Label放在LayerP

        JLabel _title = new JLabel("排行榜");
        _title.setBounds(100, 50, 200, 50);
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

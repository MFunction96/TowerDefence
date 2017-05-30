package View;

import Model.Audio.MenuMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Chris Young on 2017/5/23.
 */
public class SetMenu extends JFrame implements ActionListener, ItemListener {
    JButton _return;
    Toolkit _tk;
    JLabel _title;
    JCheckBox _audio;
    public static boolean _isopenmusic = true;

    SetMenu() {
        this.setTitle("0度塔防—游戏设置");
        this.setVisible(true);
        this.setSize(1024, 838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        _tk = Toolkit.getDefaultToolkit();
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
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  //将Label放在LayerPane层中

        _title = new JLabel("设置");
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

        if (_isopenmusic == false) {
            _audio = new JCheckBox("开启音效", new ImageIcon("src/Image/Audio.png"), false);
        } else {
            _audio = new JCheckBox("关闭音效", new ImageIcon("src/Image/Audio.png"), true);
        }
        _audio.setBounds(460, 260, 200, 160);
        _audio.setOpaque(false);
        _audio.setFont(new Font("宋体", Font.BOLD, 30));
        _audio.setForeground(Color.WHITE);
        _audio.addItemListener(this);
        this.getContentPane().add(_audio);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _return) {
            this.dispose();
            new MainMenu();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (_audio.isSelected() == true) {
            _isopenmusic = true;
            _audio.setText("关闭音效");
            MainMenu.music = new MenuMusic();
            MainMenu.music.start();
        } else {
            _isopenmusic = false;
            _audio.setText("开启音效");
            MainMenu.music.MusicSetting(false);
        }
    }
}

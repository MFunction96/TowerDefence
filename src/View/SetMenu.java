package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Chris Young on 2017/5/23.
 */
public class SetMenu extends JFrame implements ActionListener {
    JButton _return;
    Toolkit _tk;

    SetMenu(){
        this.setVisible(true);
        this.setSize(1024,838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        _tk= Toolkit.getDefaultToolkit();
        Image img= _tk.createImage("src/Image/logo.png");
        this.setIconImage(img);    //修改窗体默认图标
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        _return=new JButton("返回");
        _return.addActionListener(this);
        _return.setBounds(200,200,200,100);
        this.add(_return);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _return) {
            this.dispose();
            new MainMenu();
        }
    }
}

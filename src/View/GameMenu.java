package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Chris Young on 2017/5/22.
 */
public class GameMenu extends JFrame implements ActionListener {
    JButton _return;

    GameMenu() {
        this.setVisible(true);
        this.setSize(1024,838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        _return = new JButton("返回");
        _return.addActionListener(this);
        _return.setBounds(200,200,200,100);
        this.getContentPane().add(_return);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _return) {
            this.dispose();
            new MainMenu();
        }
    }
}

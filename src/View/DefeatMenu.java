package View;

import Model.Audio.FailMusic;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/**
 * Created by hlys on 2017/5/29.
 * 失败界面类，显示玩家失败，并提供返回主菜单按钮
 *
 * @author hlys
 */
public class DefeatMenu extends JFrame implements ActionListener{
    /**
     * 窗体宽
     */
    private int w;
    /**
     * 窗体长
     */
    private int h;
    private GameMenu _gm;
    private FailMusic failMusic=new FailMusic();

    private JButton _BackWhenDefeat ;
    public DefeatMenu(GameMenu gm){
        super("0度塔防");//设置标题
        this.setVisible(true);//设置为可见
        this.setSize(512, 419);//设置窗体大小
        this.setLayout(null);//设置为空布局
        this.setLocationRelativeTo(null);//设置为集中显示
        this.setResizable(false);//设置不可改变窗体大小
        Toolkit _tk = Toolkit.getDefaultToolkit();
        Image img = _tk.createImage("src/Image/logo.png");
        this.setIconImage(img);    //修改窗体默认图标
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置窗体关闭
        _gm=gm;

        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("src/Image/YOUDEFEAT.png");
        label.setIcon(icon);      //将图片填充到Label中
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        this.SetBackgroundMusic();




        _BackWhenDefeat = new JButton(new ImageIcon("src/Image/BackToMainMenu.png") );
        _BackWhenDefeat .setBounds(200, 256, 108, 30);
        _BackWhenDefeat .addActionListener(this);
        _BackWhenDefeat .setBorderPainted(false);
        this.getContentPane().add(_BackWhenDefeat );

    }

    private void SetBackgroundMusic(){
        if(SetMenu._isopenmusic){
            failMusic.start();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==_BackWhenDefeat  ){
            this.dispose() ;
            _gm.dispose();
            MainMenu aa= new MainMenu() ;
            aa.setVisible(true) ;
        }
    }


}

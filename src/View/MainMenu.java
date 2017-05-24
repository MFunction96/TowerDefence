package View;

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

/**
 * Created by Chris Young on 2017/5/22.
 */
public class MainMenu extends JFrame implements ActionListener{
    JButton _startBtn;
    JButton _setBtn;
    JButton _continueBtn;
    JButton _helpBtn;
    JButton _communiteeBtn;
    JButton _ranklistBtn;


    MainMenu(){
        super("0度塔防");
        this.setVisible(true);
        this.setSize(1024,838);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        JPanel imagePanel=(JPanel)this.getContentPane();
        imagePanel.setOpaque(false);

        /*
        设置界面背景
         */
        JLabel label=new JLabel();
        ImageIcon icon=new ImageIcon("src/Image/Mainmenu.png");
        label.setIcon(icon);      //将图片填充到Label中
        label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight()); //设置Label位置，以背景图像的宽和高设置label大小
        /*
        int w=this.getLayeredPane().getWidth();
        int h=this.getLayeredPane().getHeight();
        Image img=icon.getImage().getScaledInstance(w,h,Image.SCALE_FAST);
        label.setIcon(new ImageIcon(img));
        label.setBounds(0,0,w,h);
        */
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));  //将Label放在LayerPane层中

        /*
        设置按钮的位置，大小，图标，以及注册监听器，将按钮添加到ContentPane层中
         */
        _startBtn=new JButton(new ImageIcon("src/Image/start.jpg")); //设置按钮背景
       _startBtn.setBounds(104,428,296,130);  //设置按钮位置，大小
        _startBtn.addActionListener(this);  //注册监听器
        _startBtn.setBorderPainted(false);
        this.getContentPane().add(_startBtn); //将按钮添加到ContentPane层中

        _continueBtn=new JButton(new ImageIcon("src/Image/continue.jpg"));
        _continueBtn.setBounds(423,421,296,130);
        _continueBtn.setBorderPainted(false);
        _continueBtn.addActionListener(this);
        this.getContentPane().add(_continueBtn);

        _setBtn=new JButton(new ImageIcon("src/Image/set.png"));
        _setBtn.setBounds(721,440,296,130);
        _setBtn.setBorderPainted(false);
        _setBtn.addActionListener(this);
        this.getContentPane().add(_setBtn);

        _helpBtn=new JButton(new ImageIcon("src/Image/help.jpg"));
        _helpBtn.setBounds(402,635,296,130);
        _helpBtn.setBorderPainted(false);
        _helpBtn.addActionListener(this);
        this.getContentPane().add(_helpBtn);

        _ranklistBtn=new JButton(new ImageIcon("src/Image/ranklist.jpg"));
        _ranklistBtn.setBounds(51,630,296,130);
        _ranklistBtn.setBorderPainted(false);
        _ranklistBtn.addActionListener(this);
        this.getContentPane().add(_ranklistBtn);

        _communiteeBtn=new JButton(new ImageIcon("src/Image/community.jpg"));
        _communiteeBtn.setBounds(701,644,296,130);
        _communiteeBtn.setBorderPainted(false);
        _communiteeBtn.addActionListener(this);
        this.add(_communiteeBtn);

    }

    /**
     * 按钮事件响应，实现界面跳转
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==_startBtn){
            this.dispose(); //隐藏当前窗口
            new GameMenu();
        }
        else if(e.getSource()==_continueBtn){

        }
        else if(e.getSource()==_setBtn){
            this.dispose();
            new SetMenu();
        }
        else if(e.getSource()==_helpBtn){
            this.dispose();
            new HelpMenu();
        }
        else if(e.getSource()==_ranklistBtn){
            this.dispose();
            new RankListMenu();
        }
        else if(e.getSource()==_communiteeBtn){
            this.dispose();
            new CommuniteeMenu();
        }

    }

    public static void main(String args[]){
        new MainMenu();
    }
}



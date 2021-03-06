package View;

import Controller.Thread.GameController;
import Model.Audio.MenuMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

/**
 * Created by Chris Young on 2017/5/22.
 */
public class MainMenu extends JFrame implements ActionListener {
    /**
     * 开始游戏按钮
     */
    private JButton _startBtn;
    /**
     * 设置游戏按钮
     */
    private JButton _setBtn;
    /**
     * 继续游戏按钮
     */
    private JButton _continueBtn;
    /**
     * 游戏帮助按钮
     */
    private JButton _helpBtn;
    /**
     * 游戏社区按钮
     */
    private JButton _communiteeBtn;
    /**
     * 游戏排行榜按钮
     */
    private JButton _ranklistBtn;
    /**
     * 背景音乐
     */
    public static MenuMusic music = new MenuMusic();

    private GameMenu _gm;

    /**
     * 主菜单构造函数
     */
    public MainMenu() {
        //设置窗体
        super("0度塔防");    //设置标题
        this.setVisible(true);     //设置为可见
        this.setSize(1024, 838);   //设置窗体大小
        this.setLayout(null);     //设置为空布局
        this.setLocationRelativeTo(null);  //设置为在屏幕居中显示
        this.setResizable(false);   //设置为不可更改窗体大小
        /*
      绘制图片工具
     */
        Toolkit _tk = Toolkit.getDefaultToolkit();
        Image img = _tk.createImage("src/Image/logo.png");
        this.setIconImage(img);    //修改窗体默认图标
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置窗体关闭
       /*
        设置界面背景
         */
        JPanel imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("src/Image/background.png");
        label.setIcon(icon);      //将图片填充到Label中
        label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); //设置Label位置，以背景图像的宽和高设置label大小
        /*
        预留根据窗口大小填充背景方法
        int w=this.getLayeredPane().getWidth();
        int h=this.getLayeredPane().getHeight();
        Image img=icon.getImage().getScaledInstance(w,h,Image.SCALE_FAST);
        label.setIcon(new ImageIcon(img));
        label.setBounds(0,0,w,h);
        */
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));  //将Label放在LayerPane层中

        /*
        设置按钮的位置，大小，图标，以及注册监听器，将按钮添加到ContentPane层中
         */
        _startBtn = new JButton(new ImageIcon("src/Image/start.jpg")); //设置按钮背景
        _startBtn.setBounds(104, 428, 296, 130);  //设置按钮位置，大小
        _startBtn.addActionListener(this);  //注册监听器
        _startBtn.setBorderPainted(false);    //隐藏按钮边界
        this.getContentPane().add(_startBtn); //将按钮添加到ContentPane层中
        //下同
        _continueBtn = new JButton(new ImageIcon("src/Image/continue.jpg"));
        _continueBtn.setBounds(423, 421, 296, 130);
        _continueBtn.setBorderPainted(false);
        _continueBtn.addActionListener(this);
        this.getContentPane().add(_continueBtn);

        _setBtn = new JButton(new ImageIcon("src/Image/set.png"));
        _setBtn.setBounds(721, 440, 296, 130);
        _setBtn.setBorderPainted(false);
        _setBtn.addActionListener(this);
        this.getContentPane().add(_setBtn);

        _helpBtn = new JButton(new ImageIcon("src/Image/help.jpg"));
        _helpBtn.setBounds(402, 635, 296, 130);
        _helpBtn.setBorderPainted(false);
        _helpBtn.addActionListener(this);
        this.getContentPane().add(_helpBtn);

        _ranklistBtn = new JButton(new ImageIcon("src/Image/ranklist.jpg"));
        _ranklistBtn.setBounds(51, 630, 296, 130);
        _ranklistBtn.setBorderPainted(false);
        _ranklistBtn.addActionListener(this);
        this.getContentPane().add(_ranklistBtn);

        _communiteeBtn = new JButton(new ImageIcon("src/Image/community.jpg"));
        _communiteeBtn.setBounds(701, 644, 296, 130);
        _communiteeBtn.setBorderPainted(false);
        _communiteeBtn.addActionListener(this);
        this.getContentPane().add(_communiteeBtn);

        //播放背景音乐
        if (!music.isAlive()) {
            music.start();
        }


    }
    public void SetGameMenu(GameMenu gm){
        _gm=gm;
    }

    /**
     * 按钮事件响应，实现界面跳转
     */
    public  void actionPerformed(ActionEvent e) {
        if (e.getSource() == _startBtn) {   //如果事件源为_startBtn按钮
            this.dispose(); //隐藏当前窗口
           new GameMenu();
        } else if (e.getSource() == _continueBtn) {
            this.dispose();
            _gm.setVisible(true);
            _gm._gc.notifyAll();
            _gm.notifyAll();

        } else if (e.getSource() == _setBtn) {
            this.dispose();
            new SetMenu();
        } else if (e.getSource() == _helpBtn) {
            this.dispose();
            new HelpMenu();
        } else if (e.getSource() == _ranklistBtn) {
            this.dispose();
            new RankListMenu();
        } else if (e.getSource() == _communiteeBtn) {
            this.dispose();
            new CommuniteeMenu();
        }

    }

    public static void main(String args[]) {
        new MainMenu();
    }
}



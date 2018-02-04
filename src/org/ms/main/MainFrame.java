package org.ms.main;

import org.ms.db.DataSource;
import org.ms.util.Global;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    JPanel contentPane;

    JLabel ipLabel = new JLabel("IP", JLabel.RIGHT);
    JTextField ipTextField = new JTextField();
    JLabel portLabel = new JLabel("端口", JLabel.RIGHT);
    JTextField portTextField = new JTextField("1521");
    JLabel sidLabel = new JLabel("SID", JLabel.RIGHT);
    JTextField sidTextField = new JTextField();
    JLabel userNameLabel = new JLabel("用户名", JLabel.RIGHT);
    JTextField userNameTextField = new JTextField();
    JLabel pwdLabel = new JLabel("密码", JLabel.RIGHT);
    JPasswordField pwdField = new JPasswordField();
    JButton pingBtn = new JButton("测试");
    JButton saveBtn = new JButton("保存");

    public MainFrame(){
        this.contentPane = (JPanel)getContentPane();
        this.contentPane.setMinimumSize(new Dimension(600, 450));
        this.contentPane.setPreferredSize(new Dimension(600, 450));
        this.contentPane.setBorder(new TitledBorder("contentPane"));
        setContentPane(this.contentPane);
        // setResizable(false);
        setSize(new Dimension(600, 450));
        setState(0);  //0时为正常大小，1时为最小化
        setTitle("byeMess - A Simple Tool For Developer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();
        JMenu menu = new JMenu("Test");
        mb.add(menu);
        setJMenuBar(mb);

        //设置布局格式
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        //页面组件
        add(ipLabel);
        add(ipTextField);
        add(portLabel);
        add(portTextField);
        add(sidLabel);
        add(sidTextField);
        add(userNameLabel);
        add(userNameTextField);
        add(pwdLabel);
        add(pwdField);
        add(pingBtn);
        add(saveBtn);

        //定义一个GridBagConstraints，是用来控制添加进的组件的显示位置
        GridBagConstraints gbc= new GridBagConstraints();
        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        gbc.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        gbc.weighty = 0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(ipLabel, gbc);
        gbc.gridwidth = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(ipTextField, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(portLabel, gbc);
        gbc.gridwidth = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(portTextField, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(sidLabel, gbc);
        gbc.gridwidth = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(sidTextField, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(userNameLabel, gbc);
        gbc.gridwidth = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(userNameTextField, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(pwdLabel, gbc);
        gbc.gridwidth = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(pwdField, gbc);
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(pingBtn, gbc);
        gbc.gridwidth = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(saveBtn, gbc);

        //给按钮添加事件
        pingBtn.addActionListener(new PingListener());
        saveBtn.addActionListener(new SaveDbConnListener());
    }

    class PingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DataSource ds = new DataSource();
            if(ds.isConnSuccess()){
                JOptionPane.showMessageDialog(null, "连接成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "连接失败！", "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public class SaveDbConnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String ip = ipTextField.getText();
            String port = portTextField.getText();
            String sid = sidTextField.getText();
            String userName = userNameTextField.getText();
            String pwd = String.valueOf(pwdField.getPassword());

            String url = Global.DB_ORACLE_URL_PREFIX + ip + Global.SYMBOL_COLON + port + Global.SYMBOL_COLON + sid;
            //该写配置文件了，但是要不要写呢？
        }
    }

}

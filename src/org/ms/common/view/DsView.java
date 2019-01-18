package org.ms.common.view;

import org.ms.common.AbstractViewPanel;
import org.ms.common.db.DataSource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author wsy
 * @date 2018-02-15
 */
public class DsView extends AbstractViewPanel {

    /* 组件 */
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

    public DsView() {

        //设置布局格式
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);

        //页面组件
        contentPane.add(ipLabel);
        contentPane.add(ipTextField);
        contentPane.add(portLabel);
        contentPane.add(portTextField);
        contentPane.add(sidLabel);
        contentPane.add(sidTextField);
        contentPane.add(userNameLabel);
        contentPane.add(userNameTextField);
        contentPane.add(pwdLabel);
        contentPane.add(pwdField);
        contentPane.add(pingBtn);
        contentPane.add(saveBtn);



        /*
        gridx = 2; // X2
        gridy = 0; // Y0
        gridwidth = 1; // 横占一个单元格
        gridheight = 1; // 列占一个单元格
        weightx = 0.0; // 当窗口放大时，长度不变
        weighty = 0.0; // 当窗口放大时，高度不变
        anchor = GridBagConstraints.NORTH; // 当组件没有空间大时，使组件处在北部
        fill = GridBagConstraints.BOTH; // 当格子有剩余空间时，填充空间
        insert = new Insets(0, 0, 0, 0); // 组件彼此的间距
        ipadx = 0; // 组件内部填充空间，即给组件的最小宽度添加多大的空间
        ipady = 0; // 组件内部填充空间，即给组件的最小高度添加多大的空间
        new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insert, ipadx, ipady);
        */


        //定义一个GridBagConstraints，是用来控制添加进的组件的显示位置
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        gbc.gridheight = 1;
        gbc.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        gbc.weighty = 0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(ipLabel, gbc);
        gbc.gridwidth = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(ipTextField, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(portLabel, gbc);
        gbc.gridwidth = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(portTextField, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(sidLabel, gbc);
        gbc.gridwidth = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(sidTextField, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(userNameLabel, gbc);
        gbc.gridwidth = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(userNameTextField, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(pwdLabel, gbc);
        gbc.gridwidth = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(pwdField, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(pingBtn, gbc);
        gbc.gridwidth = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        layout.setConstraints(saveBtn, gbc);

        //给按钮添加事件
        pingBtn.addActionListener(new PingListener());
        saveBtn.addActionListener(new SaveDbConnListener());

    }


    @Override
    public JPanel getViewPane() {
        return contentPane;
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
            //确认对话框
            int option = JOptionPane.showConfirmDialog(null, "确定保存修改吗？", "提示",JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.NO_OPTION){
                return;
            }

            JOptionPane.showMessageDialog(null, "暂不支持修改！", "提示", JOptionPane.INFORMATION_MESSAGE);

            //以后增加校验
            // String ip = ipTextField.getText();
            // String port = portTextField.getText();
            // String sid = sidTextField.getText();
            // String userName = userNameTextField.getText();
            // String pwd = String.valueOf(pwdField.getPassword());
            //
            // String url = Global.DB_ORACLE_URL_PREFIX + ip + Global.SYMBOL_COLON + port + Global.SYMBOL_COLON + sid;

            //该写配置文件了，但是要不要写呢？

        }
    }

}

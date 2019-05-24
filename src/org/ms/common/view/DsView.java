package org.ms.common.view;

import org.ms.common.AbstractViewPanel;
import org.ms.common.util.Global;
import org.ms.common.util.JdbcUtil;
import org.ms.common.util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author wsy
 * @date 2018-02-15
 */
public class DsView extends AbstractViewPanel {

    /**
     * 组件
     */
    JLabel ipLabel = new JLabel("IP", JLabel.RIGHT);
    JTextField ipTextField = new JTextField();
    JLabel portLabel = new JLabel("端口", JLabel.RIGHT);
    JTextField portTextField = new JTextField();
    JLabel sidLabel = new JLabel("SID", JLabel.RIGHT);
    JTextField sidTextField = new JTextField();
    JLabel userNameLabel = new JLabel("用户名", JLabel.RIGHT);
    JTextField userNameTextField = new JTextField();
    JLabel pwdLabel = new JLabel("密码", JLabel.RIGHT);
    JPasswordField pwdField = new JPasswordField();
    JButton pingBtn = new JButton("测试");
    JButton saveBtn = new JButton("保存");

    /**
     * 构造方法
     */
    public DsView() {

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

        //设置页面布局
        setPaneLayout();

        //展示数据库连接配置信息
        showDbConnInfo();

        //给按钮添加事件
        pingBtn.addActionListener(new PingListener());
        saveBtn.addActionListener(new SaveDbConnListener());

    }

    /**
     * 设置页面布局
     */
    private void setPaneLayout(){
        //设置布局格式
        GridBagLayout layout = new GridBagLayout();
        contentPane.setLayout(layout);

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
        /*
        gbc.fill = GridBagConstraints.BOTH;
        该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        NONE：不调整组件大小。
        HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        BOTH：使组件完全填满其显示区域。
        */
        /*
        gbc.gridwidth = 1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        gbc.gridheight = 1;
        gbc.weightx = 0.3;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        gbc.weighty = 0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        */


        //定义一个GridBagConstraints，是用来控制添加进的组件的显示位置
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        //IP
        gbc.gridwidth = 1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        gbc.gridheight = 1;
        gbc.weightx = 0.3;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        gbc.weighty = 0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(ipLabel, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        layout.setConstraints(ipTextField, gbc);

        //端口
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(portLabel, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        layout.setConstraints(portTextField, gbc);

        //SID
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(sidLabel, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        layout.setConstraints(sidTextField, gbc);

        //用户名
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(userNameLabel, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        layout.setConstraints(userNameTextField, gbc);

        //密码
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(pwdLabel, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        layout.setConstraints(pwdField, gbc);

        //测试 & 确定
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        layout.setConstraints(pingBtn, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        layout.setConstraints(saveBtn, gbc);
    }

    /**
     * 获取显示面板对象
     */
    @Override
    public JPanel getViewPane() {
        return contentPane;
    }

    /**
     * ping按钮监听事件
     */
    public class PingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // DataSource ds = new DataSource();
            // if(ds.isConnSuccess()){
            //     JOptionPane.showMessageDialog(null, "连接成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
            // }else{
            //     JOptionPane.showMessageDialog(null, "连接失败！", "提示", JOptionPane.ERROR_MESSAGE);
            // }

            //校验输入内容
            if(!validateInput()){
                return;
            }

            Connection connection = null;
            String exMsg = "";
            try {
                String ip = ipTextField.getText();
                String port = portTextField.getText();
                String sid = sidTextField.getText();
                String userName = userNameTextField.getText();
                String pwd = String.valueOf(pwdField.getPassword());

                // jdbc:oracle:thin:@127.0.0.1:1521:orcl
                String url = Global.DB_ORACLE_URL_PREFIX + ip + Global.SYMBOL_COLON + port + Global.SYMBOL_COLON + sid;
                connection = JdbcUtil.getConnection(url, userName, pwd);
            } catch (SQLException e1) {
                e1.printStackTrace();
                exMsg = e1.getMessage();
            } catch (Exception e2) {
                e2.printStackTrace();
                exMsg = e2.getMessage();
            }

            if(connection != null){
                JOptionPane.showMessageDialog(null, "连接成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                //释放连接
                JdbcUtil.free(null, null, connection);
            }else{
                JOptionPane.showMessageDialog(null, "连接失败！" + exMsg, "提示", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * 保存按钮监听事件
     */
    public class SaveDbConnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //确认对话框
            int option = JOptionPane.showConfirmDialog(null, "确定保存修改吗？", "提示",JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.NO_OPTION){
                return;
            }

            // JOptionPane.showMessageDialog(null, "暂不支持修改！", "提示", JOptionPane.INFORMATION_MESSAGE);

            //校验输入内容
            if(!validateInput()){
                return;
            }

            String ip = ipTextField.getText();
            String port = portTextField.getText();
            String sid = sidTextField.getText();
            String userName = userNameTextField.getText();
            String pwd = String.valueOf(pwdField.getPassword());

            String url = Global.DB_ORACLE_URL_PREFIX + ip + Global.SYMBOL_COLON + port + Global.SYMBOL_COLON + sid;
            
            //设置内容
            Global.setDbProp(Global.DB_ORACLE_PROP_KEY_URL, url);
            Global.setDbProp(Global.DB_ORACLE_PROP_KEY_USERNAME, userName);
            Global.setDbProp(Global.DB_ORACLE_PROP_KEY_PASSWORD, pwd);

            //写入
            try{
                Global.writeDbProperties();
                JOptionPane.showMessageDialog(null, "保存成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception e2){
                e2.printStackTrace();
                String msg = "保存失败！失败原因：" + e2.getMessage();
                JOptionPane.showMessageDialog(null, msg, "提示", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    /**
     * 展示数据库连接配置信息
     */
    private void showDbConnInfo(){
        // jdbc:oracle:thin:@127.0.0.1:1521:orcl
        String url = Global.getPropVal(Global.DB_ORACLE_PROP_KEY_URL);
        String username = Global.getPropVal(Global.DB_ORACLE_PROP_KEY_USERNAME);
        String pwd = Global.getPropVal(Global.DB_ORACLE_PROP_KEY_PASSWORD);

        if(!StringUtil.isEmpty(url)){
            String[] arr = url.split("@");
            if(arr.length == 2){
                String[] arr2 = arr[1].split(Global.SYMBOL_COLON);
                if(arr2.length == 3){
                    ipTextField.setText(arr2[0]);
                    portTextField.setText(arr2[1]);
                    sidTextField.setText(arr2[2]);
                }
            }
        }

        userNameTextField.setText(username);
        pwdField.setText(pwd);
    }

    /**
     * 校验输入内容
     */
    private boolean validateInput(){
        String ip = ipTextField.getText();
        if(StringUtil.isEmpty(ip)){
            JOptionPane.showMessageDialog(null, "请输入IP", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String port = portTextField.getText();
        if(StringUtil.isEmpty(port)){
            JOptionPane.showMessageDialog(null, "请输入端口号", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String sid = sidTextField.getText();
        if(StringUtil.isEmpty(sid)){
            JOptionPane.showMessageDialog(null, "请输入SID", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String userName = userNameTextField.getText();
        if(StringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(null, "请输入用户名", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String pwd = String.valueOf(pwdField.getPassword());
        if(StringUtil.isEmpty(pwd)){
            JOptionPane.showMessageDialog(null, "请输入密码", "提示", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

}

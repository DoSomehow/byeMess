package org.ms.example;

import javax.swing.*;
import java.awt.*;

public class MyTestGridBag {

    public static void main(String[] args) {
        test();
    }

    private static void test(){
        JFrame jFrame = new JFrame("测试GridBag");

        JPanel contentPane = new JPanel();
        jFrame.add(contentPane);

        GridBagLayout gridbag = new GridBagLayout();
        contentPane.setLayout(gridbag);

        JButton jButton1 = new JButton("按钮1");
        contentPane.add(jButton1);

        JButton jButton2 = new JButton("按钮2");
        contentPane.add(jButton2);

        JButton jButton3 = new JButton("按钮3");
        contentPane.add(jButton3);

        JButton jButton4 = new JButton("按钮4");
        contentPane.add(jButton4);


        GridBagConstraints c = new GridBagConstraints();
        //添加按钮1
        c.fill = GridBagConstraints.BOTH;
        c.gridheight=2;
        c.gridwidth=1;
        c.weightx=0.0;//默认值为0.0
        c.weighty=0.0;//默认值为0.0
        c.anchor= GridBagConstraints.SOUTHWEST;

        gridbag.setConstraints(jButton1, c);

        //添加按钮2
        c.fill = GridBagConstraints.BOTH;
        c.gridheight=1;
        c.gridwidth=GridBagConstraints.REMAINDER;
        c.weightx=1.0;//默认值为0.0
        c.weighty=0.7;

        gridbag.setConstraints(jButton2, c);

        //添加按钮3
        c.fill = GridBagConstraints.BOTH;
        c.gridheight=1;
        c.gridwidth=GridBagConstraints.REMAINDER;
        c.weighty=0.3;

        gridbag.setConstraints(jButton3, c);

        //添加按钮4
        c.fill = GridBagConstraints.BOTH;
        c.gridheight=1;
        c.gridwidth=1;
        c.weightx=1.0;//默认值为0.0
        c.weighty=0.0;//默认值为0.0

        gridbag.setConstraints(jButton4, c);



        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
    }

}

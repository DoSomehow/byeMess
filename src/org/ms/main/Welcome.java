package org.ms.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ryan on 2017/11/14.
 */
public class Welcome {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Welcome!");
        jFrame.setSize(400, 400);
        jFrame.setLayout(new FlowLayout());

        JButton btn1 = new JButton("test");
        jFrame.add(btn1);

        JButton btn2 = new JButton("显示不");
        jFrame.add(btn2);

        JTextField jTextField = new JTextField(10);
        jFrame.add(jTextField);
        ButtonListener btnListener = new ButtonListener(jTextField);
        btn1.addActionListener(btnListener);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    static class ButtonListener implements ActionListener{
        private JTextField jTextField;

        public ButtonListener(JTextField jTextField){
            this.jTextField = jTextField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String content = jTextField.getText();
            String newContext = "";
            if(content == null || "".equals(content)){
                newContext = ((JButton)e.getSource()).getText();
            }
            jTextField.setText(newContext);
        }
    }
}

package org.ms.code.view;

import org.ms.code.GmoGen;
import org.ms.common.AbstractViewPanel;
import org.ms.common.util.ResultVO;
import org.ms.common.util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GmoView extends AbstractViewPanel {

    JLabel tableNameLabel = new JLabel("表名:");
    JTextField tableNameField = new JTextField(30);
    JLabel tipLabel = new JLabel();

    JButton genBtn = new JButton("确定");

    public GmoView() {

        contentPane.setLayout(new FlowLayout());

        contentPane.add(tableNameLabel);
        contentPane.add(tableNameField);
        contentPane.add(tipLabel);

        contentPane.add(genBtn);

        genBtn.addActionListener(new GenListener());
    }

    //
    class GenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tableName = tableNameField.getText().trim();
            if(StringUtil.isEmpty(tableName)){
                JOptionPane.showMessageDialog(null, "请输入表名！", "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            //
            GmoGen gmoGen = new GmoGen();
            ResultVO result = gmoGen.generateCode(tableName);
            if(result.getSuccess()){
                JOptionPane.showMessageDialog(null, result.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, result.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


}

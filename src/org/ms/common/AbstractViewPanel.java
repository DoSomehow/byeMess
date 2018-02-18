package org.ms.common;

import javax.swing.*;

/**
 *
 * @author wsy
 * @date 2018-02-16
 */
public abstract class AbstractViewPanel extends JFrame implements ViewPanel {

    protected JPanel contentPane;  //内容面板

    public AbstractViewPanel(){
        //新建内容面板
        contentPane = new JPanel();
        //放入框架
        add(contentPane);
    }

    @Override
    public JPanel getViewPane() {
        return contentPane;
    }
}

package org.ms.view;

import org.ms.common.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author wsy
 * @date 2018-02-15
 */
public class MainView extends AbstractViewPanel {

    JLabel label = new JLabel("Welcome to use this tool!", JLabel.CENTER);

    public MainView() {
        //
        contentPane.setLayout(new BorderLayout());
        //
        contentPane.add(BorderLayout.CENTER, label);

    }

}

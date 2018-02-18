package org.ms.help.view;

import org.ms.common.AbstractViewPanel;

import javax.swing.*;
import java.awt.*;

public class AboutView extends AbstractViewPanel {

    JLabel label = new JLabel("ByeMess V.0.0.1", JLabel.CENTER);

    public AboutView(){
        //
        contentPane.setLayout(new BorderLayout());
        //
        contentPane.add(BorderLayout.CENTER, label);
    }

}

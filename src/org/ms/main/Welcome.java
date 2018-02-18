package org.ms.main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ryan on 2017/11/14.
 */
public class Welcome {

    boolean packFrame = false;

    public Welcome() {
        MainFrame frame = new MainFrame();
        if (this.packFrame){
            frame.pack();
        }else{
            frame.validate();
        }
        //屏幕居中
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height){
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width){
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        //显示
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try{
            //校验jdk版本
            String version = System.getProperty("java.vm.version");
            if(version != null){
                try{
                    int i = version.indexOf('.');
                    int v1 = Integer.parseInt(version.substring(0, i));
                    int j = version.indexOf('.', i + 1);
                    int v2 = Integer.parseInt(version.substring(i + 1, j));
                    if ((v1 < 1) || ((v1 == 1) && (v2 < 3))){
                        JOptionPane.showMessageDialog(null, "Need Java VM version 1.3 or later.", "ERROR", 0);
                        System.exit(-1);
                    }
                }catch (Exception ex){

                }
            }
            //设置皮肤
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            //start
            new Welcome();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

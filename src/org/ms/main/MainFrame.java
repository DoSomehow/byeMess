package org.ms.main;

import org.ms.code.view.GmoView;
import org.ms.common.ViewPanel;
import org.ms.help.view.AboutView;
import org.ms.common.view.DsView;
import org.ms.main.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel mainPane;  //主面板
    private JPanel viewPane;  //内容面板


    public MainFrame() {
        this.mainPane = (JPanel)getContentPane();
        this.mainPane.setMinimumSize(new Dimension(600, 450));
        this.mainPane.setPreferredSize(new Dimension(600, 450));
        // this.mainPane.setBorder(new TitledBorder("mainPane"));
        setContentPane(this.mainPane);
        // setResizable(false);
        setSize(new Dimension(600, 450));
        setState(0);  //0时为正常大小，1时为最小化
        setTitle("ByeMess - A Simple Tool For Developer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //添加菜单栏
        JMenuBar mb = initMenuBar();
        setJMenuBar(mb);

        //设置布局格式
        // setLayout(layout);

        //加载主视图面板
        MainView view = new MainView();
        loadViewPanel(view);
    }

    //初始化菜单栏
    private JMenuBar initMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        /* SQL */
        JMenu sqlMenu = new JMenu("SQL");
        JMenuItem aaaItem = new JMenuItem("aaa");

        /* 代码生成 */
        JMenu codeMenu = new JMenu("代码");
        JMenuItem gmoItem = new JMenuItem("关口计量");

        menuBar.add(codeMenu);
        codeMenu.add(gmoItem);
        gmoItem.addActionListener(new ShowGmoVIewListener());

        /* 通用 */
        JMenu utilMenu = new JMenu("通用");
        JMenuItem dsItem = new JMenuItem("数据源配置");

        menuBar.add(utilMenu);
        utilMenu.add(dsItem);
        dsItem.addActionListener(new ShowDsViewListener());

        /* 帮助 */
        JMenu helpMenu = new JMenu("帮助");
        JMenuItem aboutItem = new JMenuItem("关于");

        menuBar.add(helpMenu);
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(new ShowAboutViewListener());

        return menuBar;
    }

    //加载视图面板
    private void loadViewPanel(ViewPanel view){
        //先移除
        if (viewPane != null) {
            mainPane.remove(viewPane);
        }
        //添加
        viewPane = view.getViewPane();
        mainPane.add(viewPane);
        //
        // update(getGraphics());
        validate();
    }

    //展示数据源视图面板
    class ShowDsViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //获取新的
            DsView view = new DsView();

            //加载该视图面板
            loadViewPanel(view);
        }
    }

    //展示关于视图面板
    class ShowAboutViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //获取新的
            AboutView view = new AboutView();

            //加载该视图面板
            loadViewPanel(view);
        }
    }

    //展示gmo视图面板
    class ShowGmoVIewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //获取新的
            GmoView view = new GmoView();

            //加载该视图面板
            loadViewPanel(view);
        }
    }


}

package org.ms.code;

import org.ms.common.util.ResultVO;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 *
 * @author wsy
 * @date 2018-02-17
 */
public class GmoGen extends AbstractGen {

    @Override
    public ResultVO<Object> generateCode(String tableName) {

        resultVO.setMessage("");

        return resultVO;
    }

    public static void main(String[] args) {
        File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
        String desktopPath = desktopDir.getAbsolutePath();  //操作系统的桌面路径
        System.out.println(desktopPath);
        String user = System.getProperty("user.dir");  //文件所在路径的父路径
        System.out.println(user);
    }
}

package org.ms.code;

import freemarker.template.Configuration;
import org.ms.common.util.ResultVO;
import org.ms.common.util.StringUtil;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 *
 * @author wsy
 * @date 2018-02-17
 */
public abstract class AbstractGen implements GenCode {

    protected static Configuration cfg;
    protected static Pattern linePattern = Pattern.compile("_(\\w)");
    protected ResultVO<Object> resultVO = new ResultVO<>(false, "");

    protected static String PATH_OUTPUT_BASE;  //输出文件根路径

    public AbstractGen(){
        //
        initTemplateCfg();
        //
        initBaseOutpuPath();
    }

    /**
     * 初始化freemarker配置对象
     */
    private void initTemplateCfg(){
        /* You should do this ONLY ONCE in the whole application life-cycle */
        /* Create and adjust the configuration singleton */
        if(cfg != null)
            return;

        cfg = new Configuration(Configuration.VERSION_2_3_25);
        try {
            String ftlPath = "resources\\template\\gen";
            cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding("UTF-8");
        // cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        // cfg.setLogTemplateExceptions(false);
    }

    /**
     * 初始化输出文件的根路径
     */
    private void initBaseOutpuPath(){
        if(StringUtil.isEmpty(PATH_OUTPUT_BASE)){
            File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
            PATH_OUTPUT_BASE = desktopDir.getAbsolutePath() + "\\byeMess";
        }
    }

}
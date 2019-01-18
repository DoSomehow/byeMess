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
    protected static Pattern linePattern = Pattern.compile("_(\\w)");  //这个也许要根据项目不同而不同
    protected ResultVO resultVO = new ResultVO(Boolean.FALSE, "");  //关于其泛型，得好好考虑考虑

    protected static String PATH_OUTPUT_BASE;  //输出文件根路径

    protected String tableName;  //表名


    public AbstractGen(){
        //
        initTemplateCfg();
        //
        initBaseOutpuPath();
    }

    @Override
    public ResultVO generateCode(String tableName) {

        if (StringUtil.isEmpty(tableName)) {
            resultVO.setMessage("生成失败！获取表名为空！");
            return resultVO;
        }
        this.tableName = tableName.toUpperCase();

        if (cfg == null) {
            resultVO.setMessage("生成失败！获取freemarker的cfg对象失败！");
            return resultVO;
        }

        try {
            execute();
        } catch (Exception e) {
            resultVO.setSuccess(Boolean.FALSE);
            resultVO.setMessage("生成失败！失败原因："  + e.getMessage());
        }

        return resultVO;
    }

    /**
     *
     */
    protected abstract void execute();

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
            cfg.setDirectoryForTemplateLoading(new File("src\\resources\\template\\gen"));
        } catch (IOException e) {
            e.printStackTrace();  //TODO: 这里要抛出异常。但是不能仅仅是抛出
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
            File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
            PATH_OUTPUT_BASE = desktopDir.getAbsolutePath() + "\\byeMess";
        }
    }

}
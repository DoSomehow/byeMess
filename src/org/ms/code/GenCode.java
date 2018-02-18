package org.ms.code;

import org.ms.common.util.ResultVO;

/**
 *
 * @author wsy
 * @date 2018-02-17
 */
public interface GenCode {


    /**
     * 生成代码
     * @param tableName
     * @return java.util.Map
     */
    ResultVO<?> generateCode(String tableName);

}

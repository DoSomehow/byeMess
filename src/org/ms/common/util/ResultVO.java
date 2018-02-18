package org.ms.common.util;



public class ResultVO<T> {
    private Boolean success;//是否执行成功;
    private String message ;//提示消息；
    private T data = null;//数据
    
    public ResultVO(){}
    
    public ResultVO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultVO(Boolean success, String message, T data) {
        this.success = success;
        this.message= message;
        this.data=data;
    }
    
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    
}


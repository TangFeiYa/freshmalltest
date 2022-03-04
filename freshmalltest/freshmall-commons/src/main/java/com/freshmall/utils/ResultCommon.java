package com.freshmall.utils;

/**
 * @BelongsProject: springboot2020
 * @BelongsPackage: com.it.utils
 * @CreateTime: 2020-10-30 14:53
 * @Description: TODO
 */
public class ResultCommon<T> {

    private int code;
    private String msg;
    private T data;

    public ResultCommon() {
    }

    public ResultCommon(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultCommon<Object> success(ResultCode resultCode){
        return new ResultCommon<>(resultCode.getCode(), resultCode.getMsg());
    }

    public static ResultCommon<Object> success(ResultCode resultCode, Object data){
        ResultCommon<Object> success = success(resultCode);
        success.setData(data);
        return success;
    }

    public static ResultCommon<Object> fail(ResultCode resultCode){
        return success(resultCode);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "返回值JSON{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

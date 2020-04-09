package com.bingo.wanandroid.core.bean;

/**
 * author bingo
 * date 2020/1/1
 */
public class BaseResponse<T> {

    /**
     * data : ......
     * errorCode : 0
     * errorMsg :
     */

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    private T data;
    /**
      0 成功 1 失败
     */
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

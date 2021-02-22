package com.acm.clubManagementSystem.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName  返回数据集
 * @Author lkh
 * @Date 2021/1/18 11:05
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 请求响应code，0为成功 其他为失败
     */
    @ApiModelProperty(value = "请求响应code，0为成功 其他为失败", name = "code")
    private int code;
    /**
     * 响应异常码详细信息
     */
    @ApiModelProperty(value = "响应异常码详细信息", name = "msg")
    private String msg;
    /**
     * 响应内容 ， code 0 时为 返回 数据
     */
    @ApiModelProperty(value = "需要返回的数据", name = "data")
    private T data;

    public <T>ResultData() {
        this.code=ResponseContants.SUCCESS;
        this.msg=ResponseContants.SUCCESS_MESSAGE;
        this.data=null;
    }

    /**
     * 操作成功 data为null
     * @param <T>
     * @return
     */
    public static <T>ResultData success(){
        return new <T>ResultData();
    }

    /**
     * 操作成功 data为token对象
     * @param <T>
     * @return
     */
    public static <T>ResultData success(int code,String message,T data){
        return new <T>ResultData(code,message,data);
    }




    public <T>ResultData(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data=null;
    }

    /**
     * 操作失败 data为null
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T>ResultData fail(int code , String msg){
        return new <T>ResultData(code,msg);
    }
}

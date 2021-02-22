package com.acm.clubManagementSystem.exception;

/**
 * @ClassName 自定义异常类
 * @Author lkh
 * @Date 2021/1/19 16:33
 * @Version 1.0
 */
public class BusinessException extends RuntimeException{
    /**
     * 异常编号
     */
    private final int messageCode;

    /**
     * 对messageCode 异常信息进行补充说明
     */
    private final String detailMessage;

    public BusinessException(int messageCode,String message) {
        super(message);
        this.messageCode = messageCode;
        this.detailMessage = message;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}

package org.ct.ctTool.exception;
/**
 * @author Think
 * @Title: OperationException
 * @ProjectName easyTool
 * @Description: 操作异常
 * @date 2019/2/21 14:43
 * @Version 1.0
 */
public class OperationException extends RuntimeException {

    public OperationException(String message) {
        super(message);
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }

}

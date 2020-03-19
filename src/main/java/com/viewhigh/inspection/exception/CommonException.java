package com.viewhigh.inspection.exception;

import lombok.Data;

/**
 * @author zhangS
 * @Description 自定义异常类
 * @date 2019-11-14 10:18
 */
@Data
//@AllArgsConstructor
public class CommonException  extends RuntimeException{

    String code;
    String msg;
    public CommonException(String code,String msg) {
        super(code + msg);
        this.code = code;
        this.msg = msg;
    }
}

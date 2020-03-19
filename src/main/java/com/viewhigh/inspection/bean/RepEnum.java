package com.viewhigh.inspection.bean;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-15 17:10
 */
//@AllArgsConstructor
public enum RepEnum {

    REP_OK(200,"成功"),
    REP_NO_DATA(400,"不在可撤销状态");

    private int code;
    private String msg;

    private RepEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
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
}

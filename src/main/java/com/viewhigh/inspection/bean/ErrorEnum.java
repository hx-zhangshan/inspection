package com.viewhigh.inspection.bean;

/**
 * @author zhangS
 * @Description
 * @date 2019-11-15 17:10
 */
//@AllArgsConstructor
public enum ErrorEnum {

    PASSWORD_ERROR("8000","用户密码错误！"),
    ACCTOUT_ERROR("8001","用户名不存在！"),

    DB_ERROR("300","数据库错误"),
    DB_DATA_NOT_FOUND("2000","未找到任何数据"),
    INSPECTION_STATE_START("1000","调度中心未派单"),
    INSPECTION_STATE_SENT("1001","调度中心已派单"),
    INSPECTION_STATE_ORDER("1002","工程师已接单"),
    INSPECTION_STATE_END("1003","执行完毕");

    private String code;
    private String msg;

    private ErrorEnum(String code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

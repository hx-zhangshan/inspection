package com.viewhigh.inspection.bean;

/**
 * @author zhangS
 * @Description
 * @date 2019-12-17 15:11
 * @description
 */
public enum ScheduledEnum {
    YEAR("年"),
    HALFYEAR("半年"),
    QUARTER("季度"),
    MONTH("月");

    private ScheduledEnum(String name){
        this.name=name;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

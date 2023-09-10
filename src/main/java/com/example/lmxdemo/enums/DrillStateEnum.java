package com.example.lmxdemo.enums;

public enum DrillStateEnum {

    READY(0, "就绪"),			// 就绪
    DRILLING(1, "演练中"),	    // 演练中
    PAUSE(2, "暂停"),			// 暂停
    FAIL(3, "失败"),			    // 失败
    SUCCESS(4, "成功");		    // 成功

    private int value;
    private String desc;

    private DrillStateEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}

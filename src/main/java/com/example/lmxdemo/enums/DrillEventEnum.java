package com.example.lmxdemo.enums;

public enum DrillEventEnum {

    START(0, "启动"),
    PAUSE(1, "手动暂停/阶段性完成"),
    GOON(2, "继续"),
    RESTART(3, "重启"),
    DRILLERROR(4, "演练错误"),
    CONFERROR(5, "配置错误"),
    COMPLETE(6, "完成"),
    MODIFYCONF(8, "修改配置"),
    FORWARD(9, "前进"),
    BACKWARD(10, "后退");

    private int value;
    private String desc;

    DrillEventEnum(int value, String desc) {
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

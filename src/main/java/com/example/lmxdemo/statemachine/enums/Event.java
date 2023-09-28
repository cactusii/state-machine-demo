package com.example.lmxdemo.statemachine.enums;

import lombok.Getter;

@Getter
public enum Event {

    START(0, "创建"),
    CREATE(1, "创建完成"),
    MODIFY(2, "修改配置"),
    CREATE_ERROR(4, "创建异常"),
    DRILL_START(3, "开始演练"),
    DRILL_COMPLETE(6, "完成演练"),
    DRILL_ERROR(7, "演练异常"),
    STOP(8, "停止"),
    DELETE1(5, "删除"),
    DELETE2(9, "删除"),
    DELETE3(10, "删除"),
    DELETE4(11, "删除"),
    DELETE5(12, "删除")
    ;

    private int value;
    private String desc;

    Event(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Event fromValue(int value) {
        for (Event event: Event.values()) {
            if (event.getValue() == value) {
                return event;
            }
        }
        return null;
    }

}

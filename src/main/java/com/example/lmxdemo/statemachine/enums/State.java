package com.example.lmxdemo.statemachine.enums;

public enum State {

    CREATE(0, "新建"),
    CREATING(1, "创建中"),
    CREATE_SUCCESS(2, "创建成功"),
    CREATE_FAIL(3, "创建失败"),
    DRILLING(4, "演练中"),
    DRILL_SUCCESS(5, "演练成功"),
    DRILL_FAIL(6, "演练失败"),
    DRILL_STOP(7, "演练停止"),
    DELETE(8, "删除")
    ;

    private int value;
    private String desc;

    private State(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static State fromValue(int value) {
        for (State state : State.values()) {
            if (value == state.getValue()){
                return state;
            }
        }
        return null;
    }
}

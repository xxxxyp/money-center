package com.fourboy.constant;

public enum ResultCodeEnum {

    SUCCESS("200", "成功", true),
    NO_DATA("201", "未查询到数据", true),
    FAIL("501", "失败", false);

    private String msg;
    private String code;
    boolean flag;

    ResultCodeEnum(String code, String msg, boolean flag) {
        this.msg = msg;
        this.code = code;
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

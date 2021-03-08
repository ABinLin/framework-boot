package com.farerboy.framework.boot.common.enums;

/**
 * TODO description
 *
 * @author linjianbin
 */
public enum DeletedEnum {

    /**
     * 删除标志
     */
    NOT_DELETED(0,"未删除"),
    DELETED(1,"已删除"),
    ;

    private Integer code;

    private String desc;

    DeletedEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static int getNotDeleted(){
        return NOT_DELETED.getCode();
    }

    public static int getDeleted(){
        return DELETED.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

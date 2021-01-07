package com.farerboy.framework.boot.util.encryption.enums;

/**
 * AES 加密模式枚举
 *
 * @author farerboy
 */
public enum AesModeEnum {

    /**
     * ECB模式
     * @author farerboy
     */
    ECB("ECB"),
    CBC("CBC"),
    ;

    private String mode;

    AesModeEnum(String mode){
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public static AesModeEnum get(String mode){
        for (AesModeEnum m : AesModeEnum.values()) {
            if(m.getMode().equals(mode)){
                return m;
            }
        }
        return null;
    }
}

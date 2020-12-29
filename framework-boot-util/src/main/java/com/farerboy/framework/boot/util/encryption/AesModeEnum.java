package com.farerboy.framework.boot.util.encryption;

/**
 * AES 加密模式枚举
 *
 * @author farerboy
 * @date 2020/12/28 3:48 下午
 */
public enum AesModeEnum {

    /**
     * ECB模式
     * @author farerboy
     * @date 2020/12/28 3:50 下午
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

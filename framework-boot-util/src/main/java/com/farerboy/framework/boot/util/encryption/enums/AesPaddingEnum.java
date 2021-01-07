package com.farerboy.framework.boot.util.encryption.enums;

/**
 * AES 加密填充枚举
 *
 * @author farerboy
 */
public enum  AesPaddingEnum {

    /**
     * 加密填充枚举
     * @author farerboy
     */
    PKCS_5_PADDING("PKCS5Padding"),

    ;

    private String padding;

    AesPaddingEnum(String padding){
        this.padding = padding;
    }

    public String getPadding() {
        return padding;
    }

    public static AesPaddingEnum get(String padding){
        for (AesPaddingEnum m : AesPaddingEnum.values()) {
            if(m.getPadding().equals(padding)){
                return m;
            }
        }
        return null;
    }

}

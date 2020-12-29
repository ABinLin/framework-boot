package com.farerboy.framework.boot.util.encryption;

/**
 * AES 加密填充枚举
 *
 * @author farerboy
 * @date 2020/12/28 3:53 下午
 */
public enum  AesPaddingEnum {

    /**
     * 加密填充枚举
     * @author farerboy
     * @date 2020/12/28 3:50 下午
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

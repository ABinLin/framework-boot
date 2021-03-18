package com.farerboy.framework.boot.server.sdk.kuaidi100.properties;

/**
 * TODO description
 * 2021/3/18 4:50 下午
 *
 * @author linjianbin
 */
public enum  Kuaidi100Enum {
    shunfeng("shunfeng","顺丰",1),
    ems("ems","EMS",1),
    youzhengguonei("youzhengguonei","邮政包裹",1),
    huitongkuaidi("huitongkuaidi","百世汇通",1),
    shentong("shentong","申通",1),
    zhongtong("zhongtong","中通",1),
    yuantong("yuantong","圆通",1),
    guotongkuaidi("guotongkuaidi","国通",1),
    yunda("yunda","韵达",1),
    tiantian("tiantian","天天",1),
    youshuwuliu("youshuwuliu","优速",1),
    jd("jd","京东",1),
    zhaijisong("zhaijisong","宅急送",1),
    annengwuliu("annengwuliu","安能物流",1),
    aae("aae","AAE",0),
    aramex("aramex","Aramex",0),
    baishiwuliu("baishiwuliu","百世快运",0),
    coe("coe","COE",0),
    flyway("flyway","程光快递",0),
    chuanxiwuliu("chuanxiwuliu","传喜物流",0),
    dhl("dhl","DHL",0),
    debangwuliu("debangwuliu","德邦快递",0),
    disifang("disifang","递四方",0),
    emsguoji("emsguoji","EMS国际件",0),
    ewe("ewe","EWE",0),
    fedex("fedex","FedEx",0),
    chronopostfren("chronopostfren","法国邮政",0),
    koreapost("koreapost","韩国邮政",0),
    postnl("postnl","荷兰邮政",0),
    jinguangsudikuaijian("jinguangsudikuaijian","京广快递",0),
    jiayiwuliu("jiayiwuliu","佳怡物流",0),
    kuayue("kuayue","跨越速运",0),
    hrvatska("hrvatska","克罗地亚邮政",0),
    lianhaowuliu("lianhaowuliu","联昊通",0),
    usps("usps","美国邮政",0),
    minghangkuaidi("minghangkuaidi","民航快递",0),
    mexico("mexico","墨西哥邮政",0),
    ganzhongnengda("ganzhongnengda","能达速递",0),
    postennorge("postennorge","挪威邮政",0),
    southafrican("southafrican","南非邮政",0),
    ocs("ocs","OCS",0),
    ontrac("ontrac","OnTrac",0),
    portugalctt("portugalctt","葡萄牙邮政",0),
    swisspost("swisspost","瑞士邮政",0),
    quanyikuaidi("quanyikuaidi","全一快递",0),
    rrs("rrs","日日顺物流",0),
    japanposten("japanposten","日本邮政",0),
    suer("suer","速尔快递",0),
    shenghuiwuliu("shenghuiwuliu","盛辉物流",0),
    tnt("tnt","TNT",0),
    tiandihuayu("tiandihuayu","天地华宇",0),
    thailand("thailand","泰国邮政",0),
    ups("ups","UPS",0),
    wanjiawuliu("wanjiawuliu","万家物流",0),
    ukrpost("ukrpost","乌克兰邮政",0),
    xinfengwuliu("xinfengwuliu","信丰物流",0),
    hkpost("hkpost","中国香港邮政",0),
    youzhengguoji("youzhengguoji","邮政国际",0),
    ztwl("ztwl","中铁飞豹",0),

    ;

    private String code;
    private String name;
    private int commonly;

    Kuaidi100Enum(String code,String name,int commonly){
        this.code = code;
        this.name = name;
        this.commonly = commonly;
    }

    public static Kuaidi100Enum get(String code){
        if(code == null || "".equals(code.trim())){
            return null;
        }
        for(Kuaidi100Enum e : values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCommonly() {
        return commonly;
    }

    public void setCommonly(int commonly) {
        this.commonly = commonly;
    }
}

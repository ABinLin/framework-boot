package com.farerboy.framework.boot.test.params;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * TODO description
 *
 * @author linjianbin
 * @date 2021/2/3 2:34 下午
 */
@Data
public class TestConvertParam {

    private Date time;

    private Long number;

    private String string;

    private LocalDate localDate;

}

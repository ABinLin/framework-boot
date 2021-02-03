//package com.farerboy.framework.boot.test.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Slf4j
//@Configuration
//public class JacksonCustomizerConfig {
//  	/**
//     * 时间LocalDateTime转换
//     */
//    @Component
//    public static class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
//        @Override
//        public LocalDateTime convert(String source) {
//            if (StringUtils.isBlank(source)) {
//                return null;
//            }
//            if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
//                return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            }
//            throw new IllegalArgumentException("Invalid value '" + source + "'");
//        }
//    }
//
//    /**
//     * 时间LocalDate转换
//     */
//    @Component
//    public static class LocalDateConverter implements Converter<String, LocalDate> {
//        @Override
//        public LocalDate convert(String source) {
//            if (StringUtils.isBlank(source)) {
//                return null;
//            }
//            if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
//                return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            }
//            throw new IllegalArgumentException("Invalid value '" + source + "'");
//        }
//    }
//}
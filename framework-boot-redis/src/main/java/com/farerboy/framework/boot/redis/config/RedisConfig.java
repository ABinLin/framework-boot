package com.farerboy.framework.boot.redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.farerboy.framework.boot.redis.manager.RedisManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author farerboy
 */
@Configuration
@EnableCaching
public class RedisConfig {
    /*@Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
        return cacheManager;
        *//*RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        // 多个缓存的名称,目前只定义了一个
        rcm.setCacheNames(Arrays.asList("thisredis"));
        //设置缓存默认过期时间(秒)
        rcm.setDefaultExpiration(600);
        return rcm;*//*
    }*/
    // 以下两种redisTemplate自由根据场景选择
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(connectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        FastJsonRedisSerializer fastJsonRedisSerializer =new FastJsonRedisSerializer(Object.class);
        serializer.setObjectMapper(mapper);
        // value序列化方式采用jackson
        template.setValueSerializer(fastJsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }

    @Bean
    RedisManager redisUtil(){
        return new RedisManager();
    }
}

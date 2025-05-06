package com.DevTino.festino_main.group_order.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    // RedisConnectionFactory는 redis 연결 설정
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        try {
            RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(redisHost, redisPort);
            return new LettuceConnectionFactory(redisConfig);
        } catch (Exception e) {
            log.error("Redis Connection Factory 생성 실패", e);
            throw e;
        }
    }

    // RedisTemplate은 key, value값의 직렬화 방식 설정
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        try {
            // redis 저장 형태
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            // RedisConnectionFactory을 통해 연결 방식 설정
            redisTemplate.setConnectionFactory(redisConnectionFactory());
            // key 값은 문자열 형태로 저장
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            // value 값은 JSON 형태로 저장
            redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
            return redisTemplate;
        } catch (Exception e) {
            log.error("Redis Template 생성 실패", e);
            throw e;
        }
    }
}
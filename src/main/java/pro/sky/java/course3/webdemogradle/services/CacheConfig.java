package pro.sky.java.course3.webdemogradle.services;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(
                new ConcurrentMapCache("books"),
                new ConcurrentMapCache("booksCovers")
        ));
        return manager;
    }

    @Bean
    public KeyGenerator keyGenerator() {

        return null;
    }
}

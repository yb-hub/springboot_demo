package yb.demo.springbootredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class SpringbootRedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootRedisApplication.class, args);
//        Object simpleCacheConfiguration = context.getBeanFactory().getSingleton("SimpleCacheConfiguration");
//        Object redisCacheConfiguration = context.getBeanFactory().getSingleton("RedisCacheConfiguration");
//        System.out.println(simpleCacheConfiguration);
//        System.out.println(redisCacheConfiguration);
    }

}

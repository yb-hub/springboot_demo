package yb.demo.springbootcache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import yb.demo.springbootcache.mapper.UserMapper;
import yb.demo.springbootcache.model.User;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable(cacheNames = "user",key = "#id")
    public User getUserById(int id) {
        User user = userMapper.getUserById(id);
        return user;
    }
}

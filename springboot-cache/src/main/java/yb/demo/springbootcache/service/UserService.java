package yb.demo.springbootcache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import yb.demo.springbootcache.mapper.UserMapper;
import yb.demo.springbootcache.model.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable(cacheNames = "user",key = "#id")
    public User getUserById(int id) {
        User user = userMapper.getUserById(id);
        return user;
    }
    //result:返回值，@Cacheable中是不能用result的，因为在@Cacheable中key是在执行方法前就创建了。
    //keyGenerator:可是使用自定义的keyGenerator来生成key，key和keyGenerator只能用其中一个
    //condition:用于条件判断
    //unless:当条件不满足时，才会加入到缓存中
    //sync:是否异步，在@CachePut中是没有异步的
    @CachePut(cacheNames = "user",key="#result.id",condition = "#id>0")
    public User updateUser(int id, User user) {
        userMapper.updateUser(id,user);
        user.setId(id);
        return user;
    }

    //allEntries:是否删除全部（相同cacheNames）
    //beforeInvocation:是否在执行方法抢删除，即使出现了异常，也会删除
    @CacheEvict(cacheNames = "user",key = "#id",beforeInvocation = true)
    public int  deletUser(int id){
        int i = userMapper.deletUser(id);
        return i;
    }

    @Cacheable(cacheNames = "userList")
    public List<User> getUserList() {
        return userMapper.getUserList();
    }




    @Cacheable(cacheNames = "userList")
    public List<User> getUserListByNameAndAge(String name, int age) {
        return userMapper.getUserListByNameAndAge(name,age);
    }

    @Caching(
      evict = {@CacheEvict(cacheNames = "userList",allEntries = true)},
      put = {@CachePut(cacheNames = "user",key="#result.id")}
    )
    public User insertUser(User user) {
        userMapper.insertUser(user);
        return user;
    }
}

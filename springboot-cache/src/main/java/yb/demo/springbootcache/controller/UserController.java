package yb.demo.springbootcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import yb.demo.springbootcache.model.User;
import yb.demo.springbootcache.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        return user;
    }
}

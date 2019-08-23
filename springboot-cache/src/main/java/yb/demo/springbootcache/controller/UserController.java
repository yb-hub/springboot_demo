package yb.demo.springbootcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yb.demo.springbootcache.model.User;
import yb.demo.springbootcache.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        return user;
    }
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") int id,@RequestBody User user){
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/user/{id}")
    public int deletUser(@PathVariable("id") int id){
        return userService.deletUser(id);
    }

    @GetMapping("/users")
    public List<User> getUserList(){
        return userService.getUserList();
    }

    @PostMapping("/user")
    public User insertUser(@RequestBody User user){
        return userService.insertUser(user);
    }

    @GetMapping("/user")
    public List<User> getUserListByNameAndAge(String name,int age){
        return userService.getUserListByNameAndAge(name,age);
    }
}

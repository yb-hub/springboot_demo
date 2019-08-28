package yb.demo.springbootsecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/user1")
    public String user1() {
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return "this is user1" + username;
    }


    @RequestMapping("/user2")
    public String user2() {
        return "this is user2";
    }


    @RequestMapping("/user3")
    public String user3() {
        return "this is user3";
    }
}

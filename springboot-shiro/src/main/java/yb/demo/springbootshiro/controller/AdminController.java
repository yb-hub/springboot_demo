package yb.demo.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Yang
 * @Date: 2019/9/14 0014 20:59
 * @Description:
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("test1")
    public Object test1() {
        System.out.println("this is test1");
        return "test1";
    }

    @RequestMapping("test2")
    public Object test2() {
        System.out.println("thsi is test2");
        return "test2";
    }

    @GetMapping("/login")
    public Object login(String username, String password, HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException uae) {
            return "用户帐号或密码不正确";
        } catch (LockedAccountException lae) {
            return "用户帐号已锁定不可用";

        } catch (AuthenticationException ae) {
            return "认证失败";
        }
        return "login success";
    }


    @GetMapping("auth/403")
    public Object noAuth(){
        return "403";
    }
}

package yb.demo.jwt.controller;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yb.demo.jwt.requestparam.UserParam;
import yb.demo.jwt.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: Yang
 * @Date: 2019/12/01 13:38
 * @Description:
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public HashMap login(@RequestBody UserParam userParam){
        HashMap<String, Object> map = new HashMap<>();
        //登录成功后返回一个token
        if("admin".equals(userParam.getUsername()) && "123456".equals(userParam.getPassword())){

            map.put("code",200);
            map.put("message", "登录成功");
            map.put("token", JwtUtils.createToken("yang", 1));
            map.put("refreshToken",JwtUtils.createToken("yang", 2));
        }else{
            map.put("code",401);
            map.put("message","登录失败");
        }
        return map;
    }



    //判断token是否过期，如未过期则返回成功，如果过期了，返回失败；前端调用checkResfreshToken接口
    //如果refreshToken没有过期，则返回成功，并且返回一个新的token和refreshToken,如果过期了，就返回失败
    @GetMapping("/checkToken")
    public HashMap checkToken(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        Boolean result = JwtUtils.validateToken(token);
        if(result){
            map.put("code", 200);
            map.put("message", "成功");
        }else{
            map.put("code", 401);
            map.put("message", "token过期");
        }
        return map;
    }

    @GetMapping("/checkRefreshToken")
    public HashMap checkRefreshToken(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();
        String refreshToken = request.getHeader("refreshToken");
        Boolean result = JwtUtils.validateToken(refreshToken);
        if(result){
            map.put("code", 200);
            map.put("message", "成功");
            map.put("token",JwtUtils.createToken("yang", 1));
            map.put("refreshToken",JwtUtils.createToken("yang", 2));
        }else{
            map.put("code", 401);
            map.put("message", "refreshtoken过期,请重新登录");
        }
        return map;
    }
}

package yb.demo.springbootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public String test1(){
        return "this is test1";
    }
    @RequestMapping("/test2")
    public String test2(){
        return "this is test2";
    }
}

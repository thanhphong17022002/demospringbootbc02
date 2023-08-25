package com.cybersoft.demospringboot02.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@Controller : định nghĩa đường dẫn được dùng để trả ra nội dung html
@RestController : dùng để định nghĩa đường dẫn , nội dung trả về String (API)
@ResponeBody : trả nội dung kiểu String



*/
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("")
    public String hello(){
        return "Hello Spring boot";
    }

    @GetMapping("/cybersoft")
    public String helloCybersoft(){
        return "hello cybersoft";
    }
}

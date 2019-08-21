package com.lrh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class DistributedSessionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedSessionDemoApplication.class, args);
    }


    @RequestMapping
    public String test(HttpSession session,@RequestParam(value = "userName",required = false) String userNameParam){
        String userName = (String) session.getAttribute("userName");
        if(userName == null){
            session.setAttribute("userName", userNameParam);
            userName = userNameParam;
        }
        return "hello: " + userName;
    }


}

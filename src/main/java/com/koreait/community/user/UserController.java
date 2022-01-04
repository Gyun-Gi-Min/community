package com.koreait.community.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/join")
    public void join(){ //WEB-INF/views/user/join.jsp로 간다
    }




}

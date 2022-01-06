package com.koreait.community.user;

import com.koreait.community.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;



@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/login")
    public void login(){}


    @GetMapping("/join")
    public void join(){ //WEB-INF/views/user/join.jsp로 간다
    }

    @GetMapping("/idChk/{uid}")
    @ResponseBody /*return이 json이 됨*/
    public Map<String, Integer> idChk(@PathVariable String uid){
        System.out.println("uid : "+ uid);

        Map<String, Integer> res = new HashMap();
        res.put("result", service.idChk(uid));
        return res;
    }

    @PostMapping("/join")
    public String joinProc(UserEntity entity, RedirectAttributes reAttr){
        int result = service.join(entity);
        return "redirect:/user/login";


    }
    }







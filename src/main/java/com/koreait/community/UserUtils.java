package com.koreait.community;

import com.koreait.community.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class UserUtils {//로그인한 사람 PK 가져오는거

    @Autowired
    private HttpSession hs;


    public void setLoginUser(UserEntity entity){
        hs.setAttribute(Const.LOGIN_USER,entity);
    }

    public UserEntity getLoginUser(){
        return (UserEntity) hs.getAttribute(Const.LOGIN_USER);
    }

    public int getLoginUserPk(){ //login한 사람 iuser 값 받아오는것.
        return getLoginUser() == null ? 0 : getLoginUser().getIuser();
    }

}

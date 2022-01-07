package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.UserUtils;
import com.koreait.community.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserUtils userUtils;


    public int idChk(String uid){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);

        UserEntity result = mapper.selUser(entity);
        //아이디가 없으면 리턴1, 있으면 0 
        //user/idChk/{uid} 에서 uid값 넣어보기
        return result == null ? 1 : 0;
    }

    public int join(UserEntity entity){//uid, upw, nm, gender
        UserEntity copyEntity = new UserEntity();
        BeanUtils.copyProperties(entity,copyEntity);
        // todo 지난번꺼랑 비교해보기 .
        //비밀번호 암호화
        String hashPw = BCrypt.hashpw(copyEntity.getUpw(),BCrypt.gensalt());
        copyEntity.setUpw(hashPw);
        return mapper.insUser(entity);

    }

    public int login(UserEntity entity){
        UserEntity dbUser = null;

        try{dbUser = mapper.selUser(entity);
        //select해봐라 loginUser
            }
        catch (Exception e){
            e.printStackTrace();
            return 0; //예외상황? 에러
        }
        if(dbUser == null) {
            //아이디로 찾는데 없다? 아이디없다
            return 2;
        }
        else if(!BCrypt.checkpw(entity.getUpw(),dbUser.getUpw())) {
            //비교해본다 entity의 pw와  selUser한 pw를
            //일치하면 true. 세션에 담는다. key값은 Const.LOGIN_USER
            return 3; //비번 다름
        }
        dbUser.setUpw(null);
        dbUser.setRdt(null);
        dbUser.setMdt(null);
        userUtils.setLoginUser(dbUser);
        return 1; //로그인 성공

    }


}

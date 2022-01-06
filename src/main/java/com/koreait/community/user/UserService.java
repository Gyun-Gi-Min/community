package com.koreait.community.user;

import com.koreait.community.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public int idChk(String uid){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);

        UserEntity result = mapper.selUser(entity);
        //아이디가 없으면 리턴1, 있으면 0 
        //user/idChk/{uid} 에서 uid값 넣어보기
        return result == null ? 1 : 0;
    }

    public int join(UserEntity entity){
        String plainPw = entity.getUpw();
        String hashPw = BCrypt.hashpw(plainPw,BCrypt.gensalt());
        entity.setUpw(hashPw);
        int result = mapper.insUser(entity);
        return result;

    }



}

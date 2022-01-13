package com.koreait.community.user;

import com.koreait.community.Const;
import com.koreait.community.MyFileUtils;
import com.koreait.community.UserUtils;
import com.koreait.community.model.UserDto;
import com.koreait.community.model.UserEntity;
import org.apache.catalina.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserUtils userUtils;

    @Autowired private MyFileUtils fileUtils;


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
        // entity가 원본 copyEntity가 복사대상
        //비밀번호 암호화
        String hashPw = BCrypt.hashpw(copyEntity.getUpw(),BCrypt.gensalt());
        copyEntity.setUpw(hashPw);
        return mapper.insUser(copyEntity);

    }

    public int login(UserEntity entity){
        UserEntity dbUser = null;
        try {
            dbUser = mapper.selUser(entity);
            //select 해봐.
        }catch (Exception e){
            e.printStackTrace();
            return 0; //에러
        }
        if(dbUser==null){
            return 2; // 없다? 아이디가 없다~
        }
        if(!BCrypt.checkpw(entity.getUpw(),dbUser.getUpw())){
            return 3; //비번 틀렷음
        }
        //이러한 과정을 거치고 내려오면 왜 다른값을 초기화하는거쥐?/
        dbUser.setUpw(null);
        dbUser.setRdt(null);
        dbUser.setMdt(null);
        userUtils.setLoginUser(dbUser);
        return 1; //로그인 성공
    }

    //이미지 업로드 처리 및 저장 파일명 리턴
    public String uploadProfileImg(MultipartFile mf){
        //압축으로 들어간다??
        if(mf == null){return  null;} //null 체크

        UserEntity loginUser = userUtils.getLoginUser();

        final String PATH = Const.UPLOAD_IMG_PATH + "/user/" + loginUser.getIuser();
        String fileNm = fileUtils.saveFile(PATH,mf);
        System.out.printf(" fileNm : " + fileNm);
        if(fileNm == null){return  null;}

        UserEntity entity = new UserEntity();
        entity.setIuser(loginUser.getIuser());

        //기존 파일명
        String oldFilePath = PATH + "/" + userUtils.getLoginUser().getProfileimg();
        fileUtils.delFile(oldFilePath);

        //파일명을 t_user 테이블에 update
        entity.setProfileimg(fileNm);
        mapper.updUser(entity);

        //세션 프로필 파일명을 수정해 준다.
        loginUser.setProfileimg(fileNm);

        return fileNm;
    }

    public int changePassword(UserDto dto) {
        dto.setIuser(userUtils.getLoginUserPk());
        UserEntity dbUser = mapper.selUser(dto);
        if(!BCrypt.checkpw(dto.getCurrentupw(), dbUser.getUpw())) {
            return 2; //현재비밀번호 다름
        }
        String hashedPw = BCrypt.hashpw(dto.getUpw(), BCrypt.gensalt());
        dto.setUpw(hashedPw);
        return mapper.updUser(dto);
    }



}

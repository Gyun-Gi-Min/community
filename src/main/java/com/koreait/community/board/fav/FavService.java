package com.koreait.community.board.fav;

import com.koreait.community.UserUtils;
import com.koreait.community.model.BoardCmtEntity;
import com.koreait.community.model.BoardFavEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FavService {
    @Autowired private FavMapper mapper;
    @Autowired private UserUtils userUtils;

    public int insBoardFav(BoardFavEntity entity){
        entity.setIuser(userUtils.getLoginUserPk()); //iuser
        return mapper.insBoardFav(entity);
    }

    public BoardFavEntity selBoardFav(int iboard){
        BoardFavEntity entity = new BoardFavEntity();
        entity.setIboard(iboard); //iboard
        entity.setIuser(userUtils.getLoginUserPk()); //iuser
        return mapper.selBoardFav(entity); //iboard iuser
        //return mapper.selBoardFav(createBoardFavEntity(iboard));
    }

    public int delBoardFav(int iboard){
        BoardFavEntity entity = new BoardFavEntity();
        entity.setIboard(iboard); //iboard
        entity.setIuser(userUtils.getLoginUserPk()); //iuser
        return mapper.delBoardFav(entity);
        //return mapper.delBoardFav(createBoardFavEntity(iboard));
    }

    // 위에서 중복소스 되는거 이렇게 메소드 따로 만들어서 사용할수있음.
    private BoardFavEntity createBoardFavEntity(int iboard){
        BoardFavEntity entity = new BoardFavEntity();
        entity.setIboard(iboard); //iboard
        entity.setIuser(userUtils.getLoginUserPk()); //iuser
        return entity;
    }


}

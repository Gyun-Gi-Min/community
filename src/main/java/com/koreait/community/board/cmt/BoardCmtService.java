package com.koreait.community.board.cmt;

import com.koreait.community.UserUtils;
import com.koreait.community.model.BoardCmtEntity;
import com.koreait.community.model.BoardCmtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCmtService {
        @Autowired private UserUtils userUtils;
        @Autowired private BoardCmtMapper mapper;

        public int insBoardCmt(BoardCmtEntity entity){
            entity.setIuser(userUtils.getLoginUserPk());
            return mapper.insBoardCmt(entity);
        }

        public List<BoardCmtVo> selBoardCmtList(BoardCmtEntity entity) {
            return mapper.selBoardCmtList(entity);
        }
}

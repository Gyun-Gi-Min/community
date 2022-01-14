package com.koreait.community.board;

import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardEntity;
import com.koreait.community.model.BoardPrevNextVo;
import com.koreait.community.model.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int insBoard(BoardEntity entity);
    List<BoardVo> selBoardList(BoardDTO dto);
    BoardVo selBoardDetail(BoardDTO dto);
    BoardPrevNextVo selPrevNext(BoardVo vo);
    int addHits(BoardEntity dto);
    int updBoard(BoardEntity dto);



}

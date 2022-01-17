package com.koreait.community.board.cmt;


import com.koreait.community.model.BoardCmtEntity;
import com.koreait.community.model.BoardCmtVo;
import com.koreait.community.model.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board/cmt")
public class BoardCmtController {

    @Autowired private BoardCmtService service;

    @PostMapping
    public Map<String, Integer> insBoardCmt(@RequestBody BoardCmtEntity entity){
        System.out.println(entity);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insBoardCmt(entity));
        return result;
    }

    @GetMapping("/{iboard}")
    public List<BoardCmtVo> selBoardCmtList(@PathVariable int iboard) {
        System.out.println("iboard : " + iboard);
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(iboard);
        return service.selBoardCmtList(entity);
    }


}

package com.koreait.community.board;


import com.koreait.community.Const;
import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardEntity;
import com.koreait.community.model.BoardPrevNextVo;
import com.koreait.community.model.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private  BoardService service;

    @GetMapping("/list/{icategory}")
    public String list(@PathVariable int icategory, BoardDTO dto, Model model){
        model.addAttribute(Const.I_CATEGORY);
        model.addAttribute(Const.LIST,service.selBoardList(dto));
        dto.setIcategory(icategory);
        return "board/list"; //return 하는값으로 1차 2차 주소 하는거임.ㅈ
    }

    @GetMapping("/write")
    public void write(){

    }
    @PostMapping("/write")
    public String writeProc(BoardEntity entity){
        int result = service.insBoard(entity);
            return "redirect:/board/list/" +entity.getIcategory();

    }

    @GetMapping("/detail")
    public void detail(BoardDTO dto, Model model, HttpServletRequest req) {
        String lastIp=req.getHeader("X-FORWARDED-FOR");
        if(lastIp == null){
            lastIp = req.getRemoteAddr();
        }
        //System.out.println("lastIp : " + lastIp);
        dto.setLastip(lastIp);
        model.addAttribute(Const.DATA, service.selBoardDetail(dto));

        BoardVo vo = service.selBoardDetail(dto);
        BoardPrevNextVo pnVo = service.selPrevNext(vo);
        model.addAttribute(Const.DATA, vo);
        model.addAttribute(Const.PREV_NEXT,pnVo);


    }


    @GetMapping("/del")
    public String delProc(BoardEntity entity){
        int result = service.delBoard(entity);
        return "redirect:/board/list/"+entity.getIcategory();

    }

    @GetMapping("/mod")
    public String mod(BoardDTO dto, Model model){
        model.addAttribute(Const.DATA,service.selBoardDetail(dto));
        return "board/write";

    }

    @PostMapping("/mod")
    public String modProc(BoardEntity entity){
        int result = service.updBoard(entity);
        return "redirect:/board/detail?iboard=" + entity.getIboard();
    }

    @GetMapping("/mypage/profile")
    public void mypageProfile() {}
}



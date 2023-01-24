package org.hsh.sb01.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hsh.sb01.dto.BoardDTO;
import org.hsh.sb01.dto.PageRequestDTO;
import org.hsh.sb01.dto.PageResponseDTO;
import org.hsh.sb01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);

        log.info(pageResponseDTO);

        model.addAttribute("responseDTO",pageResponseDTO);
    }

    @GetMapping("/register") //일단 화면으로는 보내줘야지.
    public void registerGet(){

    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO , BindingResult bindingResult, RedirectAttributes redirectAttributes){
        //BindingResult -> Valid에러 발생 시 에러를 저장하는 요소임.
        log.info("board POST Start");

        if(bindingResult.hasErrors()){
            log.info("has errors............");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/board/register";
        }

        log.info(boardDTO);

        Long bno = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/read" , "/modify"})
    public void read(Long bno, PageRequestDTO pageRequestDTO, Model model){

        BoardDTO boardDTO = boardService.readOne(bno);

        log.info(boardDTO);

        model.addAttribute("dto",boardDTO);
    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

        log.info("board modify start");

        if(bindingResult.hasErrors()){
            String link = pageRequestDTO.getLink();;

            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/board/modify?bno"+link;
        }

        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("result","modified");

        redirectAttributes.addFlashAttribute("dto",boardDTO.getBno());

        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){
        boardService.remove(bno);

        redirectAttributes.addFlashAttribute("result","removed");

        return "redirect:/board/list";
    }
}
package com.example.spdiexample.controller;

import com.example.spdiexample.dto.todoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/todo")
public class TodoController {

    @RequestMapping("/list")
    public void list(){
        log.info("list..............");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register(){
        log.info("reguster..............");
    }

    @PostMapping("/register")
    public void registerPost(todoDTO todoDTO){
        log.info("POST REGISTER ...............");
        log.info(todoDTO);
    }

    @GetMapping("/ex4")
    public void ex4(Model model){
        log.info("-----------------------------------");
        model.addAttribute("message","hello, world");
    }

    //Redirect
    @PostMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addAttribute("name","ABC"); // 쿼리 스트링에 추가
        redirectAttributes.addFlashAttribute("result","success"); // JSP에서 일회용으로 사용가능

        return "redirect:/ex6";
    }

    @GetMapping("/ex6")
    public void ex6(){
        
    }
}

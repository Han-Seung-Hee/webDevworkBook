package com.example.spdiexample.controller;

import com.example.spdiexample.controller.formatter.LocalDateFormatter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello(){
        log.info("hello.........................");
    }

    @GetMapping("/ex1")
    public void ex1(String name, int age){
        log.info("ex1 execute...........");
        log.info("name :::::::::::: " + name);
        log.info("age :::::::::::: " + age);

    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name",defaultValue = "Han") String name,
                    @RequestParam(name = "age" ,defaultValue = "21") int age){

        log.info("ex2 execute...........");
        log.info("name :::::::::::: " + name);
        log.info("age :::::::::::: " + age);


    }

    //날짜 처리(FORMATTER 사용)
    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("ex3..................");
        log.info(dueDate);

    }
}
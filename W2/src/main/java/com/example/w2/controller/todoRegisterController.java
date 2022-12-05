package com.example.w2.controller;

import lombok.extern.log4j.Log4j2;
import com.example.w2.dto.TodoDTO;
import com.example.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoRegisterController", value="/todo/register")
@Log4j2
public class todoRegisterController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        log.info("/todo/register GET : START");

        HttpSession httpSession = req.getSession();

        if(httpSession.isNew()){ // 신규 세션이면 로그인으로 튕겨버린다.
            log.info("No Session!");
            resp.sendRedirect("/Login");
            return;
        }

        if(httpSession.getAttribute("LoginInfo")==null){
            log.info("No Login Info");
            resp.sendRedirect("/login");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATETIMEFORMATTER)).build();

        log.info("/todo/register POST START");
        log.info(todoDTO);
        try{
            todoService.register(todoDTO);
        }catch(Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list");
    }

}

package com.example.w2.controller;


import lombok.extern.log4j.Log4j2;
import com.example.w2.dto.TodoDTO;
import com.example.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="todoReadController", value = "/todo/read")
@Log4j2
public class todoReadController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        try{
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO todoDTO = todoService.selectOne(tno);

            req.setAttribute("dto",todoDTO);

            // 쿠키 처리 추가 START
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodoCookie.getValue();
            boolean exist = false;

            if(todoListStr != null && todoListStr.contains(tno + "-")){
                exist = true;
            }

            log.info("existence = " + exist);

            if(!exist){
                todoListStr += tno + "-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60*60*24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);
        } catch (Exception e) {
            log.info("read fucked");
            throw new ServletException("read fucked");
        }
    }

    private Cookie findCookie(Cookie[] cookies, String cookieName){

        Cookie targetCookie = null;

        if(cookies != null && cookies.length > 0){
            for(Cookie ch : cookies){
                if(ch.getName().equals(cookieName)){
                    targetCookie = ch;
                    break;
                }
            }
        }

        if(targetCookie == null){
            targetCookie = new Cookie(cookieName , "");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }

        return targetCookie;
    }
}

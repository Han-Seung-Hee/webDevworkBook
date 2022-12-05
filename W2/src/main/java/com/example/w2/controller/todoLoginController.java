package com.example.w2.controller;

import com.example.w2.dto.MemberDTO;
import com.example.w2.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
@Log4j2
public class todoLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        log.info("Login Start");

        req.getRequestDispatcher("/WEB-INF/Login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{

        log.info("login post start................");

        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");

        String auto = req.getParameter("auto");

        boolean rememberMe = auto != null && auto.equals("on");

        try{
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);
            if(rememberMe){
                String uuid = UUID.randomUUID().toString();

                MemberService.INSTANCE.updateUuid(mid,uuid);
                memberDTO.setUuid(uuid);

                Cookie rememberMeCookie =
                            new Cookie("remember-me",uuid);
                rememberMeCookie.setMaxAge(60*60*24*7);
                rememberMeCookie.setPath("/");

                resp.addCookie(rememberMeCookie);
            }

            HttpSession session = req.getSession();
            session.setAttribute("LoginInfo", memberDTO);
            resp.sendRedirect("/todo/list");
        } catch (Exception e){
            resp.sendRedirect("/login?result=error");

        }
    }

}

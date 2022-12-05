package com.example.w2.controller;

import lombok.extern.log4j.Log4j2;
import com.example.w2.dto.TodoDTO;
import com.example.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoModifyController", value = "/todo/modify")
@Log4j2
public class todoModifyController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        try{
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO todoDTO = todoService.selectOne(tno);

            req.setAttribute("dto",todoDTO);
            req.getRequestDispatcher("/WEB-INF/todo/modify").forward(req,resp);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("modify... fucked");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String finishedStr = req.getParameter("finished");

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(Long.parseLong(req.getParameter("tno")))
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"),DATEFORMATTER))
                .finished(finishedStr != null && finishedStr.equals("on")).build();

        log.info("Object todoDTO for modify TODO");
        log.info(todoDTO);

        try{
            todoService.modify(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/todo/list");
    }
}

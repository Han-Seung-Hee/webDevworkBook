package com.example.w2.calc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "calcController" , urlPatterns = "/calc/makeResult")
public class CalcController extends HttpServlet {

    // doPost 메서드는 클라이언트에서 post로 요청을 해야하만 호출이 가능하다.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");

        System.out.printf(" num1: %s", num1);
        System.out.printf(" num2: %s", num2);

        RequestDispatcher rds = req.getRequestDispatcher("/WEB-INF/calc/calcResult.jsp");
        rds.forward(req,resp);

    }

}

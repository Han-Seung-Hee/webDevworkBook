package com.example.w2.filter;

import com.example.w2.dto.MemberDTO;
import com.example.w2.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = "/todo/*")
@Log4j2
public class LoginCheckFilter implements Filter {

    // 로그인 여부 체크
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("Login check Filter");

        //servletRequest / servletResponse 는 상위 타입이므로
        // http 작업을 하려면 형변환 해야함
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        if(session.getAttribute("LoginInfo") != null){ // 있으면 다음 작업 수행
            chain.doFilter(request,response);
            return;
        }

        // 세션에 없으면 쿠키에서 검색
        Cookie cookie = findCookie(req.getCookies(),"remember-me");

        // 둘다 없으면 짤없이 로그인으로 보내기
        if(cookie==null){
            resp.sendRedirect("/login");
            return;
        }

        // 쿠키카 존재
        log.info("Cookie Exist!!!!!!!!!!!!!!!");
        //uuid value
        String uuid = cookie.getValue();

        try{
            MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);

            log.info("User Information :::::: " + memberDTO);

            if(memberDTO == null){
                throw new Exception("Cookie value is not exist. u r fucked");
            }

            // 회원정보를 세션에 추가
            session.setAttribute("LoginInfo",memberDTO);
            chain.doFilter(request,response);

        }catch (Exception e){
            e.printStackTrace();
            resp.sendRedirect("/login");
        }
    }

    private Cookie findCookie(Cookie[] cookies , String name){
        if(cookies == null || cookies.length == 0){
            return null;
        }

        Optional<Cookie> result = Arrays.stream(cookies)
                .filter(ck -> ck.getName().equals(name))
                .findFirst();

        return result.isPresent()?result.get():null;
    }
}

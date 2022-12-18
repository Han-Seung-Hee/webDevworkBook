package com.example.spdiexample.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice // Handling Exception on Controllers
@Log4j2
public class CommonExceptionAdvice {

    @ResponseBody // Transfer String which returned as it is
    @ExceptionHandler(NumberFormatException.class) // Below Method will handle NumberFormatException
    public String exceptionNumber(NumberFormatException exception){
        log.error("======================================");
        log.error(exception.getMessage());

        return "NUMBER FORMAT EXCEPTION";
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptionCommon(Exception exception){
        log.error("========================");
        log.error(exception.getMessage());

        StringBuffer sb = new StringBuffer("<ul>");
        sb.append("<li>"+ exception.getMessage() + "</li>");

        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> { sb.append("<li>"+stackTraceElement+"</li>");});
        sb.append("</ul>");
        // 위와 같은 형식으로 자세하게 나타내는 것은 개발서버에서만
        // 운영배포시에는 별도 에러페이지 처리를 하는 것이 좋다.
        return sb.toString();
    }

    //Error handling for HTTP 404 Error
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(){
        return "custom404"; // redirect to /custom404.jsp
    }
}

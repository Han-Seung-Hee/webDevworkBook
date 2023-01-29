package org.hsh.sb01.Controller.advice;
// Rest Api 방식에서의 호출은 오류 검출이 어려움.
// 따라서 @Valid에서 문제가 생기는 경우 , 처리할 수 있도록
// @RestControllerAdvice를 추가하여 처리해준다.

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {

    // 컨트롤러에서 BindException발생 시 400Error를 던져줌.
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String,String>> handleBindException(BindException e){

        log.error(e);

        Map<String,String> errorMap = new HashMap<>();

        if(e.hasErrors()){
            BindingResult bindingResult = e.getBindingResult();
             bindingResult.getFieldErrors().forEach(fieldError -> {
                 errorMap.put(fieldError.getField(),fieldError.getCode());
             });
        }

        return ResponseEntity.badRequest().body(errorMap);
    }
}

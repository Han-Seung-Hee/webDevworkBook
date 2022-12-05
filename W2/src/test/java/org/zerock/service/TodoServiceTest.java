package org.zerock.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.w2.domain.TodoVO;
import com.example.w2.dto.TodoDTO;
import com.example.w2.service.TodoService;

import java.time.LocalDate;

@Log4j2
public class TodoServiceTest {

    private TodoService todoService;
    private TodoVO todoVO;

    @BeforeEach
    public void ready(){
        todoService = TodoService.INSTANCE;
    }

    // DTO -> VO 변환 테스트
    @Test
    public void testRegister() throws Exception{
        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC TEST TITLE")
                .dueDate(LocalDate.now())
                .build();

        log.info("---------------------------------");
        log.info(todoDTO);
        todoService.register(todoDTO);
    }
}

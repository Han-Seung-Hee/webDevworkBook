package org.hsh.sb01.service;

import lombok.extern.log4j.Log4j2;
import org.hsh.sb01.dto.BoardDTO;
import org.hsh.sb01.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;
    @Test
    public void testRegister(){
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                                    .bno(999L)
                                    .title("Registeration Test")
                                    .content("Teststetst")
                                    .writer("user00")
                                    .build();

        Long bno = boardService.register(boardDTO);
        log.info("result" + bno);
    }

    @Test void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("AAAAAAA")
                .content("MODIFY TEST")
                .bno(1L)
                .build();

        boardService.modify(boardDTO);
    }
}